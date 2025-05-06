package clashclass.elements.buildings;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.commons.Vector2D;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Represents the data structure for Buildings in the village.
 * Each enum constant corresponds to a specific type of building and contains its factory method.
 */
public enum VillageElementData {
    TOWN_HALL,
    WALL,
    CANNON,
    ARCHER_TOWER,
    GOLD_STORAGE,
    ELIXIR_STORAGE,
    GOLD_EXTRACTOR,
    ELIXIR_EXTRACTOR,
    ARMY_CAMP,
    BARRACKS;

    private static final Map<VillageElementData, Function<VectorInt2D, GameObject>> buildingIdToFactory;

    static {
        buildingIdToFactory = new EnumMap<>(VillageElementData.class);
        BuildingFactory factory = new PlayerBuildingFactoryImpl();

        // Initialize the map with factory methods for each building type
        buildingIdToFactory.put(TOWN_HALL,
                pos -> factory.createTownHall(new Vector2D(pos.x(), pos.y())));
        buildingIdToFactory.put(WALL,
                pos -> factory.createWall(new Vector2D(pos.x(), pos.y())));
        buildingIdToFactory.put(CANNON,
                pos -> factory.createCannon(new Vector2D(pos.x(), pos.y())));
        buildingIdToFactory.put(ARCHER_TOWER,
                pos -> factory.createArcherTower(new Vector2D(pos.x(), pos.y())));
        buildingIdToFactory.put(GOLD_STORAGE,
                pos -> factory.createGoldStorage(new Vector2D(pos.x(), pos.y())));
        buildingIdToFactory.put(ELIXIR_STORAGE,
                pos -> factory.createElixirStorage(new Vector2D(pos.x(), pos.y())));
        buildingIdToFactory.put(GOLD_EXTRACTOR,
                pos -> factory.createGoldExtractor(new Vector2D(pos.x(), pos.y())));
        buildingIdToFactory.put(ELIXIR_EXTRACTOR,
                pos -> factory.createElixirExtractor(new Vector2D(pos.x(), pos.y())));
        buildingIdToFactory.put(ARMY_CAMP,
                pos -> factory.createArmyCamp(new Vector2D(pos.x(), pos.y())));
        buildingIdToFactory.put(BARRACKS,
                pos -> factory.createBarracks(new Vector2D(pos.x(), pos.y())));
    }

    /**
     * Gets the factory function for creating a GameObject of the specified building type.
     *
     * @param buildingType the type of building to get the factory for
     * @return the factory function that creates the specified building type
     */
    public static Function<VectorInt2D, GameObject> getFactory(VillageElementData buildingType) {
        return buildingIdToFactory.get(buildingType);
    }

    /**
     * Gets the map of all building types to their respective factory functions.
     *
     * @return an unmodifiable view of the building factory map
     */
    public static Map<VillageElementData, Function<VectorInt2D, GameObject>> getBuildingFactories() {
        return Map.copyOf(buildingIdToFactory);
    }
}