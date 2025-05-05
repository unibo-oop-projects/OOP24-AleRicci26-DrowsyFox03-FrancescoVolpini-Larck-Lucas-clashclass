package clashclass.battle.battlereport;

public interface BattleReportModel {

    double getDestructionPercentage();

    void setDestructionPercentage(double percentage);

    void increaseDestructionPercentage();

    int getStars();

    ResourceManager getStolenResources();

    void setStolenResources(ResourceManager resources);

    void addStolenResources(ResourceManager resources);

    boolean isTownHallDestroyed();

    void setTownHallDestroyed(boolean destroyed);

    int getTroopCount();

    void setTroopCount(int count);

    boolean isVictory();
}
