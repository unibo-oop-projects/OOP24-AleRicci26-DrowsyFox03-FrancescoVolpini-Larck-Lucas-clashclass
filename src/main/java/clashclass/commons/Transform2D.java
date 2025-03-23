package clashclass.commons;

import clashclass.ecs.AbstractComponent;

/**
 * Represents a Transform in a two-dimensional space.
 */
public class Transform2D extends AbstractComponent {
    private Vector2D position;
    private Vector2D rotation;
    private Vector2D scale;

    /**
     * Gets the position component.
     *
     * @return the position
     */
    public Vector2D getPosition() {
        return this.position;
    }

    /**
     * Gets the rotation component.
     *
     * @return the rotation
     */
    public Vector2D getRotation() {
        return this.rotation;
    }

    /**
     * Gets the scale component.
     *
     * @return the scale
     */
    public Vector2D getScale() {
        return this.scale;
    }

    /**
     * Sets the position component.
     *
     * @param position the new position value
     */
    public void setPosition(final Vector2D position) {
        this.position = position;
    }

    /**
     * Sets the rotation component.
     *
     * @param rotation the new rotation value
     */
    public void setRotation(final Vector2D rotation) {
        this.rotation = rotation;
    }

    /**
     * Sets the scale component.
     *
     * @param scale the new scale value
     */
    public void setScale(final Vector2D scale) {
        this.scale = scale;
    }
}
