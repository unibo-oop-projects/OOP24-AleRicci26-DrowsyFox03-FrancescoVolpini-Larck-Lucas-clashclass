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
import java.util.Objects;
import java.util.Set;

public class PlayerVillageDecoderImpl implements VillageDecoder {
    private ComponentFactory componentFactory;
    private final BuildingFactoryMapper<PlayerBuildingFactoryImpl> mapper;

    public PlayerVillageDecoderImpl(PlayerBuildingFactoryImpl buildingFactory) {
        this.mapper = new BuildingFactoryMapper(Objects.requireNonNull(buildingFactory));
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
        Objects.requireNonNull(componentFactory,
                "ComponentFactory must be set before decoding");

        Set<GameObject> gameObjects = new HashSet<>();

        for (String line : encodedVillage.split("\\R")) {             // \n o \r\n
            line = line.strip();
            if (line.isEmpty() || line.startsWith("TYPE")) continue;

            /* TYPE,INSTANCE_ID,POS_X,POS_Y */
            String[] parts = line.split(",", -1);
            if (parts.length < 4) continue;
            VillageElementData type;

            try {
                type = VillageElementData.valueOf(parts[0]);
            } catch (IllegalArgumentException ex) {
                type = VillageElementData.values()[Integer.parseInt(parts[0])];
            }

            /* p[1] = progressivo per-tipo → ignorato */

            int x = Integer.parseInt(parts[2].trim());
            int y = Integer.parseInt(parts[3].trim());

            GameObject go = mapper.getFactoryFor(type)
                    .apply(new Vector2D(x, y));
            gameObjects.add(go);
        }
        return gameObjects;
    }
}
