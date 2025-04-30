package clashclass.ai.behaviourtree.blackboard;

/**
 * Represents an object where BehaviourTree nodes can write on and read from.
 */
public interface Blackboard {
    /**
     * Sets a property in the blackboard.
     *
     * @param name the name of the property
     * @param value the value of the property
     */
    void setProperty(String name, BlackboardProperty<?> value);

    /**
     * Gets a property in the blackboard.
     *
     * @param name the name of the property
     *
     * @return the value of the property
     */
    BlackboardProperty<?> getProperty(String name);

    /**
     * Checks whether a property exists in the blackboard.
     *
     * @param name the name of the property
     *
     * @return the value of the property
     */
    boolean hasProperty(String name);
}
