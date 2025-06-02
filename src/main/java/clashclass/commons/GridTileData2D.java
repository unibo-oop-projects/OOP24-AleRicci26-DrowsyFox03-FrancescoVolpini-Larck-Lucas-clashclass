package clashclass.commons;

import clashclass.ecs.AbstractComponent;

public class GridTileData2D extends AbstractComponent {
    private final VectorInt2D position;
    private final int rowSpan;
    private final int colSpan;

    public GridTileData2D(final VectorInt2D position, final int rowSpan, final int colSpan) {
        this.position = position;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
    }

    public VectorInt2D getPosition() {
        return this.position;
    }

    int getRowSpan() {
        return this.rowSpan;
    }

    int getColSpan() {
        return this.colSpan;
    }
}
