package clashclass.commons;

import clashclass.ecs.AbstractComponent;

public class CellPosition2D extends AbstractComponent {
    private final VectorInt2D position;

    public CellPosition2D(final VectorInt2D position) {
        this.position = position;
    }

    public VectorInt2D getPosition() {
        return this.position;
    }
}
