package clashclass.view.graphic;

import clashclass.battle.manager.BattleManagerControllerImpl;
import clashclass.battle.manager.BattleManagerModelImpl;
import clashclass.battle.manager.BattleManagerViewJavaFXImpl;
import clashclass.commons.ConversionUtility;
import clashclass.commons.GridTileData2D;
import clashclass.commons.Vector2D;
import clashclass.gamestate.GameStateManager;
import clashclass.gamestate.GameStateManagerImpl;
import clashclass.village.Village;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.engine.GameEngine;
import clashclass.engine.GameEngineImpl;
import clashclass.resources.Player;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.saveload.VillageDecoder;
import clashclass.village.manager.PlayerVillageController;
import clashclass.village.manager.PlayerVillageControllerImpl;
import clashclass.village.manager.PlayerVillageModelImpl;
import clashclass.village.manager.PlayerVillageViewJavaFXImpl;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public abstract class VillageSceneJFX extends AbstractBaseScene {
    public VillageSceneJFX(Window window, Stage stage, Path playerCsvPath, Path battleCsvPath) throws IOException {
        super(window);

        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, getWindowWidth(), getWindowHeight());
        stage.setScene(scene);
        stage.setTitle(getSceneTitle());
        stage.show();

        Canvas canvas = new Canvas(getWindowWidth(), getWindowHeight());
        final var gc = canvas.getGraphicsContext2D();
        final var graphics = new GraphicJavaFXImpl(gc, canvas, getWindowWidth(), getWindowHeight());

        root.getChildren().add(canvas);
        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);

        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());

        final var gameStateManager = new GameStateManagerImpl(
                graphics,
                () -> new PlayerVillageControllerImpl(
                        new PlayerVillageModelImpl(playerCsvPath),
                        new PlayerVillageViewJavaFXImpl(scene, root, this.getWindowWidth(), this.getWindowHeight())),
                () -> new BattleManagerControllerImpl(
                        new BattleManagerModelImpl(playerCsvPath, battleCsvPath),
                        new BattleManagerViewJavaFXImpl(scene, root)
                )
        );

        stage.setOnCloseRequest(event -> {
            gameStateManager.stopEngine();
        });

        gameStateManager.startEngine();

        initializeScene();
    }
    /**
     *
     */
    @Override
    public void initializeScene() {

    }

    // Da implementare per mappare correttamente i tipi in sprite
    protected abstract String mapTypeToSprite(GameObject go);

    // Titolo finestra specifico
    protected abstract String getSceneTitle();
}
