package clashclass.ai.logic;

import clashclass.ecs.GameObject;

public interface CalculateDamageLogic {
    int calculateDamage(GameObject actor, GameObject target);
}
