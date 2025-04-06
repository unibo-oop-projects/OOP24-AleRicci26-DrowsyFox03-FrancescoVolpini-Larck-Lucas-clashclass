package clashclass.resources;

public class Player {
    private final int GENERIC_VALUE = 10;
    public ResourceManagerImpl gold = new ResourceManagerImpl(GENERIC_VALUE);
    public ResourceManagerImpl elixir = new ResourceManagerImpl(GENERIC_VALUE);
    public ResourceManagerImpl gems = new ResourceManagerImpl(GENERIC_VALUE);
}
