package clashclass.battle.destruction;

import clashclass.ecs.GameObject;

public interface DestructionObserver {

    /**
     * Notify the destruction of a GameObject
     * @param obj the object that has been destroyed
     */
    void NotifyDestruction(GameObject obj);

}
