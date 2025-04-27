package clashclass.commons;

/**
 * Represents a Vector in two dimensions (x,y).
 *
 * @param x the x component of the vector
 * @param y the y component of the vector
 */
public record Vector2D(double x, double y) {
    /**
     * Calculates the exact distance between two vectors.
     *
     * @param other the other vector
     *
     * @return the distance between this vector and the other given vector
     */
    public double distance(final Vector2D other) {
        final double deltaX = x - other.x;
        final double deltaY = y - other.y;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public Vector2D Add(final Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D Subtract(final Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D Multiply(final double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    /**
     * Creates a vector with zero in both x and y components.
     *
     * @return the vector with (0,0)
     */
    public static Vector2D zero() {
        return new Vector2D(0, 0);
    }

    /**
     * Creates a vector with one in both x and y components.
     *
     * @return the vector with (1,1)
     */
    public static Vector2D one() {
        return new Vector2D(1, 1);
    }
}
