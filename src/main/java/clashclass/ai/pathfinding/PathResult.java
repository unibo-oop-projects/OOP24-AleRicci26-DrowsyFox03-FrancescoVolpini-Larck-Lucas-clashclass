package clashclass.ai.pathfinding;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;

/**
 * Represents a Path Result wrapper.
 *
 * @param pathNodes the list of path nodes
 * @param cost the total cost of the path
 */
@SuppressFBWarnings(value = {"EI","EI2"}, justification = "Intentional access")
public record PathResult(List<PathNode> pathNodes, float cost) { }
