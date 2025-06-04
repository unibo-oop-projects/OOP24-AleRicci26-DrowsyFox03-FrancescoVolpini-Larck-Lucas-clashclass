package clashclass.battle.manager;

public interface BattleManagerView {
    void setController(BattleManagerController controller);
    void setArmyCampTroops(BattleManagerModel model);
    void updateArmyCampTroopsCount(BattleManagerModel model);
    void clearScene();
}
