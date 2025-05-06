package clashclass.shop;

import clashclass.resources.RESOURCE_TYPE;
import clashclass.resources.ResourceManagerImpl;

public interface ShopItem {
    ResourceManagerImpl getResourceType ();
    double getPrice ();
}
