package clashclass.engine;

import clashclass.view.graphic.components.GraphicComponent;
import clashclass.view.graphic.Graphic;

import java.util.Optional;
import java.util.stream.Collectors;

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
    private final Optional<Graphic> graphics;

    /**
     * Constructs the GameLoop.
     *
     * @param fps the desired number of frames per seconds
     */
    public GameLoopImpl(final Optional<Graphic> graphic, final float fps) {
        this.graphics = graphic;
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

            this.updateGameObjects();
            this.drawGameObjects();
            this.checkForDestroyedGameObjects();

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

    private void updateGameObjects() {
        this.currentScene.updateGameObjects(deltaTime);
    }

    private void drawGameObjects() {
        this.graphics.ifPresent(graphic -> graphic
                .render(this.currentScene.getGameObjects().stream()
                    .collect(Collectors.toSet()).stream()
                        .filter(x -> x.getComponentOfType(GraphicComponent.class).isPresent())
                        .map(x -> x.getComponentOfType(GraphicComponent.class).get())
                        .collect(Collectors.toUnmodifiableSet())));
    }

    private void checkForDestroyedGameObjects() {
        this.currentScene.checkForDestroyedGameObjects();
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
