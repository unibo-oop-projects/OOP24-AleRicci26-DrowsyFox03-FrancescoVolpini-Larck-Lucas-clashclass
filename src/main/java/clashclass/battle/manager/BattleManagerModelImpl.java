package clashclass.battle.manager;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.troops.BattleTroopFactoryImpl;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.elements.troops.TroopFactory;
import clashclass.gamestate.GameStateManager;
import clashclass.saveload.BattleVillageDecoderImpl;
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
    private GameStateManager gameStateManager;
    private TROOP_TYPE currentSelectedTroop;

    public BattleManagerModelImpl(final Path playerVillageCsvPath, final Path battleVillageCsvPath) {
        this.playerVillage = this.loadVillage(playerVillageCsvPath);
        this.battleVillage = this.loadVillage(battleVillageCsvPath);
        this.troopFactory = new BattleTroopFactoryImpl();
        this.activeTroops = new HashSet<>();

        this.troopCreatorsMap = new EnumMap<>(TROOP_TYPE.class);
        this.troopCreatorsMap.put(TROOP_TYPE.BARBARIAN, this.troopFactory::createBarbarian);
        this.troopCreatorsMap.put(TROOP_TYPE.ARCHER, this.troopFactory::createArcher);
    }

    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        this.currentSelectedTroop = this.playerVillage.getPlayer()
                .getArmyCampTroopTypes().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Expected at least one troop type."));
    }

    private Village loadVillage(final Path csvPath)
    {
        try {
            final var decoder = new BattleVillageDecoderImpl();
            decoder.setComponentFactory(new ComponentFactoryImpl());
            final var csvData = Files.readString(csvPath);
            return decoder.decode(csvData);
        } catch (IOException e) {
            System.err.println("Could not read village data");
        }
        return null;
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

            this.gameStateManager.getGameEngine().addGameObject(troopGameObject);
            this.activeTroops.add(troopGameObject);
        }
    }
}
