package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.Blackboard;
import clashclass.ai.behaviourtree.blackboard.BlackboardImpl;
import clashclass.ecs.AbstractComponent;

/**
 * Represents a BehaviourTree implementation.
 */
public class BehaviourTreeImpl extends AbstractComponent implements BehaviourTree {
    private final AbstractBehaviourNode rootNode;
    private final Blackboard blackboard;

    /**
     * Constructs the behaviour tree.
     *
     * @param rootNode the root node of the tree
     */
    public BehaviourTreeImpl(final AbstractBehaviourNode rootNode) {
        this.blackboard = new BlackboardImpl();
        this.rootNode = rootNode;
        this.rootNode.setBlackboard(blackboard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final float deltaTime) {
        this.rootNode.onUpdate(deltaTime);
    }
}
