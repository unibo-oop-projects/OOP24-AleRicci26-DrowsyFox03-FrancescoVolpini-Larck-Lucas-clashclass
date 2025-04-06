package clashclass.ai.behaviourtree;

import clashclass.ecs.AbstractComponent;

/**
 * Represents a BehaviourTree implementation.
 */
public class BehaviourTreeImpl extends AbstractComponent implements BehaviourTree {
    private final AbstractBehaviourNode rootNode;

    /**
     * Constructs the behaviour tree.
     *
     * @param rootNode the root node of the tree
     */
    public BehaviourTreeImpl(final AbstractBehaviourNode rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final float deltaTime) {
        this.rootNode.onUpdate(deltaTime);
    }
}
