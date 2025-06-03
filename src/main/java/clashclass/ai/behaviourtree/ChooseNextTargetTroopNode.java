package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.behaviourtree.blackboard.wrappers.GameObjectListWrapper;
import clashclass.ai.logic.ChooseTargetLogic;
import clashclass.commons.Transform2D;
import clashclass.ecs.GameObject;
import clashclass.stats.DefenseBuildingBaseStatsComponent;

public class ChooseNextTargetTroopNode extends AbstractBehaviourNode {
    private final ChooseTargetLogic chooseTargetLogic;
    private BlackboardProperty<GameObjectListWrapper> troopsProp;
    private BlackboardProperty<GameObject> actorProp;
    private BlackboardProperty<GameObject> targetProp;

    public ChooseNextTargetTroopNode(final ChooseTargetLogic chooseTargetLogic) {
        this.chooseTargetLogic = chooseTargetLogic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.actorProp = this.getBlackboard().getProperty("actor", GameObject.class);
        this.targetProp = this.getBlackboard().getProperty("target", GameObject.class);
        this.troopsProp = this.getBlackboard().getProperty("troops", GameObjectListWrapper.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        final var actor = this.actorProp.getValue();
        final var troops = this.troopsProp.getValue().list();

        final var stats = actor.getComponentOfType(DefenseBuildingBaseStatsComponent.class)
                .orElseThrow(() -> new RuntimeException("No DefenseBuildingBaseStatsComponent found"));

        final var nextTarget = chooseTargetLogic.chooseTarget(actor, troops);
        final var actorPosition = actor.getComponentOfType(Transform2D.class).get().getPosition();
        final var targetPosition = nextTarget.getComponentOfType(Transform2D.class).get().getPosition();

        if (actorPosition.distance(targetPosition) > stats.getAttackRange()) {
            return State.RUNNING;
        }

        this.targetProp.setValue(nextTarget);
        return State.SUCCESS;
    }
}
