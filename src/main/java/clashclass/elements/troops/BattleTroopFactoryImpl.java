package clashclass.elements.troops;

import clashclass.ai.behaviourtree.BehaviourTreeFactory;
import clashclass.ai.behaviourtree.BehaviourTreeFactoryImpl;
import clashclass.ecs.GameObject;

/**
 * Represents an implementation of TroopFactory used for battle.
 */
public class BattleTroopFactoryImpl extends AbstractTroopFactory {
    private final BehaviourTreeFactory behaviourTreeFactory;

    public BattleTroopFactoryImpl() {
        this.behaviourTreeFactory = new BehaviourTreeFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalBarbarianComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100))
                .addComponent(this.behaviourTreeFactory.create());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArcherComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(70));
    }
}
