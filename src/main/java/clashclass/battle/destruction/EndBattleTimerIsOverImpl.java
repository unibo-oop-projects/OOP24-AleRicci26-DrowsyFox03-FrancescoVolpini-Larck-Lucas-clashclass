package clashclass.battle.destruction;

import clashclass.battle.endbattle.AbstractBattleEvent;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;

/**
 * Represent the implementation of EndBattleTimerIsOver
 */
public class EndBattleTimerIsOverImpl extends AbstractComponent implements EndBattleTimerIsOver{

    /**
     * initialize the timer flag to not finished
     */
    private final boolean timeFinished;
    public EndBattleTimerIsOverImpl(){
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
            // Use AbstractBattleEvent to end the battle
            new AbstractBattleEvent() {
                @Override
                public void endBattle() {
                    EndBattle(obj);
                }
            }.endBattle();
        }
    }
}
