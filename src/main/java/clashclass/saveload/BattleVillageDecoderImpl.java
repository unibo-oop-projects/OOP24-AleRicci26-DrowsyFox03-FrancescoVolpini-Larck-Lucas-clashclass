package clashclass.saveload;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.buildings.BuildingFactoryMapper;
import clashclass.elements.buildings.VillageElementData;

public class BattleVillageDecoderImpl extends AbstractVillageDecoder {
    private final BuildingFactoryMapper<?> buildingFactoryMapper;

    public BattleVillageDecoderImpl() {
        this.buildingFactoryMapper = new BuildingFactoryMapper<>(new BattleBuildingFactoryImpl());
    }

    @Override
    protected GameObject createGameObject(final VillageElementData type, final VectorInt2D position) {
        return buildingFactoryMapper.getFactoryFor(type).apply(position);
    }
}