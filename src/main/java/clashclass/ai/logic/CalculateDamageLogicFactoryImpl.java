package clashclass.ai.logic;

import clashclass.ecs.GameObject;
import clashclass.stats.TroopBaseStatsComponent;

public class CalculateDamageLogicFactoryImpl implements CalculateDamageLogicFactory {
    private static final class DefaultDamageLogic implements CalculateDamageLogic {
        @Override
        public int calculateDamage(GameObject actor, GameObject target) {
            return actor.getComponentOfType(TroopBaseStatsComponent.class).get().getDamage();
        }
    }

    @Override
    public DamageLogicComponent createForBarbarian() {
        return new DamageLogicComponent(new DefaultDamageLogic());
    }

    @Override
    public DamageLogicComponent createForArcher() {
        return new DamageLogicComponent(new DefaultDamageLogic());
    }

    @Override
    public DamageLogicComponent createForCannon() {
        return new DamageLogicComponent(new DefaultDamageLogic());
    }

    @Override
    public DamageLogicComponent createForArcherTower() {
        return new DamageLogicComponent(new DefaultDamageLogic());
    }
}
