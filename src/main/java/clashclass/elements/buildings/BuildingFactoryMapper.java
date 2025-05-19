package clashclass.elements.buildings;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Maps building types to their factory methods
 */
public class BuildingFactoryMapper {
    private final Map<VillageElementData, Function<Vector2D, GameObject>> buildingIdToFactory;
    private final BuildingFactory buildingFactory;

    public BuildingFactoryMapper(BuildingFactory buildingFactory) {
        this.buildingFactory = buildingFactory;
        this.buildingIdToFactory = new EnumMap<>(VillageElementData.class);
        initializeMap();
    }

    private void initializeMap() {
        // Map each building type to its corresponding factory method
        buildingIdToFactory.put(VillageElementData.TOWN_HALL, buildingFactory::createTownHall);
        buildingIdToFactory.put(VillageElementData.WALL, buildingFactory::createWall);
        buildingIdToFactory.put(VillageElementData.CANNON, buildingFactory::createCannon);
        buildingIdToFactory.put(VillageElementData.ARCHER_TOWER, buildingFactory::createArcherTower);
        buildingIdToFactory.put(VillageElementData.GOLD_STORAGE, buildingFactory::createGoldStorage);
        buildingIdToFactory.put(VillageElementData.ELIXIR_STORAGE, buildingFactory::createElixirStorage);
        buildingIdToFactory.put(VillageElementData.GOLD_EXTRACTOR, buildingFactory::createGoldExtractor);
        buildingIdToFactory.put(VillageElementData.ELIXIR_EXTRACTOR, buildingFactory::createElixirExtractor);
        buildingIdToFactory.put(VillageElementData.ARMY_CAMP, buildingFactory::createArmyCamp);
        buildingIdToFactory.put(VillageElementData.BARRACKS, buildingFactory::createBarracks);
    }

    /**
     * Get factory method for creating a specific building type
     *
     * @param buildingType The type of building to create
     * @return Function that creates the building at a given position
     */
    public Function<Vector2D, GameObject> getFactoryFor(VillageElementData buildingType) {
        Function<Vector2D, GameObject> factory = buildingIdToFactory.get(buildingType);
        if (factory == null) {
            throw new IllegalArgumentException("No factory method found for building type: " + buildingType);
        }
        return factory;
    }
}
