package clashclass.commons;

public class ConversionUtility {
    public static Vector2D convertGridToWorldPosition(final VectorInt2D gridPosition, final int rowSpan, final int colSpan) {
        final var centerGridPosition = gridPosition;
        final double step = GameConstants.TILE_PIXEL_SIZE * (GameConstants.TILE_SCALE / 2.0);
        final double screenOffsetX = GameConstants.SCREEN_WIDTH / 2.0;
        final double screenOffsetY = -(GameConstants.SCREEN_HEIGHT / 2.0) * (GameConstants.TILE_SCALE / 2.0);
        return new Vector2D(
                (centerGridPosition.x() - centerGridPosition.y()) * step + screenOffsetX,
                (centerGridPosition.x() + centerGridPosition.y()) * step + screenOffsetY
        );
    }

    public static VectorInt2D convertWorldToGridPosition(final Vector2D worldPosition) {
        final double step = GameConstants.TILE_PIXEL_SIZE * (GameConstants.TILE_SCALE / 2.0);
        final double screenOffsetX = GameConstants.SCREEN_WIDTH / 2.0;
        final double screenOffsetY = -(GameConstants.SCREEN_HEIGHT / 2.0) * (GameConstants.TILE_SCALE / 2.0);

        double wx = worldPosition.x() - screenOffsetX;
        double wy = worldPosition.y() - screenOffsetY;

        int gridX = (int) Math.floor((wx + wy) / (2 * step));
        int gridY = (int) Math.floor((wy - wx) / (2 * step));

        return new VectorInt2D(gridX, gridY);
    }
}
