package clashclass.village.manager;

import clashclass.gamestate.GameStateManager;
import clashclass.village.Village;

public interface PlayerVillageModel {
    void setGameStateManager(GameStateManager gameStateManager);
    GameStateManager getGameStateManager();
    Village getPlayerVillage();
}
