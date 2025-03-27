package clashclass.ecs;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Collections;

/**
 * Represents an implementation of GameObject.
 */
public class GameObjectImpl implements GameObject {
    // private static int uniqueIdProgression = 0;

    private final int uniqueId;
    private final Set<Component> components;
    private boolean destroyedFlag;

    /**
     * Constructs the GameObject.
     */
    public GameObjectImpl() {
        // this.uniqueId = uniqueIdProgression++;
        this.uniqueId = 0;
        this.components = new LinkedHashSet<>();
        this.destroyedFlag = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getUniqueId() {
        return this.uniqueId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addComponent(final Component component) {
        component.setGameObject(this);
        this.components.add(component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <T extends Component> Optional<T> getComponentOfType(final Class<T> componentType) {
        return this.components.stream()
                .filter(componentType::isInstance)
                .map(componentType::cast)
                .findFirst();
    }

    /**
     * Gets an immutable representation of all the components.
     *
     * @return an immutable representation of all the components
     */
    @Override
    public final Set<Component> getComponents() {
        return Collections.unmodifiableSet(components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void destroy() {
        this.destroyedFlag = true;
    }

    @Override
    public final boolean isMarkedAsDestroyed() {
        return this.destroyedFlag;
    }

    /**
     * Represents a GameObject.Builder implementation.
     */
    static class BuilderImpl implements Builder {
        private final Set<Component> components;

        /**
         * Constructs the Builder.
         */
        BuilderImpl() {
            this.components = new LinkedHashSet<>();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder addComponent(final Component component) {
            this.components.add(component);
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public GameObject build() {
            if (this.components.isEmpty()) {
                throw new IllegalStateException("There must be at least one component");
            }

            final var gameObject = new GameObjectImpl();
            this.components.forEach(gameObject::addComponent);

            return gameObject;
        }
    }
}
