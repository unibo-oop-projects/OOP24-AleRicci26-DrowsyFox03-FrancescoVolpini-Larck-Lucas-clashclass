package clashclass.village.manager;

import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.gamestate.GameStateManager;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.shop.ShopMenuController;
import clashclass.shop.ShopMenuControllerImpl;
import clashclass.shop.ShopMenuModelImpl;
import clashclass.shop.ShopMenuView;
import clashclass.village.Village;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Represents a {@link PlayerVillageModel} implementation.
 */
public class PlayerVillageModelImpl implements PlayerVillageModel {
    private final Village playerVillage;
    private GameStateManager gameStateManager;
    private ShopMenuController shopMenuController;

    /**
     * Constructs the model.
     *
     * @param playerVillageCsvPath the player village file path
     */
    public PlayerVillageModelImpl(final Path playerVillageCsvPath) {
        this.playerVillage = this.loadVillage(playerVillageCsvPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    private Village loadVillage(final Path csvPath) {
        try {
            final var decoder = new PlayerVillageDecoderImpl();
            decoder.setComponentFactory(new ComponentFactoryImpl());
            final var csvData = Files.readString(csvPath);
            return decoder.decode(csvData);
        } catch (final IOException e) {
            // throw new IOException("Could not read village data", e);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameStateManager getGameStateManager() {
        return this.gameStateManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Village getPlayerVillage() {
        return this.playerVillage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.playerVillage.getGroundObjects().forEach(GameObject::destroy);
        this.playerVillage.getGameObjects().forEach(GameObject::destroy);
        this.shopMenuController.clearScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildShop(final ShopMenuView view) {
        this.shopMenuController = new ShopMenuControllerImpl(
                new ShopMenuModelImpl(this.playerVillage.getPlayer()),
                view
        );
        this.shopMenuController.hide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openShop() {
        this.shopMenuController.show();
    }
}
