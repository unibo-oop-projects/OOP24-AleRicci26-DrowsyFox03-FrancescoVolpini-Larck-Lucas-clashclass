package clashclass.ecs;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Represents a GameScene implementation.
 */
public class GameSceneImpl implements GameScene {
    private final Set<GameObject> gameObjects;
    private final Set<UpdateProvider> gameObjectsToUpdate;

    /**
     * Constructs the game scene.
     */
    public GameSceneImpl() {
        this.gameObjects = new HashSet<>();
        this.gameObjectsToUpdate = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void traverseGameObjects(final Consumer<GameObject> consumer) {
        this.gameObjects.forEach(consumer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateGameObjects(final float deltaTime) {
        this.gameObjectsToUpdate.forEach(gameObject -> gameObject.update(deltaTime));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addGameObject(final GameObject gameObject) {
        this.gameObjects.add(gameObject);

        if (gameObject instanceof UpdateProvider) {
            this.gameObjectsToUpdate.add((UpdateProvider) gameObject);
        }
    }
}
