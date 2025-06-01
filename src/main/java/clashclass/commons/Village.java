package clashclass.commons;

import clashclass.ecs.GameObject;
import clashclass.elements.commons.CommonGameObjectFactoryImpl;
import clashclass.elements.commons.CommonGameObjectsFactory;
import java.util.stream.IntStream;

public class Village {
    private final GameObject[][] groundGrid;
    private final GameObject[][] objectGrid;
    private final CommonGameObjectsFactory commonGameObjectFactory;
    private final int size;
    private final int tileSize;

    public Village(final int size, final int tileSize) {
        this.size = size;
        this.tileSize = tileSize;
        this.groundGrid = new GameObject[size][size];
        this.objectGrid = new GameObject[size][size];
        this.commonGameObjectFactory = new CommonGameObjectFactoryImpl();

        this.createGroundTiles();
    }

    private void createGroundTiles() {
        IntStream.range(0, size).forEach(i ->
                IntStream.range(0, size).forEach(j -> {
                    this.groundGrid[i][j] = this.commonGameObjectFactory
                            .createVillageGroundTile(new Vector2D(i, j));
                }));
    }

    public int getSize() {
        return size;
    }

    public void placeBuilding(GameObject building, VectorInt2D position, int width, int height) {
        IntStream.range(0, width).forEach(i ->
                IntStream.range(0, height).forEach(j -> {
                    this.objectGrid[position.x() + i][position.y() + j] = building;
                }));
    }

    public GameObject getObjectAtPosition(VectorInt2D position) {
        return objectGrid[position.x()][position.y()];
    }
}
