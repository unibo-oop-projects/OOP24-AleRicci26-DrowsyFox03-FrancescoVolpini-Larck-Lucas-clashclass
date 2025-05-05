package clashclass.battle.battlereport;

public class BattleReportModelImpl implements BattleReportModel {

    private double destructionPercentage;
    private ResourceManager stolenResources;
    private boolean townHallDestroyed;
    private int totalBuildings;
    private int destroyedBuildings;
    private int troopCount;

    public BattleReportModelImpl(final int totalBuildings) {
        this.destructionPercentage = 0.0;
        this.stolenResources = new ResourceManager();
        this.townHallDestroyed = false;
        this.totalBuildings = totalBuildings;
        this.destroyedBuildings = 0;
        this.troopCount = 0;
    }

    @Override
    public double getDestructionPercentage() {
        return destructionPercentage;
    }

    @Override
    public void setDestructionPercentage(final double percentage) {
        this.destructionPercentage = Math.min(100.0, Math.max(0.0, percentage));
    }

    @Override
    public void increaseDestructionPercentage() {
        destroyedBuildings++;
        this.destructionPercentage = (double) destroyedBuildings / totalBuildings * 100.0;
        // Ensure percentage doesn't exceed 100%
        this.destructionPercentage = Math.min(100.0, this.destructionPercentage);
    }

    @Override
    public int getStars() {
        int stars = 0;

        // 1 star for 50% destruction
        if (destructionPercentage >= 50.0) {
            stars++;
        }

        // 2 stars if Town Hall is destroyed
        if (townHallDestroyed) {
            stars++;
        }

        // 3 stars for 100% destruction
        if (destructionPercentage >= 100.0) {
            stars++;
        }

        return stars;
    }

    @Override
    public ResourceManager getStolenResources() {
        return stolenResources;
    }

    @Override
    public void setStolenResources(final ResourceManager resources) {
        this.stolenResources = resources;
    }

    @Override
    public void addStolenResources(final ResourceManager resources) {
        this.stolenResources.addResources(resources);
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
        this.troopCount = count;
    }

    @Override
    public boolean isVictory() {
        return getStars() > 0;
    }
}
