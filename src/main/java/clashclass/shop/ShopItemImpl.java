package clashclass.shop;

import clashclass.resources.RESOURCE_TYPE;
import clashclass.resources.ResourceManagerImpl;
import clashclass.resources.Player;

/**
 * Represents a ShopItem implementation.
 */
public class ShopItemImpl implements ShopItem {
    private final double price;
    private final RESOURCE_TYPE type;
    private final Player player;

    /**
     * Construct ShopItemImpl.
     *
     * @param type the type of resource needed
     * @param price the price of the ShopItem
     */
    public ShopItemImpl(RESOURCE_TYPE type, double price, Player player) {
        this.type = type;
        this.price = price;
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceManagerImpl getResourceManager() {
        return player.getPlayerResources().get(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice() {
        return this.price;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RESOURCE_TYPE getResourceType() {
        return this.type;
    }
}
