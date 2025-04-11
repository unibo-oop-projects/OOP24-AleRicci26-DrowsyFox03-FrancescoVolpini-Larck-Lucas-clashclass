package clashclass.battle.destruction;

import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;

public class EndBattleAllVillageDestroyedImpl extends AbstractComponent implements EndBattleAllVillageDestroyed {

    private boolean isFullyDestroyed;

    EndBattleAllVillageDestroyedImpl(){
        this.isFullyDestroyed=false;
    }

    @Override
    public boolean isFullyDestroyed() {
        return BattleReportController.getPercentage()==100?true:false;
    }

    @Override
    public void notifyDestruction(GameObject obj) {
        if(isFullyDestroyed()){
            //qui vanno distrutti tutti gli oggetti effettivamente
            AbstractEndBattleEvent.EndBattle();
        }
    }

}
