package clashclass.battle.battlereport;

import clashclass.battle.destruction.BattleTroopsBehaviorManager;
import clashclass.battle.destruction.BattleTroopsBehaviorManagerImpl;
import clashclass.battle.destruction.EndBattleAllVillageDestroyed;
import clashclass.battle.destruction.EndBattleAllVillageDestroyedImpl;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;
import clashclass.resources.ResourceManager;

/**
 * Implementation of VillageDestructionManager.
 * Handles destruction of village objects and resource management.
 */
public class VillageDestructionManagerImpl extends AbstractComponent implements VillageDestructionManager {

    private static final String TOWN_HALL_COMPONENT = "TownHallComponent";
    private static final String GOLD_STORAGE_COMPONENT = "GoldStorageComponent";
    private static final String ELIXIR_STORAGE_COMPONENT = "ElixirStorageComponent";

    private final BattleTroopsBehaviorManager troopsBehaviorManager;
    private final EndBattleAllVillageDestroyed endBattleManager;

    /**
     * Constructor that initializes required managers.
     */
    public VillageDestructionManagerImpl() {
        this.troopsBehaviorManager = new BattleTroopsBehaviorManagerImpl();
        this.endBattleManager = new EndBattleAllVillageDestroyedImpl();
    }

    @Override
    public void destroyObject(GameObject obj, BattleReportModel battleReport) {
        if (!canDestroy(obj)) {
            return;
        }

        // Mark the object as destroyed
        obj.destroy();

        // Update battle report
        battleReport.increaseDestructionPercentage();

        // Check for special object types (like Town Hall)
        handleSpecialObject(obj, battleReport);

        // Handle resource extraction if applicable
        handleResourceExtraction(obj, battleReport);

        // Notify other systems about the destruction
        notifyDestructionListeners(obj);
    }

    @Override
    public boolean canDestroy(GameObject obj) {
        return obj != null && !isDestroyed(obj);
    }

    /**
     * Checks if a game object has been destroyed.
     */
    private boolean isDestroyed(GameObject obj) {
        return obj.isMarkedAsDestroyed();
    }

    /**
     * Handles special object types like Town Hall.
     */
    private void handleSpecialObject(GameObject obj, BattleReportModel battleReport) {
        if (isTownHall(obj)) {
            battleReport.setTownHallDestroyed(true);
        }
    }

    /**
     * Checks if the object is a town hall.
     */
    private boolean isTownHall(GameObject obj) {
        return obj.getComponents().stream()
                .anyMatch(component ->
                        component.getClass().getSimpleName().equals(TOWN_HALL_COMPONENT));
    }

    /**
     * Handles resource extraction from destroyed objects.
     */
    private void handleResourceExtraction(GameObject obj, BattleReportModel battleReport) {
        ResourceManager goldStorage = getGoldStorage(obj);
        if (goldStorage != null) {
            battleReport.addStolenGold(goldStorage);
        }

        ResourceManager elixirStorage = getElixirStorage(obj);
        if (elixirStorage != null) {
            battleReport.addStolenElixir(elixirStorage);
        }
    }

    /**
     * Gets gold storage from game object if available.
     */
    private ResourceManager getGoldStorage(GameObject obj) {
        return obj.getComponents().stream()
                .filter(component -> component.getClass().getSimpleName().equals(GOLD_STORAGE_COMPONENT))
                .findFirst()
                .map(component -> {
                    try {
                        return (ResourceManager) component;
                    } catch (ClassCastException e) {
                        return null;
                    }
                })
                .orElse(null);
    }

    /**
     * Gets elixir storage from game object if available.
     */
    private ResourceManager getElixirStorage(GameObject obj) {
        return obj.getComponents().stream()
                .filter(component -> component.getClass().getSimpleName().equals(ELIXIR_STORAGE_COMPONENT))
                .findFirst()
                .map(component -> {
                    try {
                        return (ResourceManager) component;
                    } catch (ClassCastException e) {
                        return null;
                    }
                })
                .orElse(null);
    }

    /**
     * Notifies all relevant systems about the destruction event.
     */
    private void notifyDestructionListeners(GameObject obj) {
        troopsBehaviorManager.notifyDestruction(obj);
        endBattleManager.notifyDestruction(obj);
    }
}