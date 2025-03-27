package clashclass.ai;

import java.util.List;

/**
 * Represents a sequence node, which executes all the nodes until the first failure.
 */
public class SequenceNode extends AbstractCompositeNode {
    /**
     * Constructs the sequence node.
     *
     * @param children the list of child nodes
     */
    public SequenceNode(final List<AbstractNode> children) {
        super(children);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        if (this.getCurrentChildIndex() == this.getChildren().size()) {
            return State.SUCCESS;
        }

        final var childState = this.getChildren().get(this.getCurrentChildIndex())
                .onUpdate(deltaTime);

        if (childState == State.FAILURE) {
            return State.FAILURE;
        }

        if (childState == State.SUCCESS) {
            this.getChildren().get(this.getCurrentChildIndex()).onExit();
            this.incrementCurrentChildIndex();
            this.getChildren().get(this.getCurrentChildIndex()).onEnter();
        }
        return State.RUNNING;
    }
}
