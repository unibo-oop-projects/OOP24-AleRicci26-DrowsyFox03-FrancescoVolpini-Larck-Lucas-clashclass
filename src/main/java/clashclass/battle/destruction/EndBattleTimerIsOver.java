package clashclass.battle.destruction;

/**
 * This class represent one possible cause of the end of the battle.
 * In this case the end of the time window provided by the timer.
 */
public interface EndBattleTimerIsOver {

    /**
     * @return true if the time is over false otherwise.
     */
    boolean IsFinished();
}
