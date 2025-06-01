package clashclass.elements.buildings;

import clashclass.ai.behaviourtree.BehaviourTreeDefenseBuildingFactoryImpl;
import clashclass.ai.behaviourtree.BehaviourTreeFactory;
import clashclass.ai.logic.CalculateDamageLogicFactory;
import clashclass.ai.logic.CalculateDamageLogicFactoryImpl;
import clashclass.commons.BuildingTypeComponentImpl;
import clashclass.ecs.GameObject;
import clashclass.stats.DefenseBuildingBaseStatsComponent;

/**
 * Represents an implementation of BuildingFactory used for battle.
 */
public class BattleBuildingFactoryImpl extends AbstractBuildingFactory {
    private final BehaviourTreeFactory behaviourTreeFactory;
    private final CalculateDamageLogicFactory damageLogicFactory;

    public BattleBuildingFactoryImpl() {
        this.behaviourTreeFactory = new BehaviourTreeDefenseBuildingFactoryImpl();
        this.damageLogicFactory = new CalculateDamageLogicFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalTownHallComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.TOWN_HALL))
                .addComponent(this.getComponentFactory().createHealth(100))
                .ad;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalWallComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.WALL))
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalCannonComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.CANNON))
                .addComponent(this.getComponentFactory().createHealth(100))
                .addComponent(new DefenseBuildingBaseStatsComponent(100, 20, 1, 5))
                .addComponent(this.damageLogicFactory.createForCannon())
                .addComponent(this.behaviourTreeFactory.create());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArcherTowerComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.ARCHER_TOWER))
                .addComponent(this.getComponentFactory().createHealth(80))
                .addComponent(new DefenseBuildingBaseStatsComponent(80, 15, 2, 9))
                .addComponent(this.damageLogicFactory.createForCannon())
                .addComponent(this.behaviourTreeFactory.create());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalGoldStorageComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.GOLD_STORAGE))
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalElixirStorageComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.ELIXIR_STORAGE))
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalGoldExtractorComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.GOLD_EXTRACTOR))
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalElixirExtractorComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.ELIXIR_EXTRACTOR))
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArmyCampComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.ARMY_CAMP))
                .addComponent(this.getComponentFactory().createHealth(100));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalBarracksComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.BARRACKS))
                .addComponent(this.getComponentFactory().createHealth(100));
    }
}
