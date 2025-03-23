package clashclass.ecs;

import org.junit.jupiter.api.Test;

import clashclass.commons.Transform2D;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameObjectTest {

    @Test
    void testComponentIsNotPresentInGameObject() {
        final var gameObject = new GameObjectImpl();
        final var transform = gameObject.getComponentOfType(Transform2D.class);

        assertTrue(transform.isEmpty());
    }

    @Test
    void testComponentIsPresentInGameObject() {
        final var gameObject = new GameObjectImpl();
        gameObject.addComponent(new Transform2D());

        final var transform = gameObject.getComponentOfType(Transform2D.class);

        assertTrue(transform.isPresent());
    }
}
