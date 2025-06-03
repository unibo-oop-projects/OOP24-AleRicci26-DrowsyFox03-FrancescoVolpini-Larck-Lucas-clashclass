package clashclass.village.manager;

import clashclass.ecs.GameObject;
import clashclass.gamestate.GameStateManager;

import java.util.Set;
import java.util.stream.Collectors;

public class PlayerVillageControllerImpl implements PlayerVillageController {
    private final PlayerVillageModel model;
    private final PlayerVillageView view;

    private Set<GameObject> groundObjectsCopy;
    private Set<GameObject> gameObjectsCopy;

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
        this.groundObjectsCopy = gameManager.getPlayerVillage().getGroundObjects().stream().collect(Collectors.toSet());
        this.gameObjectsCopy = gameManager.getPlayerVillage().getGameObjects().stream().collect(Collectors.toSet());

        this.groundObjectsCopy.forEach(gameManager.getGameEngine()::addGameObject);
        this.gameObjectsCopy.forEach(gameManager.getGameEngine()::addGameObject);

        this.updateView();
    }

    @Override
    public void clearScene() {
        this.groundObjectsCopy.forEach(GameObject::destroy);
        this.gameObjectsCopy.forEach(GameObject::destroy);
        this.view.clearScene();
    }
}
