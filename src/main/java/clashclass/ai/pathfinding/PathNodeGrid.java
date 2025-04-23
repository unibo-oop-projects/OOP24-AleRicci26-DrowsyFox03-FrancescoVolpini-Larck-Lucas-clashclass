package clashclass.ai.pathfinding;

import clashclass.commons.VectorInt2D;

import java.util.Set;

/**
 * Represents a grid that contains the PathNodes.
 */
public interface PathNodeGrid {
    /**
     * Gets the node at the given position.
     *
     * @param x the x component of the position
     * @param y the y component of the position
     *
     * @return the PathNode at the given position
     */
    PathNode getNode(int x, int y);

    /**
     * Gets all the nodes in the grid.
     *
     * @return all the nodes int the grid
     */
    Set<PathNode> getNodes();

    /**
     * Gets all the neighbors of a given node.
     *
     * @param node the given node
     *
     * @return the neighbors of the given node
     */
    Set<PathNode> getNeighborsOfNode(PathNode node);

    Set<VectorInt2D> getNeighborsPositionsOfNode(PathNode node);
}
