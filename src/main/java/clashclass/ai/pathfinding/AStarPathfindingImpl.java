package clashclass.ai.pathfinding;

import java.nio.file.Path;
import java.util.*;

/**
 * Represents an implementation of A*, a well-know pathfinding algorithm.
 * <a href="https://www.baeldung.com/java-a-star-pathfinding"/>
 */
public class AStarPathfindingImpl implements PathfindingAlgorithm {
    private final Queue<PathNode> openSet;
    private final Set<PathNode> closedSet;
    private final DistanceHeuristic distanceHeuristic;

    /**
     * Constructs the A* algorithm.
     *
     * @param distanceHeuristic the heuristic to use for distance estimation
     */
    public AStarPathfindingImpl(final DistanceHeuristic distanceHeuristic) {
        this.openSet = new PriorityQueue<>();
        this.closedSet = new HashSet<>();
        this.distanceHeuristic = distanceHeuristic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PathNode> findPath(final List<PathNode> nodes, final PathNode start, final PathNode end) {
        this.openSet.clear();
        this.closedSet.clear();

        this.openSet.add(start);

        while (!this.openSet.isEmpty()) {
            final PathNode next = this.openSet.poll();
            if (next.equals(end)) {
                List<PathNode> route = new ArrayList<>(nodes);
                PathNode current = next;
                do {
                    route.add(0, current);
//                    current = nodes.get(current.get)
                } while(current != null);
                return route;
            }
        }

        return List.of();
    }
}
