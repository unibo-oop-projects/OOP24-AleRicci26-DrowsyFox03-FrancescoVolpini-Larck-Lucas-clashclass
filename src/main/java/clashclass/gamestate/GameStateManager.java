package clashclass.gamestate;

import clashclass.engine.GameEngine;
import clashclass.village.Village;

public interface GameStateManager {
    void setStatePlayerVillage();
    void setStateBattle();
    GameEngine getGameEngine();
    Village getPlayerVillage();
    void startEngine();
    void stopEngine();
}
