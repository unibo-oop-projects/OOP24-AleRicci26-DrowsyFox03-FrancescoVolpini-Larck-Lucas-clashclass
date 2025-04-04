package clashclass.battle.destruction;

import clashclass.ecs.GameObject;

public interface DestructionObserver {

    void NotifyDestruction(GameObject obj);

}
