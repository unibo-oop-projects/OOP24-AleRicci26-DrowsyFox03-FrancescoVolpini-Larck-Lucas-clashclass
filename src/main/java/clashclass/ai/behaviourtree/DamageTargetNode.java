package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.logic.CalculateDamageLogic;
import clashclass.commons.HealthComponent;
import clashclass.ecs.GameObject;

public class DamageTargetNode extends AbstractBehaviourNode {
    private final CalculateDamageLogic damageLogic;
    private BlackboardProperty<GameObject> actorProp;
    private BlackboardProperty<GameObject> targetProp;

    public DamageTargetNode(final CalculateDamageLogic damageLogic) {
        this.damageLogic = damageLogic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.actorProp = this.getBlackboard().getProperty("actor", GameObject.class);
        this.targetProp = this.getBlackboard().getProperty("target", GameObject.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        final var actor = this.actorProp.getValue();
        final var target = this.targetProp.getValue();

        final var damage = this.damageLogic.calculateDamage(actor, target);

        final var targetHealth = target.getComponentOfType(HealthComponent.class).get();
        targetHealth.decrease(damage);

        return State.SUCCESS;
    }
}
