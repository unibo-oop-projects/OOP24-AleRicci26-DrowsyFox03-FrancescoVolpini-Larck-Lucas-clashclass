package clashclass.shop;

public interface ShopMenuController {
    ShopManager getShopManager();
    boolean tryToBuyItem(ShopItem item);
    void show();
    void hide();
    void clearScene();
}
