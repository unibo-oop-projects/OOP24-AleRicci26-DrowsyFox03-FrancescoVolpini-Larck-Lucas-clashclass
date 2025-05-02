package clashclass.battle.troopdeath;

import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;
import clashclass.battle.endbattle.AbstractBattleEvent;


public class EndBattleAllTroopsDeadImpl extends AbstractComponent implements EndBattleAllTroopsDead {

    private boolean allTroopsDead;
    private int troopCount;
    private int deadTroopCount;


    public EndBattleAllTroopsDeadImpl() {
        this.allTroopsDead = false;
        this.troopCount = 0;
        this.deadTroopCount = 0;
    }


    public boolean isAllTroopsDead() {
        return troopCount > 0 && troopCount == deadTroopCount;
    }


    public void setTroopCount(int count) {
        this.troopCount = count;
    }


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
