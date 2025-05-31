package clashclass.stats;

import clashclass.ecs.AbstractComponent;

public class DefenseBuildingBaseStatsComponent extends AbstractComponent {
    final int health;
    final int damage;
    final int attackSpeed;
    final int attackRange;

    public DefenseBuildingBaseStatsComponent(
            final int health,
            final int damage,
            final int attackSpeed,
            final int attackRange) {
        this.health = health;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getAttackSpeed() {
        return this.attackSpeed;
    }

    public int getAttackRange() {
        return this.attackRange;
    }
}
