package clashclass.battle.troopdeath;

import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;
import clashclass.battle.endbattle.AbstractBattleEvent;

/**
 * Implementation of EndBattleAllTroopsDead interface
 * Ends the battle when all troops are dead.
 */
public class EndBattleAllTroopsDeadImpl extends AbstractComponent implements EndBattleAllTroopsDead {

    private boolean allTroopsDead;
    private int troopCount;
    private int deadTroopCount;

    /**
     * Initialize the flag of all troops dead to false
     * and set the initial troop count to 0.
     */
    public EndBattleAllTroopsDeadImpl() {
        this.allTroopsDead = false;
        this.troopCount = 0;
        this.deadTroopCount = 0;
    }

    /**
     * Check if all troops are dead.
     *
     * @return true if all troops are dead, false otherwise
     */
    public boolean isAllTroopsDead() {
        return troopCount > 0 && troopCount == deadTroopCount;
    }

    /**
     * Set the total number of troops in the battle.
     *
     * @param count the number of troops
     */
    public void setTroopCount(int count) {
        this.troopCount = count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDeath(GameObject troop) {
        deadTroopCount++;

        if (isAllTroopsDead()) {
            allTroopsDead = true;
            new AbstractBattleEvent() {
                @Override
                public void endBattle() {
                    EndBattle(troop);
                }
            }.endBattle();
        }
    }
}
