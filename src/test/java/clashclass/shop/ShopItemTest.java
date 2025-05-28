package java.clashclass.shop;

import clashclass.shop.ShopItemImpl;
import clashclass.resources.RESOURCE_TYPE;
import clashclass.resources.Player;
import clashclass.resources.ResourceManagerImpl;

import java.util.EnumMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopItemTest {

    private Player mockPlayer;
    private ResourceManagerImpl mockResourceManager;
    private ShopItemImpl item;

    @BeforeEach
    void setUp() {
        mockResourceManager = new ResourceManagerImpl(100);
        EnumMap<RESOURCE_TYPE, ResourceManagerImpl> resources = new EnumMap<>(RESOURCE_TYPE.class);
        resources.put(RESOURCE_TYPE.GOLD, mockResourceManager);

        mockPlayer = new Player() {
            @Override
            public EnumMap<RESOURCE_TYPE, ResourceManagerImpl> getPlayerResources() {
                return resources;
            }
        };
        item = new ShopItemImpl(RESOURCE_TYPE.GOLD, 50.0, mockPlayer);
    }

    @Test
    void TestGetResourceManager () {
        assertSame(mockResourceManager, item.getResourceManager());
    }

    @Test
    void TestGetPrice () {
        assertEquals(50.0, item.getPrice());
    }

    @Test
    void TestGetResourceType () {
        assertEquals(RESOURCE_TYPE.GOLD, item.getResourceType());
    }
}
