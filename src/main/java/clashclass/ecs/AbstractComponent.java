package clashclass.ecs;

/**
 * Represents the base class of a Component.
 */
public abstract class AbstractComponent implements Component {
    private GameObject gameObject;

    /**
     * Gets the GameObject that this Component is attached to.
     *
     * @return the GameObject
     */
    protected final GameObject getGameObject() {
        return this.gameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setGameObject(final GameObject gameObject) {
        if (this.gameObject == null) {
            this.gameObject = gameObject;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final float deltaTime) {

    }
}
