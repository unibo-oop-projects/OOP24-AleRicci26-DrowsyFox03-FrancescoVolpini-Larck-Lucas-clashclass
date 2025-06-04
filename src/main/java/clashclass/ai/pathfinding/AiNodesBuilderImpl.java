package clashclass.ai.pathfinding;

import clashclass.ai.behaviourtree.blackboard.wrappers.PathNodeListWrapper;
import clashclass.battle.manager.BattleManagerController;
import clashclass.commons.BuildingTypeComponent;
import clashclass.commons.GameConstants;
import clashclass.commons.GridTileData2D;
import clashclass.commons.Transform2D;
import clashclass.elements.buildings.VillageElementData;
import clashclass.village.Village;

import java.util.Optional;
import java.util.stream.Collectors;

public class AiNodesBuilderImpl implements AiNodesBuilder {
    @Override
    public PathNodeGrid buildPathNodeList(final Village village) {
        return new PathNodeGridImpl(GameConstants.VILLAGE_SIZE, village.getBuildings().stream()
                .filter(x -> !x.isMarkedAsDestroyed())
                .map(gameObject -> {
                    final var position = gameObject.getComponentOfType(GridTileData2D.class).get().getPosition();
                    final var buildingType = gameObject.getComponentOfType(BuildingTypeComponent.class).get().getBuildingType();
                    return new PathNodeImpl(
                            position,
                            buildingType.equals(VillageElementData.WALL) ? 1000.0f : 1.0f,
                            Optional.of(gameObject));
                })
                .collect(Collectors.toSet()));
    }
}
