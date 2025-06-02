package clashclass.view.graphic.components;

import clashclass.view.graphic.Graphic;

import java.util.function.Consumer;

public class UIRendererImpl extends GraphicComponent {
    private Consumer<Graphic> drawFunction = x -> {};

    public UIRendererImpl(double width, double height, int layer) {
        super(1, 1, 2 + layer);
    }

    public void setDrawFunction(final Consumer<Graphic> drawFunction) {
        this.drawFunction = drawFunction;
    }

    @Override
    public void draw(final Graphic graphics) {
        this.drawFunction.accept(graphics);
    }
}
