package clashclass.view.graphic.components;

import clashclass.ecs.AbstractComponent;

/**
 * Abstract class that represents the GraphicComponent of a GameObject.
 */
public abstract class BaseGraphicComponent extends AbstractComponent implements DrawableComponent {
    private final double width;
    private final double height;
    private final int layer;

    /**
     * Protected Builder for GraphicComponent.
     * @param width of the GraphicComponent
     * @param height of the GraphicComponent
     */
    protected BaseGraphicComponent(final double width, final double height, final int layer) {
        this.width = width;
        this.height = height;
        this.layer = layer;
    }

    /**
     * @return the width of the GraphicComponent
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height of the GraphicComponent
     */
    public double getHeight() {
        return height;
    }

    public int getLayer() {
        return this.layer;
    }
}
