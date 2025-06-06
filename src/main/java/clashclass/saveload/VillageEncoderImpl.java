package clashclass.saveload;

import clashclass.commons.BuildingTypeComponent;
import clashclass.commons.Transform2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a {@link VillageEncoder} implementation.
 */
public class VillageEncoderImpl implements VillageEncoder {
    private static final String CSV_DELIMITER = ",";
    private static final String NEW_LINE = "\n";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHeader() {
        return "TYPE,INSTANCE_ID,POS_X,POS_Y" + NEW_LINE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode(final Set<GameObject> gameObjects) {
        final StringBuilder builder = new StringBuilder(getHeader());

        final Map<VillageElementData, Integer> counters = new EnumMap<>(VillageElementData.class);

        for (final GameObject gameObject : gameObjects) {
            // Determine the type of this GameObject
            final Optional<VillageElementData> typeOpt = determineType(gameObject);

            if (typeOpt.isPresent()) {
                final VillageElementData type = typeOpt.get();

                final int progressive = counters.merge(type, 1, (prev, inc) -> prev + 1);
                // Get the transform for position info
                final Transform2D transform = gameObject.getComponentOfType(Transform2D.class)
                        .orElseThrow(() -> new IllegalStateException("GameObject must have Transform2D"));

                final int x = (int) transform.getPosition().x();
                final int y = (int) transform.getPosition().y();

                builder.append(type.getName())
                        .append(CSV_DELIMITER)
                        .append(progressive)
                        .append(CSV_DELIMITER)
                        .append(x)
                        .append(CSV_DELIMITER)
                        .append(y)
                        .append(NEW_LINE);
            }

        }
        return builder.toString();
    }

    private Optional<VillageElementData> determineType(final GameObject gameObject) {
        return gameObject.getComponentOfType(BuildingTypeComponent.class)
                .map(BuildingTypeComponent::getBuildingType);
    }
}
