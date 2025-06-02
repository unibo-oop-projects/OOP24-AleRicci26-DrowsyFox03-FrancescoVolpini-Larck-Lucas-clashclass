package clashclass.commons;

import clashclass.ecs.GameObject;
import clashclass.elements.commons.CommonGameObjectFactoryImpl;
import clashclass.elements.commons.CommonGameObjectsFactory;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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
                            .createVillageGroundTile(new VectorInt2D(i, j));
                }));
    }

    public void placeBuilding(GameObject building, VectorInt2D position, int width, int height) {
        IntStream.range(0, width).forEach(i ->
                IntStream.range(0, height).forEach(j -> {
                    this.objectGrid[position.x() - i][position.y() - j] = building;
                }));
    }

    public GameObject getObjectAtPosition(VectorInt2D position) {
        return objectGrid[position.x()][position.y()];
    }

    public Set<GameObject> getGroundObjects() {
        return Arrays.stream(this.groundGrid)
                .flatMap(Arrays::stream)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<GameObject> getGameObjects() {
        return Arrays.stream(this.objectGrid)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableSet());
    }
}
