package clashclass.ai.behaviourtree.blackboard;

import clashclass.commons.Vector2D;
import clashclass.elements.troops.BattleTroopFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BlackboardTest {

    @Test
    void TestBlackboardProperties() {
        final var blackboard = new BlackboardImpl();
        final var troopFactory = new BattleTroopFactoryImpl();

        final var property1 = new BlackboardPropertyImpl<>(1);
        final var property2 = new BlackboardPropertyImpl<>("Test");
        final var property3 = new BlackboardPropertyImpl<>(troopFactory.createBarbarian(Vector2D.zero()));

        assertFalse(blackboard.hasProperty("Prop1"));

        blackboard.setProperty("Prop1", property1);

        assertTrue(blackboard.hasProperty("Prop1"));

        blackboard.setProperty("Prop2", property2);
        blackboard.setProperty("Prop3", property3);

        assertEquals(1, blackboard.getProperty("Prop1").getValue());
        assertEquals("Test", blackboard.getProperty("Prop2").getValue());
    }
}
