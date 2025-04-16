package clashclass.battle.destruction;

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
    EndBattleAllVillageDestroyedImpl(){
        this.isFullyDestroyed=false;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean isFullyDestroyed() {
        return BattleReportController.getPercentage()==100?true:false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDestruction(GameObject obj) {
        if(isFullyDestroyed()){
            obj.destroy();
            AbstractEndBattleEvent.EndBattle();
        }
    }
}