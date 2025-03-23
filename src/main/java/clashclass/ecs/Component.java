package clashclass.ecs;

/**
 * Represents a Component, that can be used to compose a GameObject.
 */
public interface Component {
    /**
     * Sets the GameObject that this component is attached to.
     *
     * @param gameObject the GameObject
     */
    void setGameObject(GameObject gameObject);

    /**
     * Initializes the Component.
     */
    void initialize();

    /**
     * Updates the Component once per frame.
     *
     * @param deltaTime time elapsed between the previous and the current frame.
     */
    void update(float deltaTime);
}
