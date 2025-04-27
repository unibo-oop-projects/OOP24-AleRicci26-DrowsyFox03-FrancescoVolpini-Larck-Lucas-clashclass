package clashclass.ai.behaviourtree;

import clashclass.ai.logic.ChooseTargetLogic;

public class ChooseNextTargetNode extends AbstractBehaviourNode {
    private final ChooseTargetLogic chooseTargetLogic;

    public ChooseNextTargetNode(final ChooseTargetLogic chooseTargetLogic) {
        this.chooseTargetLogic = chooseTargetLogic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
//        final var nextTarget = chooseTargetLogic.chooseTarget()
        return State.SUCCESS;
    }
}
