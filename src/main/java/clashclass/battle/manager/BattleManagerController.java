package clashclass.battle.manager;

import clashclass.elements.troops.TROOP_TYPE;
import clashclass.gamestate.GameStateController;

public interface BattleManagerController extends GameStateController {
    void endBattle();
    void setCurrentSelectedTroop(TROOP_TYPE troop);
}
