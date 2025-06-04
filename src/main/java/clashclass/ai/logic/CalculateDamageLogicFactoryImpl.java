package clashclass.ai.logic;

import clashclass.ecs.GameObject;
import clashclass.stats.DefenseBuildingBaseStatsComponent;
import clashclass.stats.TroopBaseStatsComponent;

public class CalculateDamageLogicFactoryImpl implements CalculateDamageLogicFactory {
    private static final class DefaultTroopsDamageLogic implements CalculateDamageLogic {
        @Override
        public int calculateDamage(GameObject actor, GameObject target) {
            return actor.getComponentOfType(TroopBaseStatsComponent.class).get().getDamage();
        }
    }

    private static final class DefaultDefensesDamageLogic implements CalculateDamageLogic {
        @Override
        public int calculateDamage(GameObject actor, GameObject target) {
            return actor.getComponentOfType(DefenseBuildingBaseStatsComponent.class).get().getDamage();
        }
    }

    @Override
    public DamageLogicComponent createForBarbarian() {
        return new DamageLogicComponent(new DefaultTroopsDamageLogic());
    }

    @Override
    public DamageLogicComponent createForArcher() {
        return new DamageLogicComponent(new DefaultTroopsDamageLogic());
    }

    @Override
    public DamageLogicComponent createForCannon() {
        return new DamageLogicComponent(new DefaultDefensesDamageLogic());
    }

    @Override
    public DamageLogicComponent createForArcherTower() {
        return new DamageLogicComponent(new DefaultDefensesDamageLogic());
    }
}
