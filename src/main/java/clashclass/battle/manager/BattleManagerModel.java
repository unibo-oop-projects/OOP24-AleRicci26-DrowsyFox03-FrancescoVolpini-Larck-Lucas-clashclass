package clashclass.battle.manager;

import clashclass.elements.troops.TROOP_TYPE;
import clashclass.gamestate.GameStateManager;
import clashclass.village.Village;

public interface BattleManagerModel {
    void setGameStateManager(GameStateManager gameStateManager);
    void setCurrentSelectedTroop(TROOP_TYPE troopType);
    GameStateManager getGameStateManager();
    Village getBattleVillage();
    TROOP_TYPE getCurrentSelectedTroop();
}
