package clashclass.ai.logic;

import clashclass.commons.Transform2D;
import clashclass.commons.Vector2D;
import clashclass.ecs.GameObjectImpl;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.troops.BattleTroopFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChooseTargetLogicTest {

    @Test
    void TestChooseNearestTarget() {
        final var chooseTargetLogic = new ChooseTargetNearestLogicImpl();
        final var buildingsFactory = new BattleBuildingFactoryImpl();
        final var troopFactory = new BattleTroopFactoryImpl();

        final var building1 = buildingsFactory.createCannon(new Vector2D(1, 2));
        final var building2 = buildingsFactory.createCannon(new Vector2D(1, 3));
        final var building3 = buildingsFactory.createCannon(new Vector2D(2, 1));

        final var targetsList = List.of(building1, building2, building3);

        final var troop = troopFactory.createBarbarian(new Vector2D(0, 0));

        final var nearestTarget = chooseTargetLogic.chooseTarget(troop, targetsList);

        assertEquals(new Vector2D(1, 2), nearestTarget.getComponentOfType(Transform2D.class).get().getPosition());
    }
}
