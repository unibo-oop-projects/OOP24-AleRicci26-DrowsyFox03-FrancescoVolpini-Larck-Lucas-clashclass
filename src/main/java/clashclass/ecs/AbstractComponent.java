package clashclass.ecs;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents the base class of a Component.
 */
public abstract class AbstractComponent implements Component {
    private GameObject gameObject;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI", justification = "Intentional access")
    public final GameObject getGameObject() {
        return this.gameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI2", justification = "Intentional set")
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
}
