package clashclass.shop;

import clashclass.resources.RESOURCE_TYPE;
import clashclass.resources.ResourceManagerImpl;
import clashclass.resources.Player;

public class ShopItemImpl implements ShopItem {
    private final double price;
    private final RESOURCE_TYPE type;
    private final Player playerResources = new Player();

    public ShopItemImpl(RESOURCE_TYPE type, double price) {
        this.type = type;
        this.price = price;
    }

    @Override
    public ResourceManagerImpl getResourceType() {
        return playerResources.getPlayerResources().get(type);
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
