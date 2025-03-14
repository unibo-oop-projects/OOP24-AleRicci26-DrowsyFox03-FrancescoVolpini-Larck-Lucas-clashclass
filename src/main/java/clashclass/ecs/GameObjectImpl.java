package clashclass.ecs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GameObjectImpl implements GameObject {
    private final int uniqueId;
    private Set<Component> components;

    private static int uniqueIdProgression = 0;

    public GameObjectImpl() {
        this.uniqueId = uniqueIdProgression++;
        this.components = new HashSet<>();
    }

    @Override
    public final int getUniqueId() {
        return this.uniqueId;
    }

    @Override
    public final void addComponent(Component component) {
        this.components.add(component);
    }

    @Override
    public final <T extends Component> Optional<T> getComponentOfType(Class<T> componentType) {
        return Optional.empty();
    }

    @Override
    public final Set<Component> getComponents() {
        return Collections.unmodifiableSet(components);
    }
}
