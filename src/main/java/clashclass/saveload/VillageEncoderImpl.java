package clashclass.saveload;

import clashclass.commons.Transform2D;
import clashclass.commons.Vector2D;
import clashclass.ecs.Component;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;

import java.util.*;

public class VillageEncoderImpl implements VillageEncoder {
    private static final String CSV_DELIMITER = ",";
    private static final String NEW_LINE = "\n";

    @Override
    public String getHeader() {
        return "TYPE,INSTANCE_ID,POS_X,POS_Y" + NEW_LINE;
    }

    @Override
    public String encode(Set<GameObject> gameObjects) {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader());

        Map<VillageElementData, Integer> counters = new EnumMap<>(VillageElementData.class);


        for (GameObject gameObject : gameObjects) {
            // Determine the type of this GameObject
            Optional<VillageElementData> typeOpt = determineType(gameObject);

            if (typeOpt.isPresent()) {
                VillageElementData type = typeOpt.get();

                // Get the transform for position info
                Transform2D transform = gameObject.getComponentOfType(Transform2D.class)
                        .orElseThrow(() -> new IllegalStateException("GameObject must have Transform2D"));

                Vector2D position = transform.getPosition();

                builder.append(String.format("%d,%d,%d,%d%s",
                        type.ordinal(),
                        gameObject.getUniqueId(),
                        (int) position.x(),
                        (int) position.y(),
                        NEW_LINE));
            }

        }
        return builder.toString();
    }

    private Optional<VillageElementData> determineType(GameObject gameObject) {
        // Try to identify which VillageElementData type this GameObject represents
        for (VillageElementData type : VillageElementData.values()) {
            String componentName = type.name() + "Component";
            if (gameObject.getComponents().stream()
                    .anyMatch(c -> c.getClass().getSimpleName().equals(componentName))) {
                return Optional.of(type);
            }
        }
        return Optional.empty();

    }
}