package clashclass.saveload;

import clashclass.commons.Transform2D;
import clashclass.commons.Vector2D;
import clashclass.ecs.Component;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;

import java.util.*;
import java.util.stream.Collectors;

public class VillageEncoderImpl implements VillageEncoder {
    private static final String CSV_DELIMITER= ",";
    private static final String NEW_LINE= "\n";

    @Override
    public String getHeader() {
        return "TYPE,INSTANCE_ID,POS_X,POS_Y" + NEW_LINE;
    }

    @Override
    public String encode(Set<GameObject> gameObjects) {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader());

        // Group GameObjects by their type by checking which factory created them
        Map<VillageElementData, List<GameObject>> groupedByType = new EnumMap<>(VillageElementData.class);

        // Group objects by type
        for (GameObject obj : gameObjects) {
            for (VillageElementData type : VillageElementData.values()) {
                if (VillageElementData.getFactory(type).equals(obj)) {
                    groupedByType.computeIfAbsent(type, k -> new ArrayList<>()).add(obj);
                    break;
                }
            }
        }

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
}


