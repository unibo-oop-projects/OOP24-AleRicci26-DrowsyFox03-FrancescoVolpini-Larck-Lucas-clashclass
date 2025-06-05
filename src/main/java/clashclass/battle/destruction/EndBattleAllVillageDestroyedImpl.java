package clashclass.battle.destruction;

import clashclass.battle.battlereport.BattleReportController;
import clashclass.battle.endbattle.AbstractBattleEvent;
import clashclass.battle.endbattle.BattleEvent;
import clashclass.battle.manager.BattleManagerController;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;

/**
 * Implementation of EndBattleAllVillageDestroyed interface
 */
public class EndBattleAllVillageDestroyedImpl extends AbstractComponent implements EndBattleAllVillageDestroyed {
    private final BattleManagerController battleManagerController;
    private final BattleReportController battleReportController;
    private boolean isFullyDestroyed;

    /**
     * Initialize the flag of the village to not destroyed
     */
    public EndBattleAllVillageDestroyedImpl(
            final BattleManagerController battleManagerController,
            final BattleReportController battleReportController) {
        this.battleManagerController = battleManagerController;
        this.battleReportController = battleReportController;
        this.isFullyDestroyed=false;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean isFullyDestroyed() {
        return battleReportController.getDestructionPercentage() >= 100.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDestruction(GameObject obj) {
        if(isFullyDestroyed()){
            new AbstractBattleEvent(this.battleManagerController) {
                @Override
                public void endBattle() {
                    EndBattle(obj);
                }
            }.endBattle();
        }
    }
}
