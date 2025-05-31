package clashclass.elements.troops;

import clashclass.ai.behaviourtree.BehaviourTreeFactory;
import clashclass.ai.behaviourtree.BehaviourTreeTroopFactoryImpl;
import clashclass.ai.logic.CalculateDamageLogicFactory;
import clashclass.ai.logic.CalculateDamageLogicFactoryImpl;
import clashclass.ecs.GameObject;
import clashclass.stats.TroopBaseStatsComponent;

/**
 * Represents an implementation of TroopFactory used for battle.
 */
public class BattleTroopFactoryImpl extends AbstractTroopFactory {
    private final BehaviourTreeFactory behaviourTreeFactory;
    private final CalculateDamageLogicFactory damageLogicFactory;

    public BattleTroopFactoryImpl() {
        this.behaviourTreeFactory = new BehaviourTreeTroopFactoryImpl();
        this.damageLogicFactory = new CalculateDamageLogicFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalBarbarianComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100))
                .addComponent(new TroopBaseStatsComponent(100, 30, 1, 1))
                .addComponent(this.damageLogicFactory.createForBarbarian())
                .addComponent(this.behaviourTreeFactory.create());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArcherComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(70))
                .addComponent(new TroopBaseStatsComponent(70, 25, 2, 2))
                .addComponent(this.damageLogicFactory.createForArcher())
                .addComponent(this.behaviourTreeFactory.create());
    }
}
