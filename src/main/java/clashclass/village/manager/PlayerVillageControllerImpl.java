package clashclass.village.manager;

import clashclass.gamestate.GameStateManager;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a {@link PlayerVillageController} implementation.
 */
public class PlayerVillageControllerImpl implements PlayerVillageController {
    private final PlayerVillageModel model;
    private final PlayerVillageView view;

    /**
     * Constructs the player village controller.
     *
     * @param model the player village model
     * @param view the player village view
     */
    @SuppressFBWarnings(value = "EI2", justification = "Intentional set")
    public PlayerVillageControllerImpl(
            final PlayerVillageModel model,
            final PlayerVillageView view) {
        this.model = model;
        this.view = view;

        this.model.buildShop(this.view.getShopMenuView());
        this.view.setController(this);

        this.updateView();
    }

    private void updateView() {
        this.view.update(this.model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openShop() {
        this.model.openShop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openBattleMode() {
        this.model.getGameStateManager().setStateBattle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.model.setGameStateManager(gameStateManager);

        final var gameManager = model.getGameStateManager();
        this.model.getPlayerVillage().getGroundObjects().forEach(gameManager.getGameEngine()::addGameObject);
        this.model.getPlayerVillage().getGameObjects().forEach(gameManager.getGameEngine()::addGameObject);

        this.updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.model.clearScene();
        this.view.clearScene();
    }
}
