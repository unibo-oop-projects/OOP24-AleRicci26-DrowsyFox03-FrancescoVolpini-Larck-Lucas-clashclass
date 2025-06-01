package clashclass.engine;

/**
 * Represents a GameLoop implementation.
 */
public class GameLoopImpl implements GameLoop {
    private final float fps;
    private final float secondsBetweenTwoFrames;
    private long lastTime;
    private float deltaTime;
    private long sleepTime;
    private GameScene currentScene;

    /**
     * Constructs the GameLoop.
     *
     * @param fps the desired number of frames per seconds
     */
    public GameLoopImpl(final float fps) {
        this.fps = fps;
        this.secondsBetweenTwoFrames = 1.0f / fps;
        this.lastTime = 0;
        this.deltaTime = 0.0f;
        this.sleepTime = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        this.lastTime = System.nanoTime();

        while (!Thread.currentThread().isInterrupted()) {
            this.calculateDeltaTime();
            this.currentScene.updateGameObjects(deltaTime);
            this.calculateSleepTime();

            if (this.sleepTime > 0) {
                try {
                    Thread.currentThread().sleep(sleepTime / 1_000_000, (int) (sleepTime % 1_000_000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentScene(final GameScene scene) {
        this.currentScene = scene;
    }

    private void calculateDeltaTime() {
        long currentTime = System.nanoTime();
        this.deltaTime = (currentTime - lastTime) / 1_000_000_000.0f;
        lastTime = currentTime;
    }

    private void calculateSleepTime() {
        long frameTimeNano = (long) (secondsBetweenTwoFrames * 1_000_000_000);
        long elapsedTime = System.nanoTime() - this.lastTime;
        this.sleepTime = frameTimeNano - elapsedTime;
    }
}
