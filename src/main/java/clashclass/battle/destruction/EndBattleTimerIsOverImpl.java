package clashclass.battle.destruction;

import clashclass.battle.battlereport.BattleReportController;
import clashclass.battle.endbattle.AbstractBattleEvent;
import clashclass.battle.manager.BattleManagerController;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;

/**
 * Represent the implementation of EndBattleTimerIsOver
 */
public class EndBattleTimerIsOverImpl extends AbstractComponent implements EndBattleTimerIsOver {
    private final BattleManagerController battleManagerController;

    /**
     * initialize the timer flag to not finished
     */
    public EndBattleTimerIsOverImpl(final BattleManagerController battleManagerController) {
        this.battleManagerController = battleManagerController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinished() {
        return this.battleManagerController.isBattleTimeFinished();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDestruction(GameObject obj) {
        if(this.isFinished()) {
            // Use AbstractBattleEvent to end the battle
            new AbstractBattleEvent(this.battleManagerController) {
                @Override
                public void endBattle() {
                    EndBattle(obj);
                }
            }.endBattle();
        }
    }
}
