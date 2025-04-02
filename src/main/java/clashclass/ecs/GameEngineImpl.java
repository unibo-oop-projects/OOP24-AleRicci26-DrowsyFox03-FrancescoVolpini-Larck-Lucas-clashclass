package clashclass.ecs;

/**
 * Represents a GameEngine implementation.
 */
public class GameEngineImpl implements GameEngine {
    private final GameScene currentScene;
    private final GameLoop gameLoop;
    private final Thread gameLoopThread;

    /**
     * Constructs the game engine.
     */
    public GameEngineImpl() {
        this.currentScene = new GameSceneImpl();
        this.gameLoop = new GameLoopImpl(60.0f);
        this.gameLoop.setCurrentScene(this.currentScene);
        this.gameLoopThread = new Thread(this.gameLoop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start() {
        this.gameLoopThread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void stop() {
        this.gameLoopThread.interrupt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addGameObject(final GameObject gameObject) {
        this.currentScene.addGameObject(gameObject);
    }
}
