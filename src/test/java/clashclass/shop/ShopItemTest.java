package clashclass.shop;

import clashclass.resources.ResourceType;
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
        EnumMap<ResourceType, ResourceManagerImpl> resources = new EnumMap<>(ResourceType.class);
        resources.put(ResourceType.GOLD, mockResourceManager);

        mockPlayer = new Player() {
            @Override
            public EnumMap<ResourceType, ResourceManagerImpl> getPlayerResources() {
                return resources;
            }
        };
        item = new ShopItemImpl(ResourceType.GOLD, 50.0, mockPlayer);
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
        assertEquals(ResourceType.GOLD, item.getResourceType());
    }
}
