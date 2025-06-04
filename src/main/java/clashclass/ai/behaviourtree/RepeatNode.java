package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.Blackboard;

public class RepeatNode extends AbstractBehaviourNode {
    private final AbstractBehaviourNode child;

    /**
     * Constructs the repeat node.
     *
     * @param child the child of this repeat node
     */
    public RepeatNode(final AbstractBehaviourNode child) {
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

    public void onEnter() {
        this.child.onEnter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        final var state = this.child.onUpdate(deltaTime);
        if (state == State.SUCCESS) {
            this.child.restart();
            this.child.onEnter();
        }
        return State.RUNNING;
    }

    @Override
    public void restart() {
        this.child.restart();
    }
}
