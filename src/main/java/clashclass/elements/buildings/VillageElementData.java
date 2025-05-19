package clashclass.elements.buildings;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.commons.Vector2D;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Represents the types of buildings in the village.
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

}