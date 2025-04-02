package clashclass.ai.pathfinding;

import clashclass.commons.Vector2D;

/**
 * Represents an implementation of a pathfinding Node.
 */
public class PathNodeImpl implements PathNode {
    private final int x;
    private final int y;
    private final float cost;

    /**
     * Constructs the node.
     *
     * @param x the x component
     * @param y the y component
     * @param cost the cost of the node
     */
    public PathNodeImpl(final int x, final int y, final float cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getPosition() {
        return new Vector2D(this.x, this.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getCost() {
        return this.cost;
    }
}
