package clashclass.ai.behaviourtree.blackboard;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a {@link Blackboard} implementation.
 */
public class BlackboardImpl implements Blackboard {
    private final Map<String, BlackboardProperty<?>> map;

    /**
     * Constructs the blackboard.
     */
    public BlackboardImpl() {
        this.map = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProperty(final String name, final BlackboardProperty<?> value) {
        this.map.put(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> BlackboardProperty<T> getProperty(final String name, final Class<T> type) {
        final var property = map.get(name);
        if (property == null || !type.isAssignableFrom(property.getType())) {
            return null;
        }
        return (BlackboardProperty<T>) property;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasProperty(final String name) {
        return this.map.containsKey(name);
    }
}
