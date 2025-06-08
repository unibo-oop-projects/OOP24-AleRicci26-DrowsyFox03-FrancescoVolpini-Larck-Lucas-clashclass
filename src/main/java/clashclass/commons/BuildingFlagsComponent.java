package clashclass.commons;

import clashclass.ecs.AbstractComponent;
import clashclass.elements.buildings.BuildingFlag;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Set;

/**
 * Represents a component which holds some flag useful for the buildings.
 */
public class BuildingFlagsComponent extends AbstractComponent {
    private final Set<BuildingFlag> buildingFlags;

    /**
     * Constructs the component.
     *
     * @param flags the set of flags
     */
    @SuppressFBWarnings(value = "EI2", justification = "Intentional set")
    public BuildingFlagsComponent(final Set<BuildingFlag> flags) {
        this.buildingFlags = flags;
    }

    /**
     * Gets the set of flags.
     *
     * @return the set of flags
     */
    @SuppressFBWarnings(value = "EI", justification = "Intentional access")
    public Set<BuildingFlag> getFlags() {
        return this.buildingFlags;
    }
}
