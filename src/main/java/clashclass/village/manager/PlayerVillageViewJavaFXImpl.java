package clashclass.village.manager;

import clashclass.commons.Rect2D;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.commons.CommonGameObjectFactoryImpl;
import clashclass.engine.GameEngine;
import clashclass.view.graphic.components.UIRendererImpl;

public class PlayerVillageViewJavaFXImpl implements PlayerVillageView {
    private final GameObject uiGameObject;

    public PlayerVillageViewJavaFXImpl(final GameEngine gameEngine) {
        final var factory = new CommonGameObjectFactoryImpl();
        this.uiGameObject = factory.createUIElement();
        gameEngine.addGameObject(this.uiGameObject);
    }

    @Override
    public void update(final PlayerVillageModel model) {
        this.redraw(model);
    }

    private void redraw(final PlayerVillageModel model) {
        uiGameObject.getComponentOfType(UIRendererImpl.class).ifPresent(uiRenderer -> {
            uiRenderer.setDrawFunction(graphics -> {
                graphics.drawRectangle(this.uiGameObject, new Rect2D(
                        new VectorInt2D(20, 100),
                        new VectorInt2D(300, 50)));
                // TODO ...
            });
        });
    }
}
