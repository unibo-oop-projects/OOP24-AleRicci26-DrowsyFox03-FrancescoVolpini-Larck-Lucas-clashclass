package clashclass.battle.troopdeath;

import clashclass.commons.HealthComponent;
import clashclass.ecs.AbstractComponent;

import java.util.HashSet;
import java.util.Set;

public class TroopDeathObservableImpl extends AbstractComponent implements TroopDeathObservable {
    private final Set<TroopDeathObserver> observers = new HashSet<TroopDeathObserver>();

    @Override
    public void addObserver(TroopDeathObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(TroopDeathObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void update(float deltaTime) {
        final var healthComponent = this.getGameObject().getComponentOfType(HealthComponent.class)
                .orElseThrow(() -> new RuntimeException("Health component not found"));

        if (healthComponent.isDead()) {
            this.getGameObject().destroy();
            this.observers.forEach(x -> x.notifyDeath(this.getGameObject()));
        }
    }
}
