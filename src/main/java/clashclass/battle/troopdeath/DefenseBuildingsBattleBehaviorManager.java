package clashclass.battle.troopdeath;

import clashclass.battle.manager.BattleManagerController;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a {@link TroopDeathObserver} implementation for updating the defense buildings' AI.
 */
public class DefenseBuildingsBattleBehaviorManager extends AbstractComponent implements TroopDeathObserver {
    private final BattleManagerController battleManagerController;

    /**
     * Constructs the observer.
     *
     * @param battleManagerController the battle manager controller
     */
    @SuppressFBWarnings(value = "EI2", justification = "Intentional set")
    public DefenseBuildingsBattleBehaviorManager(final BattleManagerController battleManagerController) {
        this.battleManagerController = battleManagerController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDeath(final GameObject destroyedTroop) {
        this.battleManagerController.updateTroopsState(destroyedTroop);
    }
}
