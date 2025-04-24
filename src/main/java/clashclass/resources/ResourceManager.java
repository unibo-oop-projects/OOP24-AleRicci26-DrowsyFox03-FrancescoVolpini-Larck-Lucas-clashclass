package clashclass.resources;

/**
 * Represents the in-game resources.
 */
public interface ResourceManager {
    /**
     * Increase the amount of resource by a given value.
     *
     * @param value the amount of resource to increase
     */
    void increase (int value);

    /**
     * Decrease the amount of resource by a given value.
     *
     * @param value the amount of resource to decrease
     */
    void decrease (int value);

    /**
     * Returns the current value of the resource.
     *
     * @return the resource value as an int
     */
    int getCurrentValue();
}
