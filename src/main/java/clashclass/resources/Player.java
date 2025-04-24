package clashclass.resources;

/**
 * Class that provide the player with different resources.
 */
public class Player {
    private static final int GENERIC_VALUE = 10;
    public enum ResourceType {
        GOLD,
        ELIXIR,
        GEMS;

        public final ResourceManagerImpl resource;
        ResourceType(){
            resource = new ResourceManagerImpl(GENERIC_VALUE);
        }
    }
}
