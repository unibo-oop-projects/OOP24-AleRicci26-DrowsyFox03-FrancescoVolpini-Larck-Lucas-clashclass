package clashclass.elements.commons;

import clashclass.commons.CellPosition2D;
import clashclass.commons.GameConstants;
import clashclass.commons.Vector2D;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.ecs.GameObjectImpl;
import clashclass.ecs.ImageRendererImpl;
import clashclass.elements.ComponentFactory;
import clashclass.elements.ComponentFactoryImpl;

public class CommonGameObjectFactoryImpl implements CommonGameObjectsFactory {
    private final ComponentFactory componentFactory;

    public CommonGameObjectFactoryImpl() {
        this.componentFactory = new ComponentFactoryImpl();
    }

    private Vector2D convertGridToWorldPosition(final VectorInt2D gridPosition) {
        final double step = GameConstants.TILE_PIXEL_SIZE * GameConstants.TILE_SCALE / 2.0;

        return new Vector2D(
                (gridPosition.x() - gridPosition.y()) * step + GameConstants.SCREEN_WIDTH / 2.0,
                (gridPosition.x() + gridPosition.y()) * step
        );
    }

    @Override
    public GameObject createVillageGroundTile(VectorInt2D position) {
        return new GameObjectImpl.BuilderImpl()
                .addComponent(this.componentFactory.createTransform2D(convertGridToWorldPosition(position)))
                .addComponent(new CellPosition2D(position))
                .addComponent(new ImageRendererImpl("grass-tile", 0))
                .build();
    }
}
