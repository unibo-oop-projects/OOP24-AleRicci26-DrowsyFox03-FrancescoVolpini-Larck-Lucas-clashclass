package clashclass.ai.pathfinding;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;

import java.util.Optional;

/**
 * Represents an implementation of a pathfinding Node.
 */
public class PathNodeImpl implements PathNode {
    private final int x;
    private final int y;
    private final float cost;
    private final Optional<GameObject> refGameObject;

    /**
     * Constructs the node.
     *
     * @param x the x component
     * @param y the y component
     * @param cost the cost of the
     * @param refGameObject the GameObject associated to the node, if any
     */
    public PathNodeImpl(final int x, final int y, final float cost, final GameObject refGameObject) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.refGameObject = Optional.of(refGameObject);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameObject> getRefGameObject() {
        return this.refGameObject;
    }
}
