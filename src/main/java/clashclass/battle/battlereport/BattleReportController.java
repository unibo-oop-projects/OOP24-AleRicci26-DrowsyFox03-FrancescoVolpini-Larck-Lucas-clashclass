package clashclass.battle.battlereport;

import clashclass.resources.ResourceManager;

/**
 * Controller interface for battle report management
 */
public interface BattleReportController {
    /**
     * Updates destruction percentage
     * @param percentage New destruction percentage
     */
    void updateDestructionPercentage(double percentage);

    /**
     * Increases destruction percentage based on building destruction
     */
    void increaseDestructionPercentage();

    /**
     * Adds stolen gold to report
     * @param gold Amount of gold stolen
     */
    void addStolenGold(ResourceManager gold);

    /**
     * Adds stolen elixir to report
     * @param elixir Amount of elixir stolen
     */
    void addStolenElixir(ResourceManager elixir);

    /**
     * Updates town hall destruction status
     * @param destroyed Whether town hall was destroyed
     */
    void setTownHallDestroyed(boolean destroyed);

    /**
     * Updates troop count
     * @param count Number of troops used
     */
    void updateTroopCount(int count);

    /**
     * @return Current battle report model
     */
    BattleReportModel getModel();
}