package clashclass.gamestate;

import clashclass.village.Village;

public class GameStateManagerImpl implements GameStateManager {
    private final Village playerVillage;
    private GameStateController currentGameStateController;

    public GameStateManagerImpl(final Village playerVillage) {
        this.playerVillage = playerVillage;
    }

    @Override
    public void setStatePlayerVillage() {

    }

    @Override
    public void setStateBattle() {

    }
}
