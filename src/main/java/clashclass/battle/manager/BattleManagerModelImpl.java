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
    private final Path battleVillageCsvPath;
    private GameStateManager gameStateManager;
    private Village battleVillage;
    private TROOP_TYPE currentSelectedTroop;

    public BattleManagerModelImpl(final Path battleVillageCsvPath) {
        this.battleVillageCsvPath = battleVillageCsvPath;
        this.loadBattleVillage();
    }

    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    private void loadBattleVillage()
    {
        try {
            final var decoder = new BattleVillageDecoderImpl();
            decoder.setComponentFactory(new ComponentFactoryImpl());
            final var csvData = Files.readString(this.battleVillageCsvPath);
            this.battleVillage = decoder.decode(csvData);
        } catch (IOException e) {
            System.err.println("Could not read battle Village");
        }
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
    public Village getBattleVillage() {
        return this.battleVillage;
    }

    @Override
    public TROOP_TYPE getCurrentSelectedTroop() {
        return this.currentSelectedTroop;
    }
}
