package clashclass.saveload;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;

import java.util.HashSet;
import java.util.Set;

public class VillageDecoderImpl implements VillageDecoder {
    @Override
    public Set<GameObject> decode(String encodedVillage) {
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

                // Create the GameObject using the existing factory method
                GameObject gameObject = VillageElementData.getFactory(type)
                        .apply(new VectorInt2D(x, y));

                gameObjects.add(gameObject);
            }
        }

        return gameObjects;

    }
}
