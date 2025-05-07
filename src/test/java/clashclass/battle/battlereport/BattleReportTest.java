//package clashclass.battle.battlereport;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import clashclass.resources.ResourceManager;
//import clashclass.resources.ResourceManagerImpl;
//
///**
// * Test class for the Battle Report components.
// */
//public class BattleReportTest {
//
//    private BattleReportModel model;
//    private BattleReportController controller;
//    private BattleReportView view;
//    private static final int TOTAL_BUILDINGS = 10;
//
//    @BeforeEach
//    public void setup() {
//        model = new BattleReportModelImpl(TOTAL_BUILDINGS);
//        view = new BattleReportViewImpl();
//        controller = new BattleReportControllerImpl(model, view);
//    }
//
//    @Test
//    public void testDestructionPercentage() {
//        // Initially 0%
//        assertEquals(0.0, model.getDestructionPercentage(), 0.01);
//
//        // Increase destruction percentage
//        controller.increaseDestructionPercentage();
//        assertEquals(10.0, model.getDestructionPercentage(), 0.01);
//
//        // Increase again
//        controller.increaseDestructionPercentage();
//        assertEquals(20.0, model.getDestructionPercentage(), 0.01);
//    }
//
//    @Test
//    public void testStars() {
//        // Initially 0 stars
//        assertEquals(0, model.getStars());
//
//        // 1 star at 50% destruction
//        for (int i = 0; i < 5; i++) {
//            controller.increaseDestructionPercentage();
//        }
//        assertEquals(50.0, model.getDestructionPercentage(), 0.01);
//        assertEquals(1, model.getStars());
//
//        // 2 stars when Town Hall is destroyed
//        controller.notifyTownHallDestroyed();
//        assertEquals(2, model.getStars());
//
//        // 3 stars at 100% destruction
//        for (int i = 0; i < 5; i++) {
//            controller.increaseDestructionPercentage();
//        }
//        assertEquals(100.0, model.getDestructionPercentage(), 0.01);
//        assertEquals(3, model.getStars());
//    }
//
//    @Test
//    public void testStolenResources() {
//        // Initially no resources
//        assertEquals(0, model.getStolenResources().getGold());
//        assertEquals(0, model.getStolenResources().getElixir());
//
//        // Add resources
//        ResourceManager resources = new ResourceManagerImpl();
//        resources.addGold(100);
//        resources.addElixir(200);
//
//        controller.increaseStolenResources(resources);
//        assertEquals(100, model.getStolenResources().getGold());
//        assertEquals(200, model.getStolenResources().getElixir());
//
//        // Add more resources
//        resources = new ResourceManagerImpl();
//        resources.addGold(50);
//        resources.addElixir(75);
//
//        controller.increaseStolenResources(resources);
//        assertEquals(150, model.getStolenResources().getGold());
//        assertEquals(275, model.getStolenResources().getElixir());
//    }
//
//    @Test
//    public void testVictory() {
//        // Initially not a victory
//        assertFalse(model.isVictory());
//
//        // Victory with 1 star
//        for (int i = 0; i < 5; i++) {
//            controller.increaseDestructionPercentage();
//        }
//        assertTrue(model.isVictory());
//    }
//
//    @Test
//    public void testTroopCount() {
//        // Initially 0 troops
//        assertEquals(0, model.getTroopCount());
//
//        // Set troop count
//        controller.setTroopCount(15);
//        assertEquals(15, model.getTroopCount());
//    }
//}
