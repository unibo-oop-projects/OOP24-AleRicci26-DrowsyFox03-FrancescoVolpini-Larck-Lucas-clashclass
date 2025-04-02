package clashclass.ai.pathfinding;

import java.util.List;

/**
 * Represents an algorithm used to find a valid path to a target.
 */
public interface PathfindingAlgorithm {
    /**
     * Finds a path from the start node to the given target, traversing the nodes.
     *
     * @param nodes all the nodes in the grid
     * @param start the start node
     * @param end the end node
     *
     * @return the ordered list of nodes representing the path to the target
     */
    List<PathNode> findPath(List<PathNode> nodes, PathNode start, PathNode end);
}
