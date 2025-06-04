package clashclass.view.graphic.components;

import clashclass.view.graphic.Graphic;

public class ImageRendererImpl extends BaseGraphicComponent {
    private final String spriteName;

    public ImageRendererImpl(final String spriteName, final int layer) {
        super(1, 1, layer);
        this.spriteName = spriteName;
    }

    @Override
    public void draw(final Graphic graphics) {
        graphics.drawSprites(this.getGameObject(), this.spriteName);
    }
}
