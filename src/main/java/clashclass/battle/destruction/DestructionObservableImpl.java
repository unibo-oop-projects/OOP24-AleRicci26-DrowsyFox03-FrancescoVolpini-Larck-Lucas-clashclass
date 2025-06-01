package clashclass.battle.destruction;

import clashclass.commons.HealthComponent;
import clashclass.ecs.AbstractComponent;

import java.util.HashSet;
import java.util.Set;

public class DestructionObservableImpl extends AbstractComponent implements DestructionObservable {
    private Set<DestructionObserver> observers;

    public DestructionObservableImpl() {
        observers = new HashSet<DestructionObserver>();
    }

    @Override
    public void addObserver(DestructionObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(DestructionObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void update(float deltaTime) {
        final var healthComponent = this.getGameObject().getComponentOfType(HealthComponent.class)
                .orElseThrow(() -> new RuntimeException("Health component not found"));

        if (healthComponent.isDead()) {
            this.observers.forEach(x -> x.notifyDestruction(this.getGameObject()));
        }
    }
}
