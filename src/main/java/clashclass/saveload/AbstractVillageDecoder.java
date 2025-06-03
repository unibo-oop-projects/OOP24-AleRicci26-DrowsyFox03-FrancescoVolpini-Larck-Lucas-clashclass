package clashclass.saveload;

import clashclass.commons.VectorInt2D;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.village.Village;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactory;
import clashclass.elements.buildings.VillageElementData;
import clashclass.resources.Player;
import clashclass.resources.RESOURCE_TYPE;

import java.util.Objects;

public abstract class AbstractVillageDecoder implements VillageDecoder {

        protected ComponentFactory componentFactory;

        @Override
        public void setComponentFactory(ComponentFactory componentFactory) {
            this.componentFactory = componentFactory;
        }

        @Override
        public Village decode(String encodedVillage) {
            Objects.requireNonNull(componentFactory, "ComponentFactory must be set before decoding");

            final Player player = new Player();
            final Village village = new Village(player);

            final var lines = encodedVillage.split("\\R");

            for (String line : lines) {
                line = line.strip();
                if (line.isEmpty() || line.startsWith("ResourceType")) continue;
                if (line.startsWith("TroopType")) break;

                String[] parts = line.split(",", -1);
                if (parts.length < 3) continue;

                try {
                    RESOURCE_TYPE type = RESOURCE_TYPE.valueOf(parts[0].trim());
                    int current = Integer.parseInt(parts[1].trim());
                    int max = Integer.parseInt(parts[2].trim());

                    player.getPlayerResources().get(type).increase(current);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid resource type or values: " + line, e);
                }
            }

            boolean troopsSectionStarted = false;

            for (String line : lines) {
                line = line.strip();
                if (!troopsSectionStarted) {
                    if (!line.startsWith("TroopType")) continue;

                    troopsSectionStarted = true;
                    continue;
                }

                if (line.startsWith("TYPE")) break;

                String[] parts = line.split(",", -1);
                if (parts.length < 2) continue;

                TROOP_TYPE type = TROOP_TYPE
                        .getValueFromName(parts[0])
                        .orElseThrow(() -> new IllegalArgumentException("Invalid type: " + parts[0]));

                int count = Integer.parseInt(parts[1].trim());
                player.addArmyCampTroop(type, count);
            }

            boolean buildingsSectionStarted = false;

            for (String line : lines) {
                line = line.strip();
                if (!buildingsSectionStarted) {
                    if (!line.startsWith("TYPE")) continue;

                    buildingsSectionStarted = true;
                    continue;
                }

                String[] parts = line.split(",", -1);
                if (parts.length < 4) continue;

                VillageElementData type = VillageElementData
                        .getValueFromName(parts[0])
                        .orElseThrow(() -> new IllegalArgumentException("Invalid type: " + parts[0]));

                int x = Integer.parseInt(parts[2].trim());
                int y = Integer.parseInt(parts[3].trim());

                final GameObject go = createGameObject(type, new VectorInt2D(x, y));
                village.placeBuilding(go);
            }
            return village;
        }

        protected abstract GameObject createGameObject(VillageElementData type, VectorInt2D position);
    }