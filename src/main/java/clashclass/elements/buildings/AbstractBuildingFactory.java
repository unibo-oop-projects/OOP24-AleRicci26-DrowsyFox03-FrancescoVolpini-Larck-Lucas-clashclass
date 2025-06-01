package clashclass.elements.buildings;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.ecs.GameObjectImpl;
import clashclass.ecs.ImageRendererImpl;
import clashclass.elements.ComponentFactory;
import clashclass.elements.ComponentFactoryImpl;

import java.util.function.Function;

/**
 * Represents an Abstract Factory for the creation of the building.
 */
public abstract class AbstractBuildingFactory implements BuildingFactory {
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
     * Helper method to create a base GameObject and let the child class take care of GameObject specialization.
     *
     * @param additionalBuilder the additional builder defined in the child class
     * @param mainBuilder the main builder defined in this class
     *
     * @return the final GameObject
     */
    private GameObject createWithFactoryMethod(
            final Function<GameObject.Builder, GameObject.Builder> additionalBuilder,
            final Function<GameObject.Builder, GameObject.Builder> mainBuilder) {
        return additionalBuilder.apply(mainBuilder.apply(new GameObjectImpl.BuilderImpl())).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createTownHall(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalTownHallComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(position))
                        .addComponent(new ImageRendererImpl("town-hall")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createWall(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalWallComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(position))
                        .addComponent(new ImageRendererImpl("wall")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createCannon(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalCannonComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(position))
                        .addComponent(new ImageRendererImpl("cannon")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createArcherTower(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalArcherTowerComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(position))
                        .addComponent(new ImageRendererImpl("archer-tower")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createGoldStorage(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalGoldStorageComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(position))
                        .addComponent(new ImageRendererImpl("gold-storage")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createElixirStorage(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalElixirStorageComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(position))
                        .addComponent(new ImageRendererImpl("elisir-storage")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createGoldExtractor(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalGoldExtractorComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(position))
                        .addComponent(new ImageRendererImpl("gold-extractor")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createElixirExtractor(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalElixirExtractorComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(position))
                        .addComponent(new ImageRendererImpl("elisir-extractor")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createArmyCamp(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalArmyCampComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(position))
                        .addComponent(new ImageRendererImpl("campfire")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createBarracks(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalBarracksComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(position))
                        .addComponent(new ImageRendererImpl("barracks")));
    }

    /**
     * Completes the creation of a town hall GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalTownHallComponents(GameObject.Builder builder);

    /**
     * Completes the creation of a wall GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalWallComponents(GameObject.Builder builder);

    /**
     * Completes the creation of a cannon GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalCannonComponents(GameObject.Builder builder);

    /**
     * Completes the creation of an archer tower GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalArcherTowerComponents(GameObject.Builder builder);

    /**
     * Completes the creation of a gold storage GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalGoldStorageComponents(GameObject.Builder builder);

    /**
     * Completes the creation of an elixir storage GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalElixirStorageComponents(GameObject.Builder builder);

    /**
     * Completes the creation of a gold extractor GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalGoldExtractorComponents(GameObject.Builder builder);

    /**
     * Completes the creation of an elixir extractor GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalElixirExtractorComponents(GameObject.Builder builder);

    /**
     * Completes the creation of an army camp GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalArmyCampComponents(GameObject.Builder builder);

    /**
     * Completes the creation of the barracks GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalBarracksComponents(GameObject.Builder builder);
}
