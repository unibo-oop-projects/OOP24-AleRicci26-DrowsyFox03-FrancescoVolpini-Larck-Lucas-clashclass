package clashclass.ecs;

/**
 * Concrete implementation of GraphicComponent for rendering GameObjects.
 */
public class GraphicComponentImpl extends GraphicComponent {
    
    /**
     * Creates a GraphicComponent with specified dimensions.
     * 
     * @param width the width of the component
     * @param height the height of the component
     */
    public GraphicComponentImpl(final double width, final double height) {
        super(width, height);
    }
}