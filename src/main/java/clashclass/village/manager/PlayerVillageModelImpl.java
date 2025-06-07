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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Collectors;

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
        final var decoder = new PlayerVillageDecoderImpl();
        decoder.setComponentFactory(new ComponentFactoryImpl());
        final var csvData = this.readCsvFile(csvPath);
        return decoder.decode(csvData);
    }

    private String readCsvFile(final Path csvPath) {
        if (Files.exists(csvPath)) {
            try {
                return Files.readString(csvPath);
            } catch (final IOException e) {
                return "";
            }
        }
        final var fileStream = Objects.requireNonNull(ClassLoader
                .getSystemResourceAsStream(csvPath.toString().replace("\\", "/")));
        final BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
        return reader.lines().collect(Collectors.joining("\n"));
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
