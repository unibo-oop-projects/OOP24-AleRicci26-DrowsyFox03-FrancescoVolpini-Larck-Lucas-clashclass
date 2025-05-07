//package clashclass.battle.destruction;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import clashclass.battle.battlereport.BattleReportController;
//import clashclass.battle.battlereport.BattleReportControllerImpl;
//import clashclass.battle.battlereport.BattleReportModel;
//import clashclass.battle.battlereport.BattleReportModelImpl;
//import clashclass.battle.battlereport.BattleReportView;
//import clashclass.battle.battlereport.BattleReportViewImpl;
//import clashclass.ecs.GameObject;
//
///**
// * Test class for the Destruction components.
// */
//public class DestructionTest {
//
//    private BattleReportController battleReportController;
//    private EndBattleAllVillageDestroyed villageDestroyed;
//    private EndBattleTimerIsOver timerIsOver;
//    private BattleTroopsBehaviorManager troopsBehaviorManager;
//    private GameObject mockGameObject;
//
//    @BeforeEach
//    public void setup() {
//        BattleReportModel model = new BattleReportModelImpl(10);
//        BattleReportView view = new BattleReportViewImpl();
//        battleReportController = new BattleReportControllerImpl(model, view);
//
//        villageDestroyed = new EndBattleAllVillageDestroyedImpl(battleReportController);
//        timerIsOver = new EndBattleTimerIsOverImpl();
//        troopsBehaviorManager = new BattleTroopsBehaviorManagerImpl();
//
//        mockGameObject = new GameObject();
//    }
//
//    @Test
//    public void testVillageDestruction() {
//        // Initially not fully destroyed
//        assertFalse(villageDestroyed.isFullyDestroyed());
//
//        // Destroy all buildings
//        for (int i = 0; i < 10; i++) {
//            battleReportController.increaseDestructionPercentage();
//        }
//
//        // Now fully destroyed
//        assertTrue(villageDestroyed.isFullyDestroyed());
//    }
//
//    @Test
//    public void testTimerIsOver() {
//        // Initially timer is not finished
//        assertFalse(timerIsOver.isFinished());
//
//        // Set timer as finished
//        ((EndBattleTimerIsOverImpl) timerIsOver).setTimeFinished(true);
//
//        // Now timer is finished
//        assertTrue(timerIsOver.isFinished());
//    }
//
//    @Test
//    public void testTroopsBehaviorManager() {
//        // Add a troop to the manager
//        ((BattleTroopsBehaviorManagerImpl) troopsBehaviorManager).addTroop(mockGameObject);
//
//        // Notify destruction to update troop behavior
//        troopsBehaviorManager.notifyDestruction(mockGameObject);
//
//        // Remove the troop from the manager
//        ((BattleTroopsBehaviorManagerImpl) troopsBehaviorManager).removeTroop(mockGameObject);
//    }
//}
