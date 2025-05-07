//package clashclass.battle.endbattle;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import clashclass.ecs.GameObject;
//
///**
// * Test class for the EndBattle components.
// */
//public class EndBattleTest {
//
//    private TestBattleEvent battleEvent;
//    private GameObject mockGameObject;
//
//    @BeforeEach
//    public void setup() {
//        battleEvent = new TestBattleEvent();
//        mockGameObject = new GameObject();
//    }
//
//    @Test
//    public void testBattleEvent() {
//        // Initially battle is not ended
//        assertFalse(battleEvent.isBattleEnded());
//
//        // End the battle
//        battleEvent.endBattle();
//
//        // Now battle is ended
//        assertTrue(battleEvent.isBattleEnded());
//    }
//
//    /**
//     * Test implementation of AbstractBattleEvent for testing purposes.
//     */
//    private static class TestBattleEvent extends AbstractBattleEvent {
//
//        private boolean battleEnded;
//
//        public TestBattleEvent() {
//            this.battleEnded = false;
//        }
//
//        @Override
//        public void endBattle() {
//            // Call the protected EndBattle method from AbstractBattleEvent
//            EndBattle(null);
//            this.battleEnded = true;
//        }
//
//        public boolean isBattleEnded() {
//            return battleEnded;
//        }
//    }
//}
