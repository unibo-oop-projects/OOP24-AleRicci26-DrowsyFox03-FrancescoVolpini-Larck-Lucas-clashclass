package clashclass.gamestate;

import clashclass.commons.GridTileData2D;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.engine.GameEngine;
import clashclass.engine.GameEngineImpl;
import clashclass.resources.Player;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.view.graphic.Graphic;
import clashclass.village.Village;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Supplier;

public class GameStateManagerImpl implements GameStateManager {
    private final Supplier<GameStateController> playerVillageStateCreator;
    private final Supplier<GameStateController> battleStateCreator;
    private final GameEngine gameEngine;
    private GameStateController currentGameStateController;

    public GameStateManagerImpl(
            final Graphic graphic,
            final Supplier<GameStateController> playerVillageStateCreator,
            final Supplier<GameStateController> battleStateCreator) {
        this.playerVillageStateCreator = playerVillageStateCreator;
        this.battleStateCreator = battleStateCreator;

        final var decoder = new PlayerVillageDecoderImpl();
        decoder.setComponentFactory(new ComponentFactoryImpl());

        this.gameEngine = new GameEngineImpl(Optional.of(graphic));
        this.setStatePlayerVillage();
    }

    @Override
    public void setStatePlayerVillage() {
        this.clearCurrentState();
        this.currentGameStateController = this.playerVillageStateCreator.get();
        this.currentGameStateController.setGameStateManager(this);
    }

    @Override
    public void setStateBattle() {
        this.clearCurrentState();
        this.currentGameStateController = this.battleStateCreator.get();
        this.currentGameStateController.setGameStateManager(this);
    }

    @Override
    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    @Override
    public void startEngine() {
        this.gameEngine.start();
    }

    @Override
    public void stopEngine() {
        this.gameEngine.stop();
    }

    private void clearCurrentState() {
        if (this.currentGameStateController != null) {
            this.currentGameStateController.clearScene();
        }
    }
}
