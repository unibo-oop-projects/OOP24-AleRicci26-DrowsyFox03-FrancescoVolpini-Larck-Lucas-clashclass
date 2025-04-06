package clashclass.resources;

import java.time.temporal.ValueRange;

public class ResourceManagerImpl implements ResourceManager {
    private final int MAX_VALUE;
    private int currentValue;

    public ResourceManagerImpl(final int MAX_VALUE) {
        this.MAX_VALUE = MAX_VALUE;
        this.currentValue = MAX_VALUE;
    }
    @Override
    public void increase(int value) {
        int updatedValue = this.currentValue + value;
        if (ValueRange.of(0, MAX_VALUE).isValidIntValue(updatedValue)) {
            this.currentValue = updatedValue;
        } else {
            System.out.println("Value is out of the Range.");
        }
    }

    @Override
    public void decrease(int value) {
        int updatedValue = this.currentValue - value;
        if (ValueRange.of(0, MAX_VALUE).isValidIntValue(updatedValue)) {
            this.currentValue = updatedValue;
        } else {
            System.out.println("Value is out of the Range.");
        }
    }

    @Override
    public int getCurrentValue() {
        return this.currentValue;
    }


}
