package clashclass.elements.commons;

import clashclass.commons.ConversionUtility;
import clashclass.commons.GridTileData2D;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.ecs.GameObjectImpl;
import clashclass.elements.ComponentFactory;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.view.graphic.components.ImageRendererImpl;
import clashclass.view.graphic.components.UIRendererImpl;

public class CommonGameObjectFactoryImpl implements CommonGameObjectsFactory {
    private final ComponentFactory componentFactory;

    public CommonGameObjectFactoryImpl() {
        this.componentFactory = new ComponentFactoryImpl();
    }

    @Override
    public GameObject createVillageGroundTile(VectorInt2D position) {
        return new GameObjectImpl.BuilderImpl()
                .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                        .convertGridToWorldPosition(position, 1, 1)))
                .addComponent(new GridTileData2D(position, 1, 1))
                .addComponent(new ImageRendererImpl("grass-tile", 0))
                .build();
    }

    @Override
    public GameObject createUIElement() {
        return new GameObjectImpl.BuilderImpl()
                .addComponent(new UIRendererImpl(1, 1, 2))
                .build();
    }
}
