package clashclass.battle.battlereport;

import clashclass.battle.destruction.DestructionObserver;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;


public class VillageDestructionManager extends AbstractComponent implements DestructionObserver {
    
    private final BattleReportController battleReportController;
    

    public VillageDestructionManager(final BattleReportController battleReportController) {
        this.battleReportController = battleReportController;
    }
    

    @Override
    public void notifyDestruction(final GameObject obj) {
        // Increase the destruction percentage in the battle report
        battleReportController.increaseDestructionPercentage();

    }
}