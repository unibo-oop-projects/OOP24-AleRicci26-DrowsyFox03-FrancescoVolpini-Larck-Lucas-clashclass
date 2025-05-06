package clashclass.shop;

import java.util.List;

public interface ShopManager {
    List<ShopItem> shopItems();
    double getBalance(ShopItem shopItem);
    boolean canAfford(ShopItem shopItem);
    void buyItem(ShopItem shopItem);
}
