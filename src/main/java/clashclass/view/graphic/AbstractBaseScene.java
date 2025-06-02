package clashclass.view.graphic;

import clashclass.commons.GameConstants;
import clashclass.engine.GameEngine;

/**
 * Abstract class containing all the basic methods and variables for a BaseScene.
 */
public abstract class AbstractBaseScene implements BaseScene {
    private final Window window;
    private static final double WINDOW_WIDTH = GameConstants.SCREEN_WIDTH;
    private static final double WINDOW_HEIGHT = GameConstants.SCREEN_HEIGHT;
    private Graphic graphics;
    private GameEngine gameEngine;

    protected AbstractBaseScene(final Window window) {
        this.window = window;
    }

    /**
     * @return the window
     */
    public Window getWindow() {
        return window;
    }

    /**
     * @return the width of the window
     */
    public final double getWindowWidth() {
        return WINDOW_WIDTH;
    }

    /**
     * @return the height of the window
     */
    public final double getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    protected Graphic getGraphics() {
        return graphics;
    }

    protected void setGraphics(final Graphic graphics) {
        this.graphics = graphics;
    }
}
