package clashclass.battle.destruction;

import clashclass.battle.endbattle.AbstractBattleEvent;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;

/**
 * Implementation of EndBattleAllVillageDestroyed interface
 */
public class EndBattleAllVillageDestroyedImpl extends AbstractComponent implements EndBattleAllVillageDestroyed {

    private boolean isFullyDestroyed;

    /**
     * Initialize the flag of the village to not destroyed
     */
    public EndBattleAllVillageDestroyedImpl(){
        this.isFullyDestroyed=false;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean isFullyDestroyed() {
        // return battleReportController.getDestructionPercentage() >= 100.0;
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDestruction(GameObject obj) {
        if(isFullyDestroyed()){
            new AbstractBattleEvent() {
                @Override
                public void endBattle() {
                    EndBattle(obj);
                }
            }.endBattle();
        }
    }
}
