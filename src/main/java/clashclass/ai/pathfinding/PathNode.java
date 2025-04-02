package clashclass.ai.pathfinding;

import clashclass.commons.Vector2D;

/**
 * Represents a node used for pathfinding.
 */
public interface PathNode {
    /**
     * Gets the x component of the node.
     *
     * @return the x component of the node
     */
    int getX();

    /**
     * Gets the y component of the node.
     *
     * @return the y component of the node
     */
    int getY();

    /**
     * Gets the position vector of the node.
     *
     * @return the position vector with components (x,y)
     */
    Vector2D getPosition();

    /**
     * Gets the cost of the node.
     *
     * @return the cost of the node
     */
    float getCost();
}
