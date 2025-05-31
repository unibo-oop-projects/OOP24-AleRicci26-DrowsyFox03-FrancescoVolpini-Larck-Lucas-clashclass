package clashclass.ai.logic;

public interface CalculateDamageLogicFactory {
    DamageLogicComponent createForBarbarian();
    DamageLogicComponent createForArcher();
    DamageLogicComponent createForCannon();
    DamageLogicComponent createForArcherTower();
}
