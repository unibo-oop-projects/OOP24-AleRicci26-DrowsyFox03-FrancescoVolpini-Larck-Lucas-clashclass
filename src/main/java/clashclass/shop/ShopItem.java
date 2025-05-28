package clashclass.shop;

import clashclass.resources.ResourceManagerImpl;
import clashclass.resources.RESOURCE_TYPE;

/**
 * Represents the items inside the shop.
 */
public interface ShopItem {
    /**
     * Return the Manager of the resource needed by the ShopItem.
     *
     * @return the ResourceManager needed by the item
     */
    ResourceManagerImpl getResourceManager();

    /**
     * Return the price of the item.
     *
     * @return the item's price as a double
     */
    double getPrice ();

    /**
     * Return the resource type associated with the item
     *
     * @return the resource type
     */
    RESOURCE_TYPE getResourceType();
}
