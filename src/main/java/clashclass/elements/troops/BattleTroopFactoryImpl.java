package clashclass.elements.troops;

import clashclass.ecs.GameObject;

/**
 * Represents an implementation of TroopFactory used for battle.
 */
public class BattleTroopFactoryImpl extends AbstractTroopFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalBarbarianComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
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
