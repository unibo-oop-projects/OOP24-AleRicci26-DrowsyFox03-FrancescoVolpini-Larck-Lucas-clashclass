package clashclass.battle.battlereport;

import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;


public class VillageDestructionManagerImpl extends AbstractComponent implements VillageDestructionManager {

    private final BattleReportController battleReportController;


    public VillageDestructionManagerImpl(final BattleReportController battleReportController) {
        this.battleReportController = battleReportController;
    }


    @Override
    public void notifyDestruction(final GameObject obj) {
        // Increase the destruction percentage in the battle report
        battleReportController.increaseDestructionPercentage();

    }
}
