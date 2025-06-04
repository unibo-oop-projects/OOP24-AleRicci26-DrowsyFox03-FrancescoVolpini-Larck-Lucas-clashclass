package clashclass.view.graphic.components;

import clashclass.commons.Rect2D;
import clashclass.commons.Transform2D;
import clashclass.view.graphic.Graphic;

public class ProgressBarRendererImpl extends BaseGraphicComponent {
    private final int barWidth;
    private final int yOffset;
    private final int barHeight;
    private float percentage;

    public ProgressBarRendererImpl(double width, double height, int layer, int barWidth, int barHeight, int yOffset) {
        super(1, 1, layer);
        this.barWidth = barWidth;
        this.barHeight = barHeight;
        this.yOffset = yOffset;
        this.percentage = 100.0f;
    }

    void setPercentage(final float percentage) {
        this.percentage = percentage;
    }

    @Override
    public void draw(final Graphic graphics) {
        if (this.percentage >= 99.0f) return;
        final var position = this.getGameObject()
                .getComponentOfType(Transform2D.class).get().getPosition();
        final var backgroundRect = new Rect2D(
                (int)position.x(),
                (int)position.y() - yOffset,
                this.barWidth,
                this.barHeight
        );
        final var foregroundRect = new Rect2D(
                (int)position.x(),
                (int)(position.y() - yOffset),
                (int)(this.barWidth * this.percentage),
                this.barHeight
        );

        graphics.drawRectangle(this.getGameObject(), "#FFFFFF", backgroundRect);
        graphics.drawRectangle(this.getGameObject(), "#FF0000", foregroundRect);
    }
}
