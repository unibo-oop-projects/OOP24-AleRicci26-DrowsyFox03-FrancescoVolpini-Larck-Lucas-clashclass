package clashclass.battle.manager;

import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.gamestate.GameStateManager;
import clashclass.saveload.BattleVillageDecoderImpl;
import clashclass.village.Village;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BattleManagerModelImpl implements BattleManagerModel {
    private GameStateManager gameStateManager;
    private final Village playerVillage;
    private final Village battleVillage;
    private TROOP_TYPE currentSelectedTroop;

    public BattleManagerModelImpl(final Path playerVillageCsvPath, final Path battleVillageCsvPath) {
        this.playerVillage = this.loadVillage(playerVillageCsvPath);
        this.battleVillage = this.loadVillage(battleVillageCsvPath);
    }

    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
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
}
