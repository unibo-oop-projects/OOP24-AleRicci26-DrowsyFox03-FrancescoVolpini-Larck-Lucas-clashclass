package clashclass.saveload;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactory;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.buildings.BuildingFactory;
import clashclass.elements.buildings.VillageElementData;

import java.util.HashSet;
import java.util.Set;

public class BattleVillageDecoderImpl implements VillageDecoder {
    private ComponentFactory componentFactory;
    private final BuildingFactory buildingFactory;

    public BattleVillageDecoderImpl(BattleBuildingFactoryImpl buildingFactory) {
        this.buildingFactory = buildingFactory;
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

                // Create GameObject using the battle building factory
                GameObject gameObject = buildingFactory.createBuilding(type, new VectorInt2D(x, y));
                gameObjects.add(gameObject);
            }
        }

        return gameObjects;
    }
}