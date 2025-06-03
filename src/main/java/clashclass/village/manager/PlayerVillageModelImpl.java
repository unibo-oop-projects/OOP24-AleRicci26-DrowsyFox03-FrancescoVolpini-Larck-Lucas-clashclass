package clashclass.village.manager;

import clashclass.gamestate.GameStateManager;

public class PlayerVillageModelImpl implements PlayerVillageModel {
    private GameStateManager gameStateManager;

    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    @Override
    public GameStateManager getGameStateManager() {
        return this.gameStateManager;
    }
}
