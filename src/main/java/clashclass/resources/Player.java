package clashclass.resources;

import java.util.EnumMap;

/**
 * Class that provide the player with different resources.
 */
public class Player {
    private static final int GENERIC_VALUE = 10;
    private final EnumMap<RESOURCE_TYPE, ResourceManagerImpl>
            playerResources = new EnumMap<>(RESOURCE_TYPE.class);

    public Player() {
        ResourceManagerImpl goldManager = new ResourceManagerImpl(GENERIC_VALUE);
        ResourceManagerImpl elixirManager = new ResourceManagerImpl(GENERIC_VALUE);
        ResourceManagerImpl gemsManager = new ResourceManagerImpl(GENERIC_VALUE);

        playerResources.put(RESOURCE_TYPE.GOLD, goldManager);
        playerResources.put(RESOURCE_TYPE.ELIXIR, elixirManager);
        playerResources.put(RESOURCE_TYPE.GEMS, gemsManager);
    }

    public EnumMap<RESOURCE_TYPE, ResourceManagerImpl> getPlayerResources () {
        return this.playerResources;
    }
}