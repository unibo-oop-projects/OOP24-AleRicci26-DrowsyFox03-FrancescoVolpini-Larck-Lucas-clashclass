package clashclass.elements;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.ecs.GameObjectImpl;

/**
 * Represents an Abstract Factory for the creation of the troops.
 */
public abstract class AbstractTroopFactory implements TroopFactory {
    private final ComponentFactory componentFactory = new ComponentFactoryImpl();

    /**
     * Gets the component factory.
     *
     * @return the component factory
     */
    protected final ComponentFactory getComponentFactory() {
        return this.componentFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createBarbarian(final Vector2D position) {
        return this.addBarbarianComponents(new GameObjectImpl.BuilderImpl()
                .addComponent(this.componentFactory.createTransform2D(position)))
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createArcher(final Vector2D position) {
        return this.addArcherComponents(new GameObjectImpl.BuilderImpl()
                .addComponent(this.componentFactory.createTransform2D(position)))
                .build();
    }

    /**
     * Completes the creation of a barbarian GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder addBarbarianComponents(GameObject.Builder builder);

    /**
     * Completes the creation of an archer GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder addArcherComponents(GameObject.Builder builder);
}
