package clashclass.ecs;

import java.util.Optional;
import java.util.Set;

public interface GameObject {
    public int getUniqueId();

    public void addComponent(Component component);

    <T extends Component> Optional<T> getComponentOfType(Class<T> componentType);

    public Set<Component> getComponents();
}
