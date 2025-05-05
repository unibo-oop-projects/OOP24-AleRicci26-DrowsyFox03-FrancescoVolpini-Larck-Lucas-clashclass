package clashclass.battle.battlereport;

public interface BattleReportController {

    void increaseDestructionPercentage();

    void increaseStolenResources(ResourceManager resourceManager);

    void setTroopCount(int count);
}
