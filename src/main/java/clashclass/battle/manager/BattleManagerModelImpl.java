package clashclass.battle.manager;

import clashclass.ai.behaviourtree.BehaviourTree;
import clashclass.ai.behaviourtree.blackboard.BlackboardPropertyImpl;
import clashclass.ai.behaviourtree.blackboard.wrappers.GameObjectListWrapper;
import clashclass.ai.pathfinding.AiNodesBuilder;
import clashclass.ai.pathfinding.AiNodesBuilderImpl;
import clashclass.ai.pathfinding.PathNodeGrid;
import clashclass.battle.battlereport.*;
import clashclass.battle.destruction.*;
import clashclass.battle.timer.TimerGameImpl;
import clashclass.battle.timer.TimerImpl;
import clashclass.battle.troopdeath.DefenseBuildingsBattleBehaviorManager;
import clashclass.battle.troopdeath.EndBattleAllTroopsDead;
import clashclass.battle.troopdeath.EndBattleAllTroopsDeadGameImpl;
import clashclass.battle.troopdeath.TroopDeathObservable;
import clashclass.commons.*;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.BUILDING_FLAG;
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
import java.util.Timer;
import java.util.function.Function;

/**
 * Represents a {@link BattleManagerModel} implementation.
 */
public class BattleManagerModelImpl implements BattleManagerModel {
    private final Village playerVillage;
    private final Village battleVillage;
    private final TroopFactory troopFactory;
    private final EnumMap<TROOP_TYPE, Function<Vector2D, GameObject>> troopCreatorsMap;
    private final Set<GameObject> activeTroops;
    private final AiNodesBuilder aiNodesBuilder;
    private GameStateManager gameStateManager;
    private TROOP_TYPE currentSelectedTroop;
    private DefenseBuildingsBattleBehaviorManager defenseBuildingsBattleBehaviorManager;
    private BattleTroopsBehaviorManager battleTroopsBehaviorManager;
    private VillageDestructionManager villageDestructionManager;
    private EndBattleAllVillageDestroyed endBattleAllVillageDestroyedObserver;
    private EndBattleTimerIsOver endBattleTimerIsOverObserver;
    private EndBattleAllTroopsDead endBattleAllTroopsDeadObserver;
    private BattleManagerController controller;
    private BattleReportController battleReportController;
    private final clashclass.battle.timer.Timer battleTimer;
    private boolean battleStarted;
    private final double battleDurationSeconds = 60.0;

