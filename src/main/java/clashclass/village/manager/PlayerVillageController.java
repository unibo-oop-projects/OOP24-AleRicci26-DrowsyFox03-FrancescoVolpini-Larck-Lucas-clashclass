package clashclass.village.manager;

import clashclass.gamestate.GameStateController;

/**
 * Represents a player village controller.
 */
public interface PlayerVillageController extends GameStateController {
    /**
     * Opens the shop menu.
     */
    void openShop();

    /**
     * Goes to battle mode.
     */
    void openBattleMode();
}
