package clashclass.commons;

public class ConversionUtility {
    public static Vector2D convertGridToWorldPosition(final VectorInt2D gridPosition) {
        final double step = GameConstants.TILE_PIXEL_SIZE * (GameConstants.TILE_SCALE / 2.0);
        final double screenOffsetX = GameConstants.SCREEN_WIDTH / 2.0;
        final double screenOffsetY = -(GameConstants.SCREEN_HEIGHT / 2.0) * (GameConstants.TILE_SCALE / 2.0);
        return new Vector2D(
                (gridPosition.x() - gridPosition.y()) * step + screenOffsetX,
                (gridPosition.x() + gridPosition.y()) * step + screenOffsetY
        );
    }
}
