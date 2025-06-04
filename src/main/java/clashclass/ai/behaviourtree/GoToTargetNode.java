package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.behaviourtree.blackboard.wrappers.PathNodeListWrapper;
import clashclass.ai.pathfinding.PathNode;
import clashclass.commons.ConversionUtility;
import clashclass.commons.Transform2D;
import clashclass.ecs.GameObject;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class GoToTargetNode extends AbstractBehaviourNode {
    private final float distanceToTargetTolerance;
    private BlackboardProperty<GameObject> actorProp;
    private BlackboardProperty<PathNodeListWrapper> pathProp;

    private final Queue<PathNode> remainingPathNodes;
    private Optional<PathNode> currentTarget;

    public GoToTargetNode(final float distanceToTargetTolerance) {
        this.distanceToTargetTolerance = distanceToTargetTolerance;
        this.remainingPathNodes = new LinkedList<>();
        this.currentTarget = Optional.empty();
    }

    @Override
    public void onEnter() {
        this.actorProp = this.getBlackboard().getProperty("actor", GameObject.class);
        this.pathProp = this.getBlackboard().getProperty("path", PathNodeListWrapper.class);
        this.currentTarget = Optional.empty();
        this.remainingPathNodes.clear();

        if (this.pathProp != null && this.pathProp.getValue() != null) {
            this.remainingPathNodes.addAll(this.pathProp.getValue().list());
        }
    }

    @Override
    public State onUpdate(final float deltaTime) {
        if (remainingPathNodes.isEmpty()) {
            return State.SUCCESS;
        }

        final var actor = this.actorProp.getValue();
        final var actorPosition = actor.getComponentOfType(Transform2D.class).get().getPosition();

        if (this.currentTarget.isEmpty()) {
            this.currentTarget = Optional.of(this.remainingPathNodes.poll());
        }

        final var targetPosition = ConversionUtility.convertGridToWorldPosition(
                this.currentTarget.get().getPosition(), 0, 0
        );

        final var distanceToTarget = actorPosition.distance(targetPosition);

        if (distanceToTarget <= distanceToTargetTolerance) {
            this.currentTarget = Optional.empty();
        }

        final var actorTransform = actor.getComponentOfType(Transform2D.class).get();
        final var speed = 15; // TODO: get della speed corrente (può variare eventualmente ad ogni frame)

        final var movement = targetPosition
                .subtract(actorPosition)
                .normalized()
                .multiply(speed * deltaTime);
        final var newPosition = actorPosition.add(movement);

        actorTransform.setPosition(newPosition);

        return State.RUNNING;
    }
}
