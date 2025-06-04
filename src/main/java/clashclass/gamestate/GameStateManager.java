package clashclass.gamestate;

import clashclass.engine.GameEngine;

public interface GameStateManager {
    void setStatePlayerVillage();
    void setStateBattle();
    GameEngine getGameEngine();
    void startEngine();
    void stopEngine();
}
