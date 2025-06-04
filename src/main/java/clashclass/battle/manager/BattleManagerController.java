package clashclass.battle.manager;

import clashclass.commons.Vector2D;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.gamestate.GameStateController;

public interface BattleManagerController extends GameStateController {
    void endBattle();
    void setCurrentSelectedTroop(TROOP_TYPE troop);
    void createTroop(Vector2D position);
}
