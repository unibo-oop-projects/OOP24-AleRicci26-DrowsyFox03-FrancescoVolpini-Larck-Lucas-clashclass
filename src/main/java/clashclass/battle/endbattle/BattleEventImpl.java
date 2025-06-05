package clashclass.battle.endbattle;

import clashclass.battle.manager.BattleManagerController;

public class BattleEventImpl implements BattleEvent {
    private final BattleManagerController battleManagerController;

    public BattleEventImpl(final BattleManagerController battleManagerController) {
        this.battleManagerController = battleManagerController;
    }

    @Override
    public void endBattle() {
        this.battleManagerController.endBattle();
    }
}
