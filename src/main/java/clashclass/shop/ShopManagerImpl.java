package clashclass.shop;

import java.util.List;

public record ShopManagerImpl(List<ShopItem> shopItems) implements ShopManager {
    @Override
    public double getBalance(ShopItem shopItem) {
        return shopItem.getResourceType().getCurrentValue();
    }

    @Override
    public boolean canAfford(ShopItem shopItem) {
        return shopItem.getPrice() <= this.getBalance(shopItem);
    }

    @Override
    public void buyItem(ShopItem shopItem) {
        if (this.canAfford(shopItem)) {
            shopItem.getResourceType().decrease(shopItem.getPrice());
        }
    }
}
