package clashclass.engine;

import clashclass.ecs.GameObject;
import clashclass.ecs.UpdateProvider;

import java.util.Collections;
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
    public final synchronized void traverseGameObjects(final Consumer<GameObject> consumer) {
        this.gameObjects.forEach(consumer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized void updateGameObjects(final float deltaTime) {
        this.gameObjectsToUpdate.forEach(gameObject -> gameObject.update(deltaTime));
    }

    @Override
    public synchronized void checkForDestroyedGameObjects() {
        this.gameObjects.stream()
                .filter(GameObject::isMarkedAsDestroyed)
                .filter(gameObject -> gameObject instanceof UpdateProvider)
                .forEach(gameObject -> this.gameObjectsToUpdate.remove((UpdateProvider) gameObject));
        this.gameObjects.removeIf(GameObject::isMarkedAsDestroyed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized void addGameObject(final GameObject gameObject) {
        this.gameObjects.add(gameObject);
        gameObject.setScene(this);

        if (gameObject instanceof UpdateProvider) {
            this.gameObjectsToUpdate.add((UpdateProvider) gameObject);
        }
    }

    @Override
    public synchronized Set<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    @Override
    public synchronized Set<GameObject> getGameObjectsCopy() {
        return new HashSet<>(this.getGameObjects());
    }
}
