package clashclass.gamestate;

import clashclass.engine.GameEngine;
import clashclass.village.Village;

public interface GameStateManager {
    void setStatePlayerVillage();
    void setStateBattle();
    GameEngine getGameEngine();
    void startEngine();
    void stopEngine();
}
