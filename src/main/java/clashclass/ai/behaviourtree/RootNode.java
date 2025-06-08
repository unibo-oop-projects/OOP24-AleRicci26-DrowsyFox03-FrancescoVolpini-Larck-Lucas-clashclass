package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.Blackboard;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a root node, where the {@link BehaviourTree} starts.
 */
public class RootNode extends AbstractBehaviourNode {
    private final AbstractBehaviourNode child;

    /**
     * Constructs the root node.
     *
     * @param child the child of this root node
     */
    @SuppressFBWarnings(value = "EI2", justification = "Intentional set")
    public RootNode(final AbstractBehaviourNode child) {
        this.child = child;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlackboard(final Blackboard blackboard) {
        super.setBlackboard(blackboard);
        this.child.setBlackboard(blackboard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.child.onEnter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        return this.child.onUpdate(deltaTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restart() {
        this.child.restart();
    }
}
