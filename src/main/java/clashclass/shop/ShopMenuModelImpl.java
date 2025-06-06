package clashclass.shop;

import clashclass.elements.buildings.VillageElementData;
import clashclass.resources.Player;
import clashclass.resources.ResourceType;

import java.util.List;

/**
 * Represents a {@link ShopMenuModel} implementation.
 */
public class ShopMenuModelImpl implements ShopMenuModel {
    private final ShopManager shopManager;

    /**
     * Constructs the model.
     *
     * @param player the player reference
     */
    public ShopMenuModelImpl(final Player player) {
        this.shopManager = new ShopManagerImpl(List.of(
                new ShopItemImpl(VillageElementData.GOLD_EXTRACTOR, ResourceType.GOLD, 1000, player),
                new ShopItemImpl(VillageElementData.GOLD_STORAGE, ResourceType.GOLD, 3000, player),
                new ShopItemImpl(VillageElementData.ELIXIR_EXTRACTOR, ResourceType.ELIXIR, 1000, player),
                new ShopItemImpl(VillageElementData.ELIXIR_STORAGE, ResourceType.ELIXIR, 3000, player),
                new ShopItemImpl(VillageElementData.WALL, ResourceType.GOLD, 500, player),
                new ShopItemImpl(VillageElementData.ARMY_CAMP, ResourceType.GOLD, 3000, player),
                new ShopItemImpl(VillageElementData.CANNON, ResourceType.GOLD, 3000, player),
                new ShopItemImpl(VillageElementData.ARCHER_TOWER, ResourceType.ELIXIR, 3500, player)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShopManager getShopManager() {
        return this.shopManager;
    }
}
