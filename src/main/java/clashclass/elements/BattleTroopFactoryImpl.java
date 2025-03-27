package clashclass.elements;

import clashclass.ecs.GameObject;

/**
 * Represents an implementation of TroopFactory used for battle.
 */
public class BattleTroopFactoryImpl extends AbstractTroopFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder addBarbarianComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder addArcherComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(70));
    }
}
