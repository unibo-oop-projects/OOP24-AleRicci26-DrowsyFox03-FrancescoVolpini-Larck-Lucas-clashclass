package clashclass.battle.endbattle;

import clashclass.battle.manager.BattleManagerController;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a {@link BattleEvent} implementation.
 */
public class BattleEventImpl implements BattleEvent {
    private final BattleManagerController battleManagerController;

    /**
     * Constructs the battle event.
     *
     * @param battleManagerController the battle manager controller
     */
    @SuppressFBWarnings(value = "EI2", justification = "Intentional set")
    public BattleEventImpl(final BattleManagerController battleManagerController) {
        this.battleManagerController = battleManagerController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endBattle() {
        this.battleManagerController.endBattle();
    }
}
