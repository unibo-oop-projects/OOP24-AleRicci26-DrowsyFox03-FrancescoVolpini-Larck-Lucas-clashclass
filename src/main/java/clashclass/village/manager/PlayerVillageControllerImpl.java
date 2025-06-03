package clashclass.village.manager;

import clashclass.ecs.GameObject;
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

        final var gameManager = model.getGameStateManager();
        gameManager.getPlayerVillage().getGroundObjects().forEach(gameManager.getGameEngine()::addGameObject);
        gameManager.getPlayerVillage().getGameObjects().forEach(gameManager.getGameEngine()::addGameObject);

        this.updateView();
    }

    @Override
    public void clearScene() {
        final var gameManager = model.getGameStateManager();
        gameManager.getPlayerVillage().getGroundObjects().forEach(GameObject::destroy);
        gameManager.getPlayerVillage().getGameObjects().forEach(GameObject::destroy);
        this.view.clearScene();
    }
}
