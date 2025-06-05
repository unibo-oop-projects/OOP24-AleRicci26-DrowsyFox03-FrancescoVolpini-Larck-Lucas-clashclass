package clashclass.battle.manager;

/**
 * Represents the View-part of the battle manager.
 */
public interface BattleManagerView {
    /**
     * Sets the controller reference.
     *
     * @param controller the controller
     */
    void setController(BattleManagerController controller);

    /**
     * Updates the army camp troops.
     *
     * @param model the model reference
     */
    void setArmyCampTroops(BattleManagerModel model);

    /**
     * Updates the army troops count.
     *
     * @param model the model reference
     */
    void updateArmyCampTroopsCount(BattleManagerModel model);

    /**
     * Clears the scene.
     */
    void clearScene();
}
