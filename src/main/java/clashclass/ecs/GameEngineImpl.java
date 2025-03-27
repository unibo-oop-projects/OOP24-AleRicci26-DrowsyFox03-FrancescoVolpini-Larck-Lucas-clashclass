package clashclass.ecs;

/**
 * Represents a GameEngine implementation.
 */
public class GameEngineImpl implements GameEngine {
    private final GameScene currentScene;
    private boolean isRunning;

    /**
     * Constructs the game engine.
     */
    public GameEngineImpl() {
        this.currentScene = new GameSceneImpl();
        this.isRunning = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start() {
        this.isRunning = true;

        while (this.isRunning) {
            this.currentScene.updateGameObjects(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void stop() {
        this.isRunning = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addGameObject(final GameObject gameObject) {
        this.currentScene.addGameObject(gameObject);
    }
}
