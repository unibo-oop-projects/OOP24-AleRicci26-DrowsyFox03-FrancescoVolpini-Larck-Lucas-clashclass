package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.behaviourtree.blackboard.wrappers.GameObjectListWrapper;
import clashclass.ai.logic.ChooseTargetLogic;
import clashclass.ecs.GameObject;

public class ChooseNextTargetNode extends AbstractBehaviourNode {
    private final ChooseTargetLogic chooseTargetLogic;
    private final GameObject actor;
    private BlackboardProperty<GameObjectListWrapper> potentialTargetsProp;
    private BlackboardProperty<GameObject> targetProp;

    public ChooseNextTargetNode(final GameObject actor, final ChooseTargetLogic chooseTargetLogic) {
        this.actor = actor;
        this.chooseTargetLogic = chooseTargetLogic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.potentialTargetsProp = this.getBlackboard().getProperty("potentialTargets", GameObjectListWrapper.class);
        this.targetProp = this.getBlackboard().getProperty("target", GameObject.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        final var potentialTargets = this.potentialTargetsProp.getValue().list();
        final var nextTarget = chooseTargetLogic.chooseTarget(this.actor, potentialTargets);

        this.targetProp.setValue(nextTarget);

        return State.SUCCESS;
    }
}