    /**
     * Constructs the model.
     *
     * @param playerVillageCsvPath the player village csv file path
     * @param battleVillageCsvPath the battle village csv file path
     */
    public BattleManagerModelImpl(final Path playerVillageCsvPath, final Path battleVillageCsvPath) {
        this.playerVillage = this.loadVillage(playerVillageCsvPath, new PlayerVillageDecoderImpl());
        this.battleVillage = this.loadVillage(battleVillageCsvPath, new BattleVillageDecoderImpl());
        this.troopFactory = new BattleTroopFactoryImpl();
        this.activeTroops = new HashSet<>();
        this.aiNodesBuilder = new AiNodesBuilderImpl();

        this.troopCreatorsMap = new EnumMap<>(TROOP_TYPE.class);
        this.troopCreatorsMap.put(TROOP_TYPE.BARBARIAN, this.troopFactory::createBarbarian);
        this.troopCreatorsMap.put(TROOP_TYPE.ARCHER, this.troopFactory::createArcher);

        this.battleTimer = new TimerGameImpl();

        this.handleBattleVillageDefenseBuildings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        this.battleReportController.setGameStateManager(gameStateManager);
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
                    destructionObservable.addObserver(this.villageDestructionManager);
                    destructionObservable.addObserver(this.battleTroopsBehaviorManager);
                    destructionObservable.addObserver(this.endBattleAllVillageDestroyedObserver);
                    destructionObservable.addObserver(this.endBattleTimerIsOverObserver);
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentSelectedTroop(final TROOP_TYPE troopType) {
        this.currentSelectedTroop = troopType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameStateManager getGameStateManager() {
        return this.gameStateManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Village getPlayerVillage() {
        return this.playerVillage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Village getBattleVillage() {
        return this.battleVillage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TROOP_TYPE getCurrentSelectedTroop() {
        return this.currentSelectedTroop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTroop(final Vector2D position) {
        final var gridCoordinates = ConversionUtility.convertWorldToGridPosition(position);
        if (this.battleVillage.isCellOutsideOfGrid(gridCoordinates) ||
                this.battleVillage.isCellBusy(gridCoordinates)) {
            return;
        }

        final var player = this.playerVillage.getPlayer();
        if (player.hasArmyCampTroop(this.currentSelectedTroop)) {
            player.removeArmyCampTroop(this.currentSelectedTroop, 1);
            final var troopGameObject = this.troopCreatorsMap
                    .get(this.currentSelectedTroop)
                    .apply(position);

            this.activeTroops.add(troopGameObject);

            final var deathObservable = troopGameObject.getComponentOfType(TroopDeathObservable.class).get();
            deathObservable.addObserver(this.defenseBuildingsBattleBehaviorManager);
            deathObservable.addObserver(this.endBattleAllTroopsDeadObserver);

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

        if (!this.battleStarted) {
            this.battleStarted = true;
            this.battleTimer.start();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getActiveTroops() {
        return this.activeTroops;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final BattleManagerController controller) {
        this.controller = controller;
        this.battleTroopsBehaviorManager = new BattleTroopsBehaviorManagerImpl(this.controller);
        this.defenseBuildingsBattleBehaviorManager = new DefenseBuildingsBattleBehaviorManager(this.controller);
        this.villageDestructionManager = new VillageDestructionManagerImpl(this.battleReportController);
        this.endBattleAllVillageDestroyedObserver = new EndBattleAllVillageDestroyedImpl(this.controller, this.battleReportController);
        this.endBattleTimerIsOverObserver = new EndBattleTimerIsOverImpl(this.controller);
        this.endBattleAllTroopsDeadObserver = new EndBattleAllTroopsDeadGameImpl(this.controller);
        this.handleBattleVillageBuildings();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.battleVillage.getGroundObjects().forEach(GameObject::destroy);
        this.battleVillage.getGameObjects().forEach(GameObject::destroy);
        this.activeTroops.forEach(GameObject::destroy);
        this.battleReportController.clearScene();
    }

    @Override
    public void buildBattleReport(final BattleReportView view) {
        final var nonWallBuildingsCount = (int) this.battleVillage.getBuildings().stream()
                .filter(x -> !x.getComponentOfType(BuildingTypeComponent.class).get()
                        .getBuildingType().equals(VillageElementData.WALL))
                .count();

        this.battleReportController = new BattleReportControllerImpl(
                new BattleReportModelImpl(nonWallBuildingsCount),
                view
        );
    }

    @Override
    public boolean isBattleStarted() {
        return this.battleStarted;
    }

    @Override
    public boolean isBattleTimeFinished() {
        return this.battleTimer.getElapsedTime() >= this.battleDurationSeconds;
    }

    @Override
    public boolean areAllTroopsDead() {
        return this.activeTroops.isEmpty() &&
                this.playerVillage.getPlayer().getArmyCampTroopTypes().stream()
                        .map(type -> this.playerVillage.getPlayer().getArmyCampTroopCount(type))
                        .allMatch(count -> count == 0);
    }

    @Override
    public void showBattleReport() {
        this.activeTroops.forEach(troop ->
                troop.getComponentOfType(BehaviourTree.class).get().stop());
        this.battleVillage.getBuildings().stream()
                .filter(x -> x.getComponentOfType(BuildingFlagsComponent.class).get()
                        .getFlags().contains(BUILDING_FLAG.DEFENSE))
                .forEach(defenseBuilding -> defenseBuilding
                        .getComponentOfType(BehaviourTree.class).get().stop());

        this.battleReportController.show();
    }
}
