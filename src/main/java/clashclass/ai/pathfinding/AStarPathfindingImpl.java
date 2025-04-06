package clashclass.ai.pathfinding;

import java.util.List;

/**
 * Represents an implementation of A*, a well-know pathfinding algorithm.
 */
public class AStarPathfindingImpl implements PathfindingAlgorithm {
    private final DistanceHeuristic distanceHeuristic;

    /**
     * Constructs the A* algorithm.
     *
     * @param distanceHeuristic the heuristic to use for distance estimation
     */
    public AStarPathfindingImpl(final DistanceHeuristic distanceHeuristic) {
        this.distanceHeuristic = distanceHeuristic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PathNode> findPath(final List<PathNode> nodes, final PathNode start, final PathNode end) {
        return List.of();
    }
}
