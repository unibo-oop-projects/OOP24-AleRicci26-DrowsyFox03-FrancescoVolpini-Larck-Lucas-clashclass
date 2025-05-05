package clashclass.battle.battlereport;

public interface BattleReportView {

    void update(BattleReportModel model);

    void displayDestructionPercentage(double percentage);

    void displayStars(int stars);

    void displayStolenResources(ResourceManager resources);

    void displayBattleResult(boolean isVictory);

    void displayTroopCount(int troopCount);
}
