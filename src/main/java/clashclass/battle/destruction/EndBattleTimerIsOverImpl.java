package clashclass.battle.destruction;
import clashclass.ecs.Component;
import clashclass.ecs.GameObject;
import clashclass.ecs.AbstractComponent;

/**
 * Represent the implementation of EndBattleTimerIsOver
 */
public class EndBattleTimerIsOverImpl extends AbstractComponent implements EndBattleTimerIsOver{

    /**
     * initialize the timer flag to not finished
     */
    private final boolean timeFinished;
    EndBattleTimerIsOverImpl(){
        timeFinished=false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinished() {
        return timeFinished;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDestruction(GameObject obj) {
        if(isFinished()){
            //AbstractEndBattleEvent.EndBattle();
            obj.destroy();
        }
    }
}
