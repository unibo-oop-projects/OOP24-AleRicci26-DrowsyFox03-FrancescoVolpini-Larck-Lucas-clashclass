package clashclass.battle.manager;

import clashclass.battle.battlereport.BattleReportModel;
import clashclass.battle.battlereport.BattleReportView;
import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.gamestate.GameStateManager;
import clashclass.village.Village;

import java.util.Set;

/**
 * Represents the Model-part of the battle manager.
 */
public interface BattleManagerModel {
    /**
     * Sets the game state manager
     * @param gameStateManager the game state manager
     */
    void setGameStateManager(GameStateManager gameStateManager);

    /**
     * Sets the current selected troop.
     *
     * @param troopType the current selected troop
     */
    void setCurrentSelectedTroop(TROOP_TYPE troopType);

    /**
     * Gets the game state manager.
     *
     * @return the game state manager
     */
    GameStateManager getGameStateManager();

    /**
     * Gets the player village.
     *
     * @return the player village
     */
    Village getPlayerVillage();

    /**
     * Gets the battle village.
     *
     * @return the battle village
     */
    Village getBattleVillage();

    /**
     * Gets the current selected troop.
     *
     * @return the current selected troop
     */
    TROOP_TYPE getCurrentSelectedTroop();

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
     * Sets the controller reference.
     *
     * @param controller the controller
     */
    void setController(BattleManagerController controller);

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

    /**
     * Clears the scene.
     */
    void clearScene();

    void buildBattleReport(BattleReportView view);

    boolean isBattleStarted();

    boolean isBattleTimeFinished();

    boolean areAllTroopsDead();
}
