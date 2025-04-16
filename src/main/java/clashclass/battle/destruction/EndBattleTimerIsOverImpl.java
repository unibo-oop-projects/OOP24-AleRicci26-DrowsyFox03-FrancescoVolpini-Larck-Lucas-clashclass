package clashclass.battle.destruction;
import clashclass.battle.timer.Timer;
import clashclass.battle.timer.TimerImpl;

/**
 * Represent the implementation of EndBattleTimerIsOver
 */
public class EndBattleTimerIsOverImpl implements EndBattleTimerIsOver{

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
    public boolean IsFinished() {
        return timeFinished;
    }
}
