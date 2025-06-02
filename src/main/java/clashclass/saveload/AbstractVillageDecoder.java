package clashclass.saveload;

import clashclass.commons.Vector2D;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactory;
import clashclass.elements.buildings.VillageElementData;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractVillageDecoder implements VillageDecoder {

        protected ComponentFactory componentFactory;

        @Override
        public void setComponentFactory(ComponentFactory componentFactory) {
            this.componentFactory = componentFactory;
        }

        @Override
        public Set<GameObject> decode(String encodedVillage) {
            Objects.requireNonNull(componentFactory, "ComponentFactory must be set before decoding");

            Set<GameObject> result = new HashSet<>();
            for (String line : encodedVillage.split("\\R")) {
                line = line.strip();
                if (line.isEmpty() || line.startsWith("TYPE")) continue;

                String[] parts = line.split(",", -1);
                if (parts.length < 4) continue;

                VillageElementData type = VillageElementData
                        .getValueFromName(parts[0])
                        .orElseThrow(() -> new IllegalArgumentException("Invalid type: " + parts[0]));

                int x = Integer.parseInt(parts[2].trim());
                int y = Integer.parseInt(parts[3].trim());

                GameObject go = createGameObject(type, new VectorInt2D(x, y));
                result.add(go);
            }
            return result;
        }

        protected abstract GameObject createGameObject(VillageElementData type, VectorInt2D position);
    }