package clashclass.battle.troopdeath;

public interface EndBattleAllTroopsDead extends TroopDeathObserver {

    boolean isAllTroopsDead();

    void setTroopCount(int count);
}