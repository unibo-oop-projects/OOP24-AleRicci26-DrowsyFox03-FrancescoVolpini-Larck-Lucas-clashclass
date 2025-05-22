package clashclass.battle.battlereport;

import clashclass.ecs.GameObject;

/**
 * Interface for the VillageDestructionManager.
 * Handles the destruction of village objects and updates battle reports.
 */
public interface VillageDestructionManager {
    /**
     * Destroys a game object and updates the battle report accordingly.
     *
     * @param obj The game object to destroy
     * @param battleReport The battle report to update
     */
    void destroyObject(GameObject obj, BattleReportModel battleReport);

    /**
     * Checks if a game object can be destroyed.
     *
     * @param obj The game object to check
     * @return true if the object can be destroyed, false otherwise
     */
    boolean canDestroy(GameObject obj);
}