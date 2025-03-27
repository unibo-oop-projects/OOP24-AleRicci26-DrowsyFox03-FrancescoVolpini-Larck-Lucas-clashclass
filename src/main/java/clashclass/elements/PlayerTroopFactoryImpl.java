package clashclass.elements;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;

/**
 * Represents an implementation of TroopFactory used for player village (no battle behaviours).
 */
public class PlayerTroopFactoryImpl extends AbstractTroopFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createBarbarian(Vector2D position) {
        return super.createBarbarian(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createArcher(Vector2D position) {
        return super.createArcher(position);
    }
}
