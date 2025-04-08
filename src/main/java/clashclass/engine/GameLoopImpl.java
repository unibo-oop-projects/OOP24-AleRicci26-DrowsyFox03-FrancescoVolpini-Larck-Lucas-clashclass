package clashclass.engine;

/**
 * Represents a GameLoop implementation.
 */
public class GameLoopImpl implements GameLoop {
    private final float fps;
    private final float secondsBetweenTwoFrames;
    private GameScene currentScene;

    /**
     * Constructs the GameLoop.
     *
     * @param fps the desired number of frames per seconds
     */
    public GameLoopImpl(final float fps) {
        this.fps = fps;
        this.secondsBetweenTwoFrames = 1.0f / fps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            this.currentScene.updateGameObjects(1.0f);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentScene(final GameScene scene) {
        this.currentScene = scene;
    }
}
