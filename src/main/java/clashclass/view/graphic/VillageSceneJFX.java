package clashclass.view.graphic;

import clashclass.ai.pathfinding.PathNodeGrid;
import clashclass.ai.pathfinding.PathNodeGridImpl;
import clashclass.ai.pathfinding.PathNodeImpl;
import clashclass.commons.CellPosition2D;
import clashclass.commons.Vector2D;
import clashclass.commons.VectorInt2D;
import clashclass.commons.Village;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.elements.commons.CommonGameObjectFactoryImpl;
import clashclass.engine.GameEngine;
import clashclass.engine.GameEngineImpl;
import clashclass.resources.Player;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.saveload.VillageDecoder;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Cell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

public abstract class VillageSceneJFX extends AbstractBaseScene {
    private final VillageDecoder decoder;
    protected final Set<GameObject> gameObjects;
    protected final GraphicsContext gc;

    public VillageSceneJFX(Window window, Stage stage, Path csvPath) throws IOException {
        super(window);

        decoder = new PlayerVillageDecoderImpl(new PlayerBuildingFactoryImpl());
        decoder.setComponentFactory(new ComponentFactoryImpl());

        String csvData = Files.readString(csvPath);
        this.gameObjects = decodeVillage(csvData);

        Canvas canvas = new Canvas(getWindowWidth(), getWindowHeight());
        this.gc = canvas.getGraphicsContext2D();
        this.setGraphics(new GraphicJavaFXImpl(gc, canvas, getWindowWidth(), getWindowHeight()));

        Pane root = new Pane(canvas);
        root.setStyle("-fx-background-color: #0a8f32;");
        Scene scene = new Scene(root, getWindowWidth(), getWindowHeight());
        stage.setScene(scene);
        stage.setTitle(getSceneTitle());
        stage.show();

        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());

//        canvas.widthProperty().addListener((obs, oldVal, newVal) -> redraw());
//        canvas.heightProperty().addListener((obs, oldVal, newVal) -> redraw());

        final GameEngine gameEngine = new GameEngineImpl(this.getGraphics());

        final var player = new Player();
        final var village = new Village();

        this.gameObjects.forEach(gameObject -> village.placeBuilding(
                gameObject,
                gameObject.getComponentOfType(CellPosition2D.class).get().getPosition(),
                1,
                1));

        village.getGroundObjects().forEach(gameEngine::addGameObject);
        village.getGameObjects().forEach(gameEngine::addGameObject);

        gameEngine.start();

        initializeScene();
    }
    /**
     *
     */
    @Override
    public void initializeScene() {

    }

    private Set<GameObject> decodeVillage(String csvData) {
        return this.decoder.decode(csvData);
    }

    // Da implementare per mappare correttamente i tipi in sprite
    protected abstract String mapTypeToSprite(GameObject go);

    // Titolo finestra specifico
    protected abstract String getSceneTitle();
}
