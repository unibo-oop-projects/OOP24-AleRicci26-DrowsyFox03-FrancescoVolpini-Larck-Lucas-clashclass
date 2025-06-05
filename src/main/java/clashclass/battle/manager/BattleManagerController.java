package clashclass.battle.manager;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.gamestate.GameStateController;
import clashclass.village.Village;

import java.util.Set;

/**
 * Represents a scene manager for the game state 'battle'.
 */
public interface BattleManagerController extends GameStateController {
    /**
     * Ends the battle.
     */
    void endBattle();

    /**
     * Sets the current selected troop.
     *
     * @param troop the current selected troop
     */
    void setCurrentSelectedTroop(TROOP_TYPE troop);

    /**
     * Creates a new troop in the battle village.
     */
    void createTroop(Vector2D position);

    /**
     * Gets the current active (alive) troops.
     *
     * @return the current active troops
     */
    Set<GameObject> getActiveTroops();

    /**
     * Gets the battle village.
     *
     * @return the battle village
     */
    Village getBattleVillage();

    /**
     * Updates the village state (buildings) for troops.
     *
     * @param destroyedBuilding the building that has been destroyed
     */
    void updateVillageState(GameObject destroyedBuilding);

    /**
     * Updates the troops state (troops) for defense buildings.
     *
     * @param destroyedTroop the troop that has been destroyed
     */
    void updateTroopsState(GameObject destroyedTroop);

    boolean isBattleTimeFinished();

    boolean areAllTroopsDead();
}
