package clashclass.resources;

public class ResourceManagerImpl implements ResourceManager {
    private final int MAX_VALUE;
    private int currentValue;

    public ResourceManagerImpl(final int MAX_VALUE) {
        this.MAX_VALUE = MAX_VALUE;
        this.currentValue = MAX_VALUE;
    }
    @Override
    public void increase(int value) {
        this.currentValue = Math.min(MAX_VALUE, this.currentValue - value);
    }

    @Override
    public void decrease(int value) {
        this.currentValue = Math.max(0, this.currentValue - value);
    }

    @Override
    public int getCurrentValue() {
        return this.currentValue;
    }


}
