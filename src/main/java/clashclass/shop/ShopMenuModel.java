package clashclass.shop;

/**
 * Represents a shop menu model.
 */
@FunctionalInterface
public interface ShopMenuModel {
    /**
     * Gets the shop manager.
     *
     * @return the shop manager
     */
    ShopManager getShopManager();
}
