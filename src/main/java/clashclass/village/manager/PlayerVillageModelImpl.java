package clashclass.village.manager;

import clashclass.elements.ComponentFactoryImpl;
import clashclass.gamestate.GameStateManager;
import clashclass.saveload.BattleVillageDecoderImpl;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.village.Village;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlayerVillageModelImpl implements PlayerVillageModel {
    private Village playerVillage;
    private GameStateManager gameStateManager;

    public PlayerVillageModelImpl(final Path playerVillageCsvPath) {
        this.loadVillage(playerVillageCsvPath);
    }

    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    private void loadVillage(final Path csvPath)
    {
        try {
            final var decoder = new PlayerVillageDecoderImpl();
            decoder.setComponentFactory(new ComponentFactoryImpl());
            final var csvData = Files.readString(csvPath);
            this.playerVillage = decoder.decode(csvData);
        } catch (IOException e) {
            System.err.println("Could not read village data");
        }
    }

    @Override
    public GameStateManager getGameStateManager() {
        return this.gameStateManager;
    }

    @Override
    public Village getPlayerVillage() {
        return this.playerVillage;
    }
}
