package clashclass.battle.manager;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.gamestate.GameStateController;
import clashclass.village.Village;

import java.util.Set;

public interface BattleManagerController extends GameStateController {
    void endBattle();
    void setCurrentSelectedTroop(TROOP_TYPE troop);
    void createTroop(Vector2D position);
    Set<GameObject> getActiveTroops();
    Village getBattleVillage();
    void updateVillageState(GameObject destroyedBuilding);
    void updateTroopsState(GameObject destroyedTroop);
}
