package clashclass.stats;

import clashclass.ecs.AbstractComponent;

public class TroopBaseStatsComponent extends AbstractComponent {
    final int health;
    final int damage;
    final int attackSpeed;
    final int movementSpeed;

    public TroopBaseStatsComponent(
            final int health,
            final int damage,
            final int attackSpeed,
            final int movementSpeed) {
        this.health = health;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.movementSpeed = movementSpeed;
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

    public int getMovementSpeed() {
        return this.movementSpeed;
    }
}
