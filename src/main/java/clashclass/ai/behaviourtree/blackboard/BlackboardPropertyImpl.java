package clashclass.ai.behaviourtree.blackboard;

/**
 * Represents a {@link BlackboardProperty} implementation.
 *
 * @param <T> the type of the property
 */
public class BlackboardPropertyImpl<T> implements BlackboardProperty<T> {
    private final T value;
    private final Class<T> type;

    /**
     * Constructs the blackboard property.
     *
     * @param value the value of the property
     * @param type the type of the property
     */
    public BlackboardPropertyImpl(final T value, final Class<T> type) {
        this.value = value;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public Class<T> getType() {
        return type;
    }
}
