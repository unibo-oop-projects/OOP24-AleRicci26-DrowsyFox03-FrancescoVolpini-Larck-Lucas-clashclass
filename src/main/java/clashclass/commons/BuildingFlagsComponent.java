package clashclass.commons;

import clashclass.ecs.AbstractComponent;
import clashclass.elements.buildings.BUILDING_FLAG;

import java.util.Set;

public class BuildingFlagsComponent extends AbstractComponent {
    private final Set<BUILDING_FLAG> buildingFlags;

    public BuildingFlagsComponent(final Set<BUILDING_FLAG> flags) {
        this.buildingFlags = flags;
    }

    public Set<BUILDING_FLAG> getFlags() {
        return this.buildingFlags;
    }
}
