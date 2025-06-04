package clashclass.elements.buildings;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents the types of buildings in the village.
 */
public enum VillageElementData {
    TOWN_HALL("town-hall", 3, 3),
    WALL("wall", 1, 1),
    CANNON("cannon", 2, 2),
    ARCHER_TOWER("archer-tower", 2, 2),
    GOLD_STORAGE("gold-storage", 2, 2),
    ELIXIR_STORAGE("elixir-storage", 2, 2),
    GOLD_EXTRACTOR("gold-extractor", 2, 2),
    ELIXIR_EXTRACTOR("elixir-extractor", 2, 2),
    ARMY_CAMP("army-camp", 3, 3),
    BARRACKS("barracks", 3, 3);

    private final String name;
    private final int rowSpan;
    private final int colSpan;

    VillageElementData(String name, int rowSpan, int colSpan) {
        this.name = name;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
    }

    public static Optional<VillageElementData> getValueFromName(String id) {
        return Arrays.stream(values())
                .filter(x -> x.name.equalsIgnoreCase(id))
                .findFirst();
    }

    public String getName() {
        return this.name;
    }

    public int getRowSpan() {
        return this.rowSpan;
    }

    public int getColSpan() {
        return this.colSpan;
    }
}