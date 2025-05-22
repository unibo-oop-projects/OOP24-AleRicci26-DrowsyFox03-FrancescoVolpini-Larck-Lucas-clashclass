package clashclass.battle.battlereport;

import clashclass.resources.ResourceManager;
import clashclass.resources.ResourceManagerImpl;

public class BattleReportModelImpl implements BattleReportModel {
    private double destructionPercentage;
    private final ResourceManager stolenGold;
    private final ResourceManager stolenElixir;
    private boolean townHallDestroyed;
    private final int totalBuildings;
    private int destroyedBuildings;
    private int troopCount;

    private static final int INITIAL_RESOURCE_CAPACITY = 1000000; // 1 million capacity

    public BattleReportModelImpl(final int totalBuildings) {
        this.totalBuildings = totalBuildings;
        this.destroyedBuildings = 0;
        this.destructionPercentage = 0.0;
        this.townHallDestroyed = false;
        this.troopCount = 0;
        this.stolenGold = new ResourceManagerImpl(INITIAL_RESOURCE_CAPACITY);
        this.stolenElixir = new ResourceManagerImpl(INITIAL_RESOURCE_CAPACITY);
    }

    @Override
    public double getDestructionPercentage() {
        return destructionPercentage;
    }

    @Override
    public void setDestructionPercentage(final double percentage) {
        this.destructionPercentage = validatePercentage(percentage);
    }

    @Override
    public void increaseDestructionPercentage() {
        destroyedBuildings++;
        this.destructionPercentage = validatePercentage((double) destroyedBuildings / totalBuildings * 100.0);
    }

    @Override
    public int getStars() {
        int stars = 0;
        if (destructionPercentage >= 50) stars++;
        if (townHallDestroyed) stars++;
        if (destructionPercentage >= 100) stars++;
        return stars;
    }

    @Override
    public ResourceManager getStolenGold() {
        return stolenGold;
    }

    @Override
    public ResourceManager getStolenElixir() {
        return stolenElixir;
    }

    @Override
    public void addStolenGold(ResourceManager goldStorage) {
        if (goldStorage != null) {
            int stolenAmount = calculateStolenAmount(goldStorage, destructionPercentage);
            stolenGold.increase(stolenAmount);
        }
    }

    @Override
    public void addStolenElixir(ResourceManager elixirStorage) {
        if (elixirStorage != null) {
            int stolenAmount = calculateStolenAmount(elixirStorage, destructionPercentage);
            stolenElixir.increase(stolenAmount);
        }
    }

    @Override
    public boolean isTownHallDestroyed() {
        return townHallDestroyed;
    }

    @Override
    public void setTownHallDestroyed(final boolean destroyed) {
        this.townHallDestroyed = destroyed;
    }

    @Override
    public int getTroopCount() {
        return troopCount;
    }

    @Override
    public void setTroopCount(final int count) {
        this.troopCount = Math.max(0, count);
    }

    @Override
    public boolean isVictory() {
        return getStars() > 0;
    }

    /**
     * Validates and constrains percentage between 0 and 100
     * @param percentage Value to validate
     * @return Validated percentage
     */
    private double validatePercentage(double percentage) {
        return Math.min(100.0, Math.max(0.0, percentage));
    }

    /**
     * Calculates the amount of resources that can be stolen based on destruction percentage
     * @param storage Original resource storage
     * @param percentage Destruction percentage
     * @return Amount that can be stolen
     */
    private int calculateStolenAmount(ResourceManager storage, double percentage) {
        if (storage == null) {
            return 0;
        }
        int originalAmount = storage.getCurrentValue();
        return (int) (originalAmount * (validatePercentage(percentage) / 100.0));
    }
}