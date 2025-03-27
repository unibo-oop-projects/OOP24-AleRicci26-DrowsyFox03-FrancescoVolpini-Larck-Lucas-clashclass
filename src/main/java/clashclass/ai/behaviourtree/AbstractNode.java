package clashclass.ai.behaviourtree;

/**
 * Represents an abstract implementation of Node.
 */
public abstract class AbstractNode implements Node {
    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        return State.SUCCESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onExit() {

    }
}
