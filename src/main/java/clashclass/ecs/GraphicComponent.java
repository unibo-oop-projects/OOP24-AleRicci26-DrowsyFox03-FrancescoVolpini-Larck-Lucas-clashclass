package clashclass.ecs;

/**
 * Abstract class that represents the GraphicComponent of a GameObject.
 */
public abstract class GraphicComponent extends AbstractComponent implements DrawableComponent {
    private final double width;
    private final double height;

    /**
     * Protected Builder for GraphicComponent.
     * @param width of the GraphicComponent
     * @param height of the GraphicComponent
     */
    protected GraphicComponent(final double width, final double height) {
        this.width = width;
        this.height = height;
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
}
