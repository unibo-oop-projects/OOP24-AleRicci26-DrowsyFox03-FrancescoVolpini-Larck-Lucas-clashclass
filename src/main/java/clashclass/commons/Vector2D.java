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
}
