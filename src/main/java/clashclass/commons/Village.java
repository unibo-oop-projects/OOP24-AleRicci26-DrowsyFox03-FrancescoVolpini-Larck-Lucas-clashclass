package clashclass.commons;

import clashclass.ecs.GameObject;
import clashclass.elements.commons.CommonGameObjectFactoryImpl;
import clashclass.elements.commons.CommonGameObjectsFactory;
import clashclass.resources.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Village {
    private final Player player;
    private final Set<GameObject> buildings;
    private final GameObject[][] groundGrid;
    private final GameObject[][] objectGrid;
    private final CommonGameObjectsFactory commonGameObjectFactory;

    public Village(final Player player) {
        this.player = player;
        this.groundGrid = new GameObject[GameConstants.VILLAGE_SIZE][GameConstants.VILLAGE_SIZE];
        this.objectGrid = new GameObject[GameConstants.VILLAGE_SIZE][GameConstants.VILLAGE_SIZE];
        this.commonGameObjectFactory = new CommonGameObjectFactoryImpl();
        this.buildings = new HashSet<>();

        this.createGroundTiles();
    }

    private void createGroundTiles() {
        IntStream.range(0, GameConstants.VILLAGE_SIZE).forEach(i ->
                IntStream.range(0, GameConstants.VILLAGE_SIZE).forEach(j -> {
                    this.groundGrid[i][j] = this.commonGameObjectFactory
                            .createVillageGroundTile(new VectorInt2D(i, j));
                }));
    }

    public void placeBuilding(final GameObject building, final VectorInt2D position, int width, int height) {
        this.buildings.add(building);
        IntStream.range(0, width).forEach(i ->
                IntStream.range(0, height).forEach(j -> {
                    this.objectGrid[position.x() - i][position.y() - j] = building;
                }));
    }

    public void placeBuilding(final GameObject building) {
        final var tileData = building.getComponentOfType(GridTileData2D.class).get();
        this.placeBuilding(building, tileData.getPosition(), tileData.getRowSpan(), tileData.getColSpan());
    }

    public GameObject getBuildingAtPosition(VectorInt2D position) {
        return this.objectGrid[position.x()][position.y()];
    }

    public Player getPlayer() {
        return this.player;
    }

    public Set<GameObject> getBuildings() {
        return this.buildings.stream().collect(Collectors.toUnmodifiableSet());
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
