package clashclass.commons;

import clashclass.ecs.AbstractComponent;

/**
 * Represents an HealthComponent implementation.
 */
public class HealthComponentImpl extends AbstractComponent implements HealthComponent {
    private final int maxValue;
    private int currentValue;

    /**
     * Constructs the health component.
     *
     * @param maxValue the value of maximum health, used also for initialize the current health value
     */
    public HealthComponentImpl(final int maxValue) {
        this.maxValue = maxValue;
        this.currentValue = maxValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decrease(final int amount) {
        this.currentValue = Math.max(currentValue - amount, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increase(final int amount) {
        this.currentValue = Math.min(currentValue + amount, this.maxValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return this.currentValue == 0;
    }
}
