package clashclass.battle.battlereport;
import clashclass.resources.ResourceManager;

/**
 * Interface for the Battle Report Model.
 * Defines the data structure and operations for battle reports.
 */
public interface BattleReportModel {
    /**
     * Get the current destruction percentage of the village.
     * @return The destruction percentage (0-100)
     */
    double getDestructionPercentage();

    /**
     * Set the destruction percentage of the village.
     * @param percentage The new destruction percentage (0-100)
     */
    void setDestructionPercentage(double percentage);

    /**
     * Increase the destruction percentage by a calculated amount.
     */
    void increaseDestructionPercentage();

    /**
     * Get the number of stars earned in the battle (0-3).
     * @return The number of stars (0-3)
     */
    int getStars();

    /**
     * Get the gold stolen during the battle.
     * @return The ResourceManager containing stolen gold
     */
    ResourceManager getStolenGold();

    /**
     * Get the elixir stolen during the battle.
     * @return The ResourceManager containing stolen elixir
     */
    ResourceManager getStolenElixir();

    /**
     * Add stolen gold resources.
     * @param gold The ResourceManager containing gold to add
     */
    void addStolenGold(ResourceManager gold);

    /**
     * Add stolen elixir resources.
     * @param elixir The ResourceManager containing elixir to add
     */
    void addStolenElixir(ResourceManager elixir);

    /**
     * Check if the Town Hall has been destroyed.
     * @return true if destroyed, false otherwise
     */
    boolean isTownHallDestroyed();

    /**
     * Set the Town Hall destruction status.
     * @param destroyed true if destroyed, false otherwise
     */
    void setTownHallDestroyed(boolean destroyed);

    /**
     * Get the total number of troops used in the battle.
     * @return The total number of troops used
     */
    int getTroopCount();

    /**
     * Set the total number of troops used in the battle.
     * @param count The total number of troops used
     */
    void setTroopCount(int count);

    /**
     * Check if the battle resulted in a victory (at least 1 star).
     * @return true if victory, false otherwise
     */
    boolean isVictory();
}