package clashclass.battle.destruction;

import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;
import java.util.List;
import java.util.ArrayList;


public class BattleTroopsBehaviorManagerImpl extends AbstractComponent implements BattleTroopsBehaviorManager {
    
    private final List<GameObject> troops;
    

    public BattleTroopsBehaviorManagerImpl() {
        this.troops = new ArrayList<>();
    }
    

    @Override
    public void notifyDestruction(GameObject obj) {
        // When a building is destroyed, update the behavior of troops
        updateTroopsBehavior();
    }
    

    @Override
    public void updateTroopsBehavior() {
        //chiama AiNodesBuilder
        // AiNodesBuilder.buildNodes(troops);
        
        // For now, just log that the behavior is being updated
        System.out.println("Updating troops behavior after destruction event");
    }
    

    public void addTroop(GameObject troop) {
        if (troop != null && !troops.contains(troop)) {
            troops.add(troop);
        }
    }
    

    public void removeTroop(GameObject troop) {
        troops.remove(troop);
    }
}