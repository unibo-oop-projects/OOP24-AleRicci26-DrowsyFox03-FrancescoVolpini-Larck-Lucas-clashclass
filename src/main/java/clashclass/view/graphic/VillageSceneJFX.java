package clashclass.view.graphic;

import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.engine.GameEngine;
import clashclass.engine.GameEngineImpl;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.saveload.VillageDecoder;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public abstract class VillageSceneJFX extends AbstractBaseScene{
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
        Scene scene = new Scene(root, getWindowWidth(), getWindowHeight());
        stage.setScene(scene);
        stage.setTitle(getSceneTitle());
        stage.show();

        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());

//        canvas.widthProperty().addListener((obs, oldVal, newVal) -> redraw());
//        canvas.heightProperty().addListener((obs, oldVal, newVal) -> redraw());

        final GameEngine gameEngine = new GameEngineImpl(this.getGraphics());
        this.gameObjects.forEach(gameEngine::addGameObject);

        gameEngine.start();

        initializeScene();
    }
    /**
     *
     */
    @Override
    public void initializeScene() {

    }

    protected void drawAllGameObjects() {
        for (GameObject go : gameObjects) {
            String spriteName = mapTypeToSprite(go);
            if (spriteName != null) {
                this.getGraphics().drawSprites(go, spriteName);
            } else {
                this.getGraphics().drawRectangle(go);
            }
        }
    }

    private Set<GameObject> decodeVillage(String csvData) {
        return this.decoder.decode(csvData);
    }

    // Da implementare per mappare correttamente i tipi in sprite
    protected abstract String mapTypeToSprite(GameObject go);

    // Titolo finestra specifico
    protected abstract String getSceneTitle();
}
