package clashclass.elements.buildings;

import clashclass.ecs.GameObject;

/**
 * Represents an implementation of BuildingFactory used for battle.
 */
public class BattleBuildingFactoryImpl extends AbstractBuildingFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalTownHallComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalWallComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalCannonComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArcherTowerComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalGoldStorageComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalElixirStorageComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalGoldExtractorComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalElixirExtractorComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArmyCampComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalBarracksComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(this.getComponentFactory().createHealth(100));
    }
}
