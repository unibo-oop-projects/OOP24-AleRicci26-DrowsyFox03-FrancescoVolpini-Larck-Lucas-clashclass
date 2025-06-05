package clashclass.battle.troopdeath;

import clashclass.battle.endbattle.AbstractBattleEvent;
import clashclass.battle.manager.BattleManagerController;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;

public class EndBattleAllTroopsDeadGameImpl extends AbstractComponent implements EndBattleAllTroopsDead{
    private final BattleManagerController battleManagerController;

    public EndBattleAllTroopsDeadGameImpl(final BattleManagerController battleManagerController) {
        this.battleManagerController = battleManagerController;
    }

    @Override
    public boolean isAllTroopsDead() {
        return this.battleManagerController.areAllTroopsDead();
    }

    @Override
    public void setTroopCount(int count) {}

    @Override
    public void notifyDeath(GameObject troop) {
        if (this.isAllTroopsDead()) {
            new AbstractBattleEvent(this.battleManagerController) {
                @Override
                public void endBattle() {
                    EndBattle(troop);
                }
            }.endBattle();
        }
    }
}
