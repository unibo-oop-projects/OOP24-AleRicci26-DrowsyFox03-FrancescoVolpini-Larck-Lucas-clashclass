package clashclass.commons;

import clashclass.ecs.AbstractComponent;
import clashclass.elements.buildings.BUILDING_FLAG;

import java.util.Set;

/**
 * Represents a component which holds some flag useful for the buildings.
 */
public class BuildingFlagsComponent extends AbstractComponent {
    private final Set<BUILDING_FLAG> buildingFlags;

    /**
     * Constructs the component.
     *
     * @param flags the set of flags
     */
    public BuildingFlagsComponent(final Set<BUILDING_FLAG> flags) {
        this.buildingFlags = flags;
    }

    /**
     * Gets the set of flags.
     *
     * @return the set of flags
     */
    public Set<BUILDING_FLAG> getFlags() {
        return this.buildingFlags;
    }
}
