package clashclass.battle.destruction;

import clashclass.commons.HealthComponent;
import clashclass.ecs.AbstractComponent;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a {@link DestructionObservable} implementation.
 */
public class DestructionObservableImpl extends AbstractComponent implements DestructionObservable {
    private final Set<DestructionObserver> observers;

    /**
     * Constructs the observable.
     */
    public DestructionObservableImpl() {
        observers = new HashSet<DestructionObserver>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(DestructionObserver observer) {
        this.observers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(DestructionObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float deltaTime) {
        final var healthComponent = this.getGameObject().getComponentOfType(HealthComponent.class)
                .orElseThrow(() -> new RuntimeException("Health component not found"));

        if (healthComponent.isDead()) {
            this.getGameObject().destroy();
            this.observers.forEach(x -> x.notifyDestruction(this.getGameObject()));
        }
    }
}
