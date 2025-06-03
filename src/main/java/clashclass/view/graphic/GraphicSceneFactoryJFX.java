package clashclass.view.graphic;

import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * JavaFX-based implementation of GraphicSceneFactory.
 */
public class GraphicSceneFactoryJFX implements GraphicSceneFactory {
    private final Stage stage;
    /**
     * Constructor for GraphicSceneFactory
     * @param stage the stage in which the scene is created
     */
    public GraphicSceneFactoryJFX(Stage stage) {
        this.stage = new Stage(stage.getStyle());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BaseScene createMenuScene(Window window) {
        return new MenuJFX(stage, window);
    }

    @Override
    public BaseScene createPlayerVillageScene(Window window) {
        try {
            return new PlayerVillageSceneJFX(
                    window,
                    stage,
                    Paths.get("data_villages/default_village.csv"),
                    Paths.get("data_villages/default_village.csv"));
        } catch (IOException e) {
            System.err.println("Player village file not found ");
            return null;
        }
    }
}
