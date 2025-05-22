package clashclass.view.graphic;

import javafx.stage.Stage;

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
}
