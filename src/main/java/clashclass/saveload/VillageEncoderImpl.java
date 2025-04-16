package clashclass.saveload;

import clashclass.commons.Transform2D;
import clashclass.commons.Vector2D;
import clashclass.ecs.Component;
import clashclass.ecs.GameObject;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class VillageEncoderImpl implements VillageEncoder {
    private static final String CSV_DELIMITER= ",";
    private static final String NEW_LINE= "\n";

    @Override
    public String encode(Set<GameObject> gameObjects) {
        StringBuilder builder = new StringBuilder();

        // Add header
        builder.append("Type,PosX,PosY,Properties")
                .append(NEW_LINE);

        // Encode each GameObject
        gameObjects.forEach(gameObject -> {
            Transform2D transform = gameObject.getComponentOfType(Transform2D.class)
                    .orElseThrow(() -> new IllegalStateException("GameObject must have Transform2D"));

            Vector2D position = transform.getPosition();

            builder.append(getGameObjectType(gameObject)) //TO BE: use enum class for unique ID
                    .append(CSV_DELIMITER)
                    .append(position.x())
                    .append(CSV_DELIMITER)
                    .append(position.y())
                    .append(CSV_DELIMITER)
                    .append(encodeProperties(gameObject))
                    .append(NEW_LINE);
        });

        return builder.toString();

    }

    private String getGameObjectType(GameObject gameObject) {
        // Implement type detection logic based on components
        return gameObject.getComponents().stream()
                .map(component -> component.getClass().getSimpleName())
                .collect(Collectors.joining(";"));

    }

    private String encodeProperties(GameObject gameObject) {
        // Encode additional properties like health, damage, etc.
        return gameObject.getComponents().stream()
                .map(this::encodeComponent)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(";"));

    }
    private String encodeComponent(Component component) {
        // Add specific component encoding logic here
        return null;
    }

}
