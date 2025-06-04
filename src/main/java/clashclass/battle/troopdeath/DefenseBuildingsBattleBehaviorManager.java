package clashclass.battle.troopdeath;

import clashclass.battle.manager.BattleManagerController;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;

public class DefenseBuildingsBattleBehaviorManager extends AbstractComponent implements TroopDeathObserver{
    private final BattleManagerController battleManagerController;

    public DefenseBuildingsBattleBehaviorManager(BattleManagerController battleManagerController) {
        this.battleManagerController = battleManagerController;
    }

    @Override
    public void notifyDeath(GameObject destroyedTroop) {
        this.battleManagerController.updateTroopsState(destroyedTroop);
    }
}
