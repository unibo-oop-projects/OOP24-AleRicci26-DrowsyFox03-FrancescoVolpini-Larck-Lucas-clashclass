package clashclass.resources;

/**
 * Represents a ResourceManager implementation.
 */
public class ResourceManagerImpl implements ResourceManager {
    private final double MAX_VALUE;
    private double currentValue;

    /**
     * Construct ResourceManager.
     *
     * @param MAX_VALUE the maximum value of the resource
     */
    public ResourceManagerImpl(final int MAX_VALUE) {
        this.MAX_VALUE = MAX_VALUE;
        this.currentValue = MAX_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increase(double value) {
        this.currentValue = Math.min(MAX_VALUE, this.currentValue - value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decrease(double value) {
        this.currentValue = Math.max(0, this.currentValue - value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentValue() {
        return this.currentValue;
    }
}
