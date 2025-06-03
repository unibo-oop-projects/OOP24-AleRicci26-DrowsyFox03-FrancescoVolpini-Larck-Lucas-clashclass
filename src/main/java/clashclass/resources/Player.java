package clashclass.resources;

import clashclass.elements.troops.TROOP_TYPE;

import java.util.EnumMap;
import java.util.Set;

/**
 * Class that provide the player with different resources.
 */
public class Player {
    private static final int GENERIC_VALUE = 10;
    private final EnumMap<RESOURCE_TYPE, ResourceManager>
            playerResources = new EnumMap<>(RESOURCE_TYPE.class);
    private final EnumMap<TROOP_TYPE, Integer> armyCampTroops = new EnumMap<>(TROOP_TYPE.class);

    public Player() {
        ResourceManager goldManager = new ResourceManagerImpl(GENERIC_VALUE);
        ResourceManager elixirManager = new ResourceManagerImpl(GENERIC_VALUE);
        ResourceManager gemsManager = new ResourceManagerImpl(GENERIC_VALUE);

        playerResources.put(RESOURCE_TYPE.GOLD, goldManager);
        playerResources.put(RESOURCE_TYPE.ELIXIR, elixirManager);
        playerResources.put(RESOURCE_TYPE.GEMS, gemsManager);
    }

    public EnumMap<RESOURCE_TYPE, ResourceManager> getPlayerResources() {
        return playerResources;
    }

    public void addArmyCampTroop(final TROOP_TYPE troopType, int count) {
        if (this.armyCampTroops.containsKey(troopType)) {
            this.armyCampTroops.put(troopType, this.armyCampTroops.get(troopType) + count);
            return;
        }
        this.armyCampTroops.put(troopType, count);
    }

    public void removeArmyCampTroop(final TROOP_TYPE troopType, int count) {
        if (this.armyCampTroops.containsKey(troopType)) {
            final var newCount = this.armyCampTroops.get(troopType) - count;

            if (newCount < 0) {
                this.armyCampTroops.remove(troopType);
            } else {
                this.armyCampTroops.put(troopType, newCount);
            }
        }
    }

    public Set<TROOP_TYPE> getArmyCampTroopTypes() {
        return this.armyCampTroops.keySet();
    }
}