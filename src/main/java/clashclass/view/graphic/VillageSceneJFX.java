package clashclass.view.graphic;

import clashclass.ecs.GameObject;
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
    protected final Set<GameObject> gameObjects;
    protected final GraphicJavaFXImpl graphic;
    protected final GraphicsContext gc;

    public VillageSceneJFX(Window window, Stage stage, Path csvPath) throws IOException {
        super(window);

        String csvData = Files.readString(csvPath);
        this.gameObjects = decodeVillage(csvData);

        Canvas canvas = new Canvas(getWindowWidth(), getWindowHeight());
        this.gc = canvas.getGraphicsContext2D();
        this.graphic = new GraphicJavaFXImpl(gc, getWindowWidth(), getWindowHeight());

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, getWindowWidth(), getWindowHeight());
        stage.setScene(scene);
        stage.setTitle(getSceneTitle());
        stage.show();

        initializeScene();
    }
    /**
     *
     */
    @Override
    public void initializeScene() {
        drawAllGameObjects();
    }
    protected void drawAllGameObjects() {
        for (GameObject go : gameObjects) {
            String spriteName = mapTypeToSprite(go);
            if (spriteName != null) {
                graphic.drawSprites(go, spriteName);
            } else {
                graphic.drawRectangle(go);
            }
        }
    }

    // Da implementare per scegliere la factory e decodifica corretta
    protected abstract Set<GameObject> decodeVillage(String csvData);

    // Da implementare per mappare correttamente i tipi in sprite
    protected abstract String mapTypeToSprite(GameObject go);

    // Titolo finestra specifico
    protected abstract String getSceneTitle();
}
