//package clashclass.battle.troopdeath;
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
// * Test class for the TroopDeath components.
// */
//public class TroopDeathTest {
//
//    private EndBattleAllTroopsDeadImpl allTroopsDead;
//    private GameObject mockTroop;
//
//    @BeforeEach
//    public void setup() {
//        allTroopsDead = new EndBattleAllTroopsDeadImpl();
//        mockTroop = new GameObject();
//    }
//
//    @Test
//    public void testNoTroops() {
//        // Initially no troops are set, so all troops are not dead
//        assertFalse(allTroopsDead.isAllTroopsDead());
//    }
//
//    @Test
//    public void testAllTroopsDead() {
//        // Set the troop count to 2
//        allTroopsDead.setTroopCount(2);
//
//        // Initially all troops are not dead
//        assertFalse(allTroopsDead.isAllTroopsDead());
//
//        // Notify death of one troop
//        allTroopsDead.notifyDeath(mockTroop);
//
//        // Still not all troops are dead
//        assertFalse(allTroopsDead.isAllTroopsDead());
//
//        // Notify death of another troop
//        allTroopsDead.notifyDeath(mockTroop);
//
//        // Now all troops are dead
//        assertTrue(allTroopsDead.isAllTroopsDead());
//    }
//
//    @Test
//    public void testZeroTroops() {
//        // Set the troop count to 0
//        allTroopsDead.setTroopCount(0);
//
//        // With 0 troops, all troops are not dead (since there are no troops)
//        assertFalse(allTroopsDead.isAllTroopsDead());
//    }
//}
//
