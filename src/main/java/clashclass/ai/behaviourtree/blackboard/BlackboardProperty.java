package clashclass.ai.behaviourtree.blackboard;

/**
 * Represents a property in a {@link Blackboard}.
 *
 * @param <T> the type of the property
 */
public interface BlackboardProperty<T> {
    /**
     * Gets the property's value.
     *
     * @return the property's value
     */
    T getValue();
}
