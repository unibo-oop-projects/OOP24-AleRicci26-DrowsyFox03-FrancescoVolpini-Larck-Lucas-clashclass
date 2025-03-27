package clashclass.elements;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.ecs.GameObjectImpl;

/**
 * Represents an Abstract Factory for the creation of the troops.
 */
public abstract class AbstractTroopFactory implements TroopFactory {
    private final ComponentFactory componentFactory = new ComponentFactoryImpl();

    protected final ComponentFactory getComponentFactory() {
        return this.componentFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createBarbarian(Vector2D position) {
        return new GameObjectImpl.BuilderImpl()
                .addComponent(this.componentFactory.createTransform2D(position))
                .addComponent(this.componentFactory.createHealth(100))
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createArcher(Vector2D position) {
        return new GameObjectImpl.BuilderImpl()
                .addComponent(this.componentFactory.createTransform2D(position))
                .addComponent(this.componentFactory.createHealth(70))
                .build();
    }
}
