package clashclass.shop;

/**
 * Represents a shop menu controller.
 */
public interface ShopMenuController {
    /**
     * Gets the shop manager.
     *
     * @return the shop manager
     */
    ShopManager getShopManager();

    /**
     * Tries to buy an item.
     *
     * @param item the itme
     *
     * @return true if the item has been bought
     */
    boolean tryToBuyItem(ShopItem item);

    /**
     * Shows the menu.
     */
    void show();

    /**
     * Hides the menu.
     */
    void hide();

    /**
     * Clears the scene.
     */
    void clearScene();
}
