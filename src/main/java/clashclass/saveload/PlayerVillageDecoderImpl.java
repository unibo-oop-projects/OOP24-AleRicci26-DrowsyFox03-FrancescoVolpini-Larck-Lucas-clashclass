package clashclass.saveload;

import clashclass.commons.Vector2D;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactory;
import clashclass.elements.buildings.BuildingFactory;
import clashclass.elements.buildings.BuildingFactoryMapper;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.elements.buildings.VillageElementData;

import java.util.HashSet;
import java.util.Set;

public class PlayerVillageDecoderImpl implements VillageDecoder {
    private ComponentFactory componentFactory;
    private final BuildingFactory buildingFactory;
    private final BuildingFactoryMapper buildingFactoryMapper;

    // Update the constructor
    public PlayerVillageDecoderImpl(PlayerBuildingFactoryImpl buildingFactory) {
        this.buildingFactory = buildingFactory;
        this.buildingFactoryMapper = new BuildingFactoryMapper(buildingFactory);
    }

    /**
     * Converts a VectorInt2D to a Vector2D.
     *
     * @param vector the integer vector to convert
     * @return the equivalent Vector2D
     */
    private Vector2D vectorToVector2D(VectorInt2D vector) {
        return new Vector2D(vector.x(), vector.y());
    }


    @Override
    public void setComponentFactory(ComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
    }

    @Override
    public Set<GameObject> decode(String encodedVillage) {
        if (componentFactory == null) {
            throw new IllegalStateException("ComponentFactory must be set before decoding");
        }

        Set<GameObject> gameObjects = new HashSet<>();
        String[] lines = encodedVillage.split("\n");

        // Skip header
        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(",");
            if (parts.length >= 4) {
                int typeOrdinal = Integer.parseInt(parts[0]);
                int x = Integer.parseInt(parts[2]);
                int y = Integer.parseInt(parts[3]);

                // Get the VillageElementData enum from ordinal
                VillageElementData type = VillageElementData.values()[typeOrdinal];

                // Create GameObject using the player building factory
                GameObject gameObject = buildingFactoryMapper.getFactoryFor(type).apply(vectorToVector2D(new VectorInt2D(x, y)));

            }
        }

        return gameObjects;
    }
}
