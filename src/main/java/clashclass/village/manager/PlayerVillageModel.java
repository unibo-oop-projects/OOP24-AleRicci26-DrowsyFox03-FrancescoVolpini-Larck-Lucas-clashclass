package clashclass.village.manager;

import clashclass.gamestate.GameStateManager;

public interface PlayerVillageModel {
    void setGameStateManager(GameStateManager gameStateManager);
    GameStateManager getGameStateManager();
}
