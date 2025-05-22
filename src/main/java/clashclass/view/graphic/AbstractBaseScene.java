package clashclass.view.graphic;

/**
 * Abstract class containing all the basic methods and variables for a BaseScene.
 */
public abstract class AbstractBaseScene implements BaseScene {
    private final Window window;
    private static final double WINDOW_WIDTH = 1920; //TODO da rendere scalabile T^T
    private static final double WINDOW_HEIGHT = 1080;

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
}
