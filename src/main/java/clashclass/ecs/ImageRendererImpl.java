package clashclass.ecs;

import clashclass.view.graphic.Graphic;

public class ImageRendererImpl extends GraphicComponent {
    private final String spriteName;

    public ImageRendererImpl(String spriteName) {
        super(1, 1);
        this.spriteName = spriteName;
    }

    @Override
    public void draw(final Graphic graphics) {
        graphics.drawSprites(this.getGameObject(), this.spriteName);
    }
}
