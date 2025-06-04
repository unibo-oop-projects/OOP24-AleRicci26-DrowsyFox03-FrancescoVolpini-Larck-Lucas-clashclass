package clashclass.battle.manager;

import clashclass.commons.Vector2D;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.gamestate.GameStateManager;
import clashclass.village.Village;

public interface BattleManagerModel {
    void setGameStateManager(GameStateManager gameStateManager);
    void setCurrentSelectedTroop(TROOP_TYPE troopType);
    GameStateManager getGameStateManager();
    Village getPlayerVillage();
    Village getBattleVillage();
    TROOP_TYPE getCurrentSelectedTroop();
    void createTroop(Vector2D position);
}
