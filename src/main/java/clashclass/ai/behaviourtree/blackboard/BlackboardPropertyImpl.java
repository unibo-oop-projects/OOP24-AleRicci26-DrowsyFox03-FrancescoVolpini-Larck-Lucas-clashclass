package clashclass.ai.behaviourtree.blackboard;

/**
 * Represents a {@link BlackboardProperty} implementation.
 *
 * @param <T> the type of the property
 */
public class BlackboardPropertyImpl<T> implements BlackboardProperty<T> {
    private final T value;

    /**
     * Constructs the blackboard property.
     *
     * @param value the value of the property
     */
    public BlackboardPropertyImpl(final T value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getValue() {
        return this.value;
    }
}
