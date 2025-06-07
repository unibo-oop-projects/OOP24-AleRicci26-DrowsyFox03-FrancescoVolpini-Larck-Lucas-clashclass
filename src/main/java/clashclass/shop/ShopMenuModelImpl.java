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
                new ShopItemImpl(VillageElementData.GOLD_EXTRACTOR, ResourceType.GOLD,
                        BuildingPrice.GOLD_EXTRACTOR.getPrice(), player),
                new ShopItemImpl(VillageElementData.GOLD_STORAGE, ResourceType.GOLD,
                        BuildingPrice.GOLD_STORAGE.getPrice(), player),
                new ShopItemImpl(VillageElementData.ELIXIR_EXTRACTOR, ResourceType.ELIXIR,
                        BuildingPrice.ELIXIR_EXTRACTOR.getPrice(), player),
                new ShopItemImpl(VillageElementData.ELIXIR_STORAGE, ResourceType.ELIXIR,
                        BuildingPrice.ELIXIR_STORAGE.getPrice(), player),
                new ShopItemImpl(VillageElementData.WALL, ResourceType.GOLD,
                        BuildingPrice.WALL.getPrice(), player),
                new ShopItemImpl(VillageElementData.ARMY_CAMP, ResourceType.GOLD,
                        BuildingPrice.ARMY_CAMP.getPrice(), player),
                new ShopItemImpl(VillageElementData.CANNON, ResourceType.GOLD,
                        BuildingPrice.CANNON.getPrice(), player),
                new ShopItemImpl(VillageElementData.ARCHER_TOWER, ResourceType.ELIXIR,
                        BuildingPrice.ARCHER_TOWER.getPrice(), player)
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
