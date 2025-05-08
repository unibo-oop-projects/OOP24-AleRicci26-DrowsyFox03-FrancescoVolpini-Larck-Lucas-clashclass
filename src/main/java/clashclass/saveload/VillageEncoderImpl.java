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

        // Group GameObjects by their type
        Map<VillageElementData, List<GameObject>> groupedByType = groupGameObjectsByType(gameObjects);

        // Generate CSV entries
        groupedByType.forEach((type, objects) -> {
            int instanceId = 1;
            for (GameObject gameObject : objects) {
                Transform2D transform = gameObject.getComponentOfType(Transform2D.class)
                        .orElseThrow(() -> new IllegalStateException("GameObject must have Transform2D"));

                Vector2D position = transform.getPosition();

                builder.append(String.format("%d,%d,%d,%d%s",
                        type.ordinal(),
                        instanceId++,
                        (int) position.x(),
                        (int) position.y(),
                        NEW_LINE));
            }
        });

        return builder.toString();
    }

    private Map<VillageElementData, List<GameObject>> groupGameObjectsByType(Set<GameObject> gameObjects) {
        Map<VillageElementData, List<GameObject>> groupedByType = new EnumMap<>(VillageElementData.class);

        for (GameObject obj : gameObjects) {
            // Try to identify which VillageElementData type this GameObject represents
            Optional<VillageElementData> matchingType = Arrays.stream(VillageElementData.values())
                    .filter(type -> {
                        // Check if this object has components that match this type
                        // This is a simplified approach - you might need a more robust type identification
                        return obj.hasComponent(type.name() + "Component");
                    })
                    .findFirst();

            matchingType.ifPresent(type ->
                    groupedByType.computeIfAbsent(type, k -> new ArrayList<>()).add(obj));
        }

        return groupedByType;
    }
}