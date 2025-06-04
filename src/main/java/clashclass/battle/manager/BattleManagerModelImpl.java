package clashclass.battle.manager;

import clashclass.ai.behaviourtree.BehaviourTree;
import clashclass.ai.behaviourtree.blackboard.BlackboardPropertyImpl;
import clashclass.ai.behaviourtree.blackboard.wrappers.GameObjectListWrapper;
import clashclass.ai.pathfinding.AiNodesBuilder;
import clashclass.ai.pathfinding.AiNodesBuilderImpl;
import clashclass.ai.pathfinding.PathNodeGrid;
import clashclass.battle.destruction.BattleTroopsBehaviorManager;
import clashclass.battle.destruction.BattleTroopsBehaviorManagerImpl;
import clashclass.battle.destruction.DestructionObservable;
import clashclass.battle.troopdeath.DefenseBuildingsBattleBehaviorManager;
import clashclass.battle.troopdeath.TroopDeathObservable;
import clashclass.commons.BuildingFlagsComponent;
import clashclass.commons.BuildingTypeComponentImpl;
import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.BUILDING_FLAG;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.buildings.VillageElementData;
import clashclass.elements.troops.BattleTroopFactoryImpl;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.elements.troops.TroopFactory;
import clashclass.gamestate.GameStateManager;
import clashclass.saveload.BattleVillageDecoderImpl;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.saveload.VillageDecoder;
import clashclass.village.Village;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class BattleManagerModelImpl implements BattleManagerModel {
    private final Village playerVillage;
    private final Village battleVillage;
    private final TroopFactory troopFactory;
    private final EnumMap<TROOP_TYPE, Function<Vector2D, GameObject>> troopCreatorsMap;
    private final Set<GameObject> activeTroops;
    private final AiNodesBuilder aiNodesBuilder;
    private GameStateManager gameStateManager;
    private TROOP_TYPE currentSelectedTroop;
    private BattleTroopsBehaviorManager battleTroopsBehaviorManager;
    private DefenseBuildingsBattleBehaviorManager defenseBuildingsBattleBehaviorManager;
    private BattleManagerController controller;

    public BattleManagerModelImpl(final Path playerVillageCsvPath, final Path battleVillageCsvPath) {
        this.playerVillage = this.loadVillage(playerVillageCsvPath, new PlayerVillageDecoderImpl());
        this.battleVillage = this.loadVillage(battleVillageCsvPath, new BattleVillageDecoderImpl());
        this.troopFactory = new BattleTroopFactoryImpl();
        this.activeTroops = new HashSet<>();
        this.aiNodesBuilder = new AiNodesBuilderImpl();

        this.troopCreatorsMap = new EnumMap<>(TROOP_TYPE.class);
        this.troopCreatorsMap.put(TROOP_TYPE.BARBARIAN, this.troopFactory::createBarbarian);
        this.troopCreatorsMap.put(TROOP_TYPE.ARCHER, this.troopFactory::createArcher);

        this.handleBattleVillageDefenseBuildings();
    }

    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    private Village loadVillage(final Path csvPath, final VillageDecoder decoder)
    {
        try {
            decoder.setComponentFactory(new ComponentFactoryImpl());
            final var csvData = Files.readString(csvPath);
            return decoder.decode(csvData);
        } catch (IOException e) {
            System.err.println("Could not read village data");
        }
        return null;
    }

    private void handleBattleVillageDefenseBuildings() {
        this.battleVillage.getGameObjects().stream()
                .filter(x -> x.getComponentOfType(BuildingFlagsComponent.class).isPresent())
                .filter(x -> x.getComponentOfType(BuildingFlagsComponent.class).get()
                        .getFlags().contains(BUILDING_FLAG.DEFENSE))
                .forEach(defenseBuilding -> {
                    final var behaviourTree = defenseBuilding.getComponentOfType(BehaviourTree.class).get();
                    final var blackboard = behaviourTree.getBlackboard();

                    blackboard.setProperty("actor", new BlackboardPropertyImpl<>(defenseBuilding, GameObject.class));
                    blackboard.setProperty("troops", new BlackboardPropertyImpl<>(new GameObjectListWrapper(
                            this.activeTroops.stream().toList()), GameObjectListWrapper.class));
                });
    }

    private void handleBattleVillageBuildings() {
        this.battleVillage.getGameObjects().stream()
                .filter(x -> x.getComponentOfType(DestructionObservable.class).isPresent())
                .map(x -> x.getComponentOfType(DestructionObservable.class).get())
                .forEach(destructionObservable -> {
                    destructionObservable.addObserver(this.battleTroopsBehaviorManager);
                });
    }

    @Override
    public void setCurrentSelectedTroop(final TROOP_TYPE troopType) {
        this.currentSelectedTroop = troopType;
    }

    @Override
    public GameStateManager getGameStateManager() {
        return this.gameStateManager;
    }

    @Override
    public Village getPlayerVillage() {
        return this.playerVillage;
    }

    @Override
    public Village getBattleVillage() {
        return this.battleVillage;
    }

    @Override
    public TROOP_TYPE getCurrentSelectedTroop() {
        return this.currentSelectedTroop;
    }

    @Override
    public void createTroop(final Vector2D position) {
        final var player = this.playerVillage.getPlayer();
        if (player.hasArmyCampTroop(this.currentSelectedTroop)) {
            player.removeArmyCampTroop(this.currentSelectedTroop, 1);
            final var troopGameObject = this.troopCreatorsMap
                    .get(this.currentSelectedTroop)
                    .apply(position);

            this.activeTroops.add(troopGameObject);

            final var deathObservable = troopGameObject.getComponentOfType(TroopDeathObservable.class).get();
            deathObservable.addObserver(this.defenseBuildingsBattleBehaviorManager);

            final var behaviourTree = troopGameObject.getComponentOfType(BehaviourTree.class).get();
            final var blackboard = behaviourTree.getBlackboard();

            final var pathNodeGrid = aiNodesBuilder.buildPathNodeList(this.battleVillage);

            blackboard.setProperty("actor", new BlackboardPropertyImpl<>(troopGameObject, GameObject.class));
            blackboard.setProperty("potentialTargets", new BlackboardPropertyImpl<>(new GameObjectListWrapper(
                    this.battleVillage.getGameObjects().stream().toList()), GameObjectListWrapper.class));
            blackboard.setProperty("pathNodeGrid", new BlackboardPropertyImpl<>(pathNodeGrid, PathNodeGrid.class));

            this.gameStateManager.getGameEngine().addGameObject(troopGameObject);
            behaviourTree.start();
        }

        this.battleVillage.getGameObjects().stream()
                .filter(x -> x.getComponentOfType(BuildingFlagsComponent.class).isPresent())
                .filter(x -> x.getComponentOfType(BuildingFlagsComponent.class).get()
                        .getFlags().contains(BUILDING_FLAG.DEFENSE))
                .forEach(defenseBuilding -> {
                    final var behaviourTree = defenseBuilding.getComponentOfType(BehaviourTree.class).get();
                    final var blackboard = behaviourTree.getBlackboard();

                    blackboard.getProperty("troops", GameObjectListWrapper.class)
                            .setValue(new GameObjectListWrapper(this.activeTroops.stream().toList()));
                });
    }

    @Override
    public Set<GameObject> getActiveTroops() {
        return this.activeTroops;
    }

    @Override
    public void setController(final BattleManagerController controller) {
        this.controller = controller;
        this.battleTroopsBehaviorManager = new BattleTroopsBehaviorManagerImpl(this.controller);
        this.defenseBuildingsBattleBehaviorManager = new DefenseBuildingsBattleBehaviorManager(this.controller);
        this.handleBattleVillageBuildings();
    }

    @Override
    public void updateVillageState(final GameObject destroyedBuilding) {
        this.battleVillage.removeBuilding(destroyedBuilding);
        final var pathNodeGrid = aiNodesBuilder.buildPathNodeList(destroyedBuilding);
        final var potentialTargets = new GameObjectListWrapper(this.battleVillage.getGameObjects().stream().toList());

        this.activeTroops.forEach(troopGameObject -> {
            final var behaviourTree = troopGameObject.getComponentOfType(BehaviourTree.class).get();
            final var blackboard = behaviourTree.getBlackboard();

            blackboard.getProperty("potentialTargets", GameObjectListWrapper.class).setValue(potentialTargets);

            final var currentTarget = blackboard.getProperty("target", GameObject.class).getValue();
            if (currentTarget != null && currentTarget.equals(destroyedBuilding)) {
                blackboard.getProperty("pathNodeGrid", PathNodeGrid.class).setValue(pathNodeGrid);
                behaviourTree.restart();
            }
        });
    }

    @Override
    public void updateTroopsState(GameObject destroyedTroop) {
        this.activeTroops.remove(destroyedTroop);
        final var troops = new GameObjectListWrapper(this.activeTroops.stream().toList());

        this.battleVillage.getGameObjects().stream()
                .filter(gameObject -> gameObject.getComponentOfType(BuildingFlagsComponent.class).get()
                        .getFlags().contains(BUILDING_FLAG.DEFENSE))
                .forEach(defenseBuilding -> {
                    final var behaviourTree = defenseBuilding.getComponentOfType(BehaviourTree.class).get();
                    final var blackboard = behaviourTree.getBlackboard();

                    blackboard.getProperty("troops", GameObjectListWrapper.class).setValue(troops);

                    final var currentTarget = blackboard.getProperty("target", GameObject.class).getValue();
                    if (currentTarget != null && currentTarget.equals(destroyedTroop)) {
                        behaviourTree.restart();
                    }
                });
    }
}
