package clashclass.elements;

import clashclass.commons.HealthComponentImpl;
import clashclass.commons.Transform2D;
import clashclass.commons.Vector2D;
import clashclass.ecs.Component;
import clashclass.view.graphic.components.GraphicComponentImpl;
import clashclass.view.graphic.components.ProgressBarRendererImpl;

/**
 * Represents an implementation of ComponentFactory.
 */
public class ComponentFactoryImpl implements ComponentFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Component createTransform2D(final Vector2D position) {
        return new Transform2D(position, Vector2D.zero(), Vector2D.one());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createHealth(final int maxValue) {
        return new HealthComponentImpl(maxValue);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Component createGraphic(final double width, final double height) {
        return new GraphicComponentImpl(width, height);
    }

    @Override
    public Component createProgressBar() {
        return new ProgressBarRendererImpl(1, 1, 2, 20, 5, 10);
    }
}
