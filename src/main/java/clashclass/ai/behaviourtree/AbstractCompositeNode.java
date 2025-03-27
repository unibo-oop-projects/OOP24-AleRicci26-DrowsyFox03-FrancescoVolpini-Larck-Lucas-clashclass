package clashclass.ai.behaviourtree;

import java.util.List;

/**
 * Represents an abstract composite node, a node which can have children.
 */
public abstract class AbstractCompositeNode extends AbstractNode {
    private final List<AbstractNode> children;
    private int currentChildIndex;

    /**
     * Constructs the composite node.
     *
     * @param children the list of child nodes
     */
    public AbstractCompositeNode(final List<AbstractNode> children) {
        this.children = children;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.currentChildIndex = 0;
    }

    /**
     * Gets the child nodes.
     *
     * @return the child nodes
     */
    protected final List<AbstractNode> getChildren() {
        return this.children;
    }

    /**
     * Gets the current child index.
     *
     * @return the current child index
     */
    protected final int getCurrentChildIndex() {
        return this.currentChildIndex;
    }

    /**
     * Increments the current child index.
     */
    protected final void incrementCurrentChildIndex() {
        this.currentChildIndex++;
    }
}
