package clashclass.elements.commons;

import clashclass.commons.*;
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

    @Override
    public GameObject createVillageGroundTile(VectorInt2D position) {
        return new GameObjectImpl.BuilderImpl()
                .addComponent(this.componentFactory.createTransform2D(ConversionUtility.convertGridToWorldPosition(position)))
                .addComponent(new GridTileData2D(position))
                .addComponent(new ImageRendererImpl("grass-tile", 0))
                .build();
    }
}
