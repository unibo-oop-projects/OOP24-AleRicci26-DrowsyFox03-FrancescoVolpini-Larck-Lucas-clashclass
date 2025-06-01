package clashclass.commons;

import clashclass.ecs.GameObject;
import clashclass.elements.commons.CommonGameObjectFactoryImpl;
import clashclass.elements.commons.CommonGameObjectsFactory;
import java.util.stream.IntStream;

public class Village {
    private final GameObject[][] groundGrid;
    private final GameObject[][] objectGrid;
    private final CommonGameObjectsFactory commonGameObjectFactory;

    public Village() {
        this.groundGrid = new GameObject[GameConstants.VILLAGE_SIZE][GameConstants.VILLAGE_SIZE];
        this.objectGrid = new GameObject[GameConstants.VILLAGE_SIZE][GameConstants.VILLAGE_SIZE];
        this.commonGameObjectFactory = new CommonGameObjectFactoryImpl();

        this.createGroundTiles();
    }

    private void createGroundTiles() {
        IntStream.range(0, GameConstants.VILLAGE_SIZE).forEach(i ->
                IntStream.range(0, GameConstants.VILLAGE_SIZE).forEach(j -> {
                    this.groundGrid[i][j] = this.commonGameObjectFactory
                            .createVillageGroundTile(new Vector2D(i, j));
                }));
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
