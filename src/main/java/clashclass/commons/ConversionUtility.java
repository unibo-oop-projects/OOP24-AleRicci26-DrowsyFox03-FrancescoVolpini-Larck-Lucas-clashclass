package clashclass.commons;

import clashclass.elements.buildings.VillageElementData;

public class ConversionUtility {
    public static Vector2D convertGridToWorldPosition(final VectorInt2D gridPosition, final int rowSpan, final int colSpan) {
//        final var centerGridPosition = new VectorInt2D(
//                gridPosition.x() + rowSpan / 2,
//                gridPosition.y() + colSpan / 2
//        );

        final var centerGridPosition = gridPosition;
        final double step = GameConstants.TILE_PIXEL_SIZE * (GameConstants.TILE_SCALE / 2.0);
        final double screenOffsetX = GameConstants.SCREEN_WIDTH / 2.0;
        final double screenOffsetY = -(GameConstants.SCREEN_HEIGHT / 2.0) * (GameConstants.TILE_SCALE / 2.0);
        return new Vector2D(
                (centerGridPosition.x() - centerGridPosition.y()) * step + screenOffsetX,
                (centerGridPosition.x() + centerGridPosition.y()) * step + screenOffsetY
        );
    }
}
