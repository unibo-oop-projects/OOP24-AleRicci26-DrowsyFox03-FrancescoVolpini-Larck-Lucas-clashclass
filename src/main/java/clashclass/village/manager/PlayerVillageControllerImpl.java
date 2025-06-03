package clashclass.village.manager;

import clashclass.gamestate.GameStateManager;

public class PlayerVillageControllerImpl implements PlayerVillageController {
    private final PlayerVillageModel model;
    private final PlayerVillageView view;

    public PlayerVillageControllerImpl(final PlayerVillageModel model, final PlayerVillageView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        this.updateView();
    }

    private void updateView() {
        this.view.update(this.model);
    }

    @Override
    public void openShop() {

    }

    @Override
    public void openBattleMode() {
        this.model.getGameStateManager().setStateBattle();
    }

    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.model.setGameStateManager(gameStateManager);
    }

    @Override
    public void clearScene() {
        this.view.clearScene();
    }
}
