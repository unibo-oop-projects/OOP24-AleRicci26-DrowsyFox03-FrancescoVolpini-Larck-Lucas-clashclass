package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.behaviourtree.blackboard.wrappers.GameObjectListWrapper;
import clashclass.ai.behaviourtree.blackboard.wrappers.PathNodeListWrapper;
import clashclass.ai.logic.ChooseTargetLogic;
import clashclass.ai.pathfinding.AStarPathfindingImpl;
import clashclass.ai.pathfinding.EuclideanDistanceHeuristicImpl;
import clashclass.ai.pathfinding.PathNodeGrid;
import clashclass.ai.pathfinding.PathfindingAlgorithm;
import clashclass.ecs.GameObject;

public class ChooseNextTargetNode extends AbstractBehaviourNode {
    private final ChooseTargetLogic chooseTargetLogic;
    private final PathfindingAlgorithm pathfindingAlgorithm;
    private BlackboardProperty<GameObject> actorProp;
    private BlackboardProperty<GameObjectListWrapper> potentialTargetsProp;
    private BlackboardProperty<PathNodeGrid> pathNodeGridProp;
    private BlackboardProperty<GameObject> targetProp;
    private BlackboardProperty<PathNodeListWrapper> pathProp;

    public ChooseNextTargetNode(final ChooseTargetLogic chooseTargetLogic) {
        this.chooseTargetLogic = chooseTargetLogic;
        this.pathfindingAlgorithm = new AStarPathfindingImpl(new EuclideanDistanceHeuristicImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.actorProp = this.getBlackboard().getProperty("actor", GameObject.class);
        this.targetProp = this.getBlackboard().getProperty("target", GameObject.class);
        this.potentialTargetsProp = this.getBlackboard().getProperty("potentialTargets", GameObjectListWrapper.class);
        this.pathProp = this.getBlackboard().getProperty("path", PathNodeListWrapper.class);
        this.pathNodeGridProp = this.getBlackboard().getProperty("pathNodeGrid", PathNodeGrid.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        final var actor = this.actorProp.getValue();
        final var potentialTargets = this.potentialTargetsProp.getValue().list();

        final var nextTarget = chooseTargetLogic.chooseTarget(actor, potentialTargets);
        this.targetProp.setValue(nextTarget);

        final var pathNodeGrid = this.pathNodeGridProp.getValue();
        final var path = pathfindingAlgorithm.findPath(
                pathNodeGrid, pathNodeGrid.getNode(0, 0), pathNodeGrid.getNode(1, 1));

        this.pathProp.setValue(new PathNodeListWrapper(path));

        return State.SUCCESS;
    }
}
