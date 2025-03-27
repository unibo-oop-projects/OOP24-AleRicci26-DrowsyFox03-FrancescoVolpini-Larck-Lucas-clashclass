package clashclass.elements;

import clashclass.ecs.GameObject;

/**
 * Represents an implementation of TroopFactory used for player village (no battle behaviours).
 */
public class PlayerTroopFactoryImpl extends AbstractTroopFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder addBarbarianComponents(final GameObject.Builder builder) {
        return builder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder addArcherComponents(final GameObject.Builder builder) {
        return builder;
    }
}
