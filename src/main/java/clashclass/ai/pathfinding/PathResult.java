package clashclass.ai.pathfinding;

import java.util.List;

/**
 * Represents a Path Result wrapper.
 *
 * @param pathNodes the list of path nodes
 * @param cost the total cost of the path
 */
public record PathResult(List<PathNode> pathNodes, float cost) {}
