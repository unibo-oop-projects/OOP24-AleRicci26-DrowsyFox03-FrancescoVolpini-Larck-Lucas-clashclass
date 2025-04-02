package clashclass.ai.pathfinding;

/**
 * Represents an implementation of a pathfinding Node.
 */
public class PathNodeImpl implements PathNode {
    private final int x;
    private final int y;

    /**
     * Constructs the node.
     *
     * @param x the x component
     * @param y the y component
     */
    public PathNodeImpl(final int x, final int y) {
        this.x = x;
        this.y = y;
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
}
