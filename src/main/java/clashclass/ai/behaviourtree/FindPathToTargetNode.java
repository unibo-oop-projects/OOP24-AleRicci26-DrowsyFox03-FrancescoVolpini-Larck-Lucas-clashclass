package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.behaviourtree.blackboard.wrappers.PathNodeListWrapper;
import clashclass.ai.pathfinding.PathNodeGrid;
import clashclass.ai.pathfinding.PathfindingAlgorithm;
import clashclass.commons.ConversionUtility;
import clashclass.commons.Transform2D;
import clashclass.ecs.GameObject;

public class FindPathToTargetNode extends AbstractBehaviourNode {
    private final PathfindingAlgorithm pathfindingAlgorithm;
    private BlackboardProperty<GameObject> actorProp;
    private BlackboardProperty<PathNodeGrid> pathNodeGridProp;
    private BlackboardProperty<GameObject> targetProp;
    private BlackboardProperty<PathNodeListWrapper> pathProp;

    public FindPathToTargetNode(final PathfindingAlgorithm pathfindingAlgorithm) {
        this.pathfindingAlgorithm = pathfindingAlgorithm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.actorProp = this.getBlackboard().getProperty("actor", GameObject.class);
        this.targetProp = this.getBlackboard().getProperty("target", GameObject.class);
        this.pathProp = this.getBlackboard().getProperty("path", PathNodeListWrapper.class);
        this.pathNodeGridProp = this.getBlackboard().getProperty("pathNodeGrid", PathNodeGrid.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        final var actor = this.actorProp.getValue();
        final var target = this.targetProp.getValue();
        final var pathNodeGrid = this.pathNodeGridProp.getValue();

        final var actorGridPosition = ConversionUtility.convertWorldToGridPosition(
                actor.getComponentOfType(Transform2D.class).get().getPosition()
        );

        final var targetGridPosition = ConversionUtility.convertWorldToGridPosition(
                target.getComponentOfType(Transform2D.class).get().getPosition()
        );

        final var path = pathfindingAlgorithm.findPath(
                pathNodeGrid,
                pathNodeGrid.getNode(actorGridPosition.x(), actorGridPosition.y()),
                pathNodeGrid.getNode(targetGridPosition.x(), targetGridPosition.y()));

        this.pathProp.setValue(new PathNodeListWrapper(path));

        return State.SUCCESS;
    }
}
