package clashclass.shop;

import clashclass.elements.buildings.VillageElementData;
import clashclass.resources.Player;
import clashclass.resources.RESOURCE_TYPE;

import java.util.List;

public class ShopMenuModelImpl implements ShopMenuModel {
    private final ShopManager shopManager;

    public ShopMenuModelImpl(final Player player) {
        this.shopManager = new ShopManagerImpl(List.of(
                new ShopItemImpl(VillageElementData.GOLD_EXTRACTOR, RESOURCE_TYPE.GOLD, 1000, player),
                new ShopItemImpl(VillageElementData.GOLD_STORAGE, RESOURCE_TYPE.GOLD, 3000, player),
                new ShopItemImpl(VillageElementData.ELIXIR_EXTRACTOR, RESOURCE_TYPE.ELIXIR, 1000, player),
                new ShopItemImpl(VillageElementData.ELIXIR_STORAGE, RESOURCE_TYPE.ELIXIR, 3000, player),
                new ShopItemImpl(VillageElementData.WALL, RESOURCE_TYPE.GOLD, 500, player),
                new ShopItemImpl(VillageElementData.ARMY_CAMP, RESOURCE_TYPE.GOLD, 3000, player),
                new ShopItemImpl(VillageElementData.CANNON, RESOURCE_TYPE.GOLD, 3000, player),
                new ShopItemImpl(VillageElementData.ARCHER_TOWER, RESOURCE_TYPE.ELIXIR, 3500, player)
        ));
    }

    @Override
    public ShopManager getShopManager() {
        return this.shopManager;
    }
}
