package clashclass.battle.manager;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.gamestate.GameStateManager;
import clashclass.village.Village;

import java.util.Set;

public interface BattleManagerModel {
    void setGameStateManager(GameStateManager gameStateManager);
    void setCurrentSelectedTroop(TROOP_TYPE troopType);
    GameStateManager getGameStateManager();
    Village getPlayerVillage();
    Village getBattleVillage();
    TROOP_TYPE getCurrentSelectedTroop();
    void createTroop(Vector2D position);
    Set<GameObject> getActiveTroops();
    void setController(BattleManagerController controller);
    void updateVillageState(GameObject destroyedBuilding);
}
