package clashclass.saveload;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.BuildingFactoryMapper;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.elements.buildings.VillageElementData;

public class PlayerVillageDecoderImpl extends AbstractVillageDecoder {
    private final BuildingFactoryMapper<?> buildingFactoryMapper;

    public PlayerVillageDecoderImpl() {
        this.buildingFactoryMapper = new BuildingFactoryMapper<>(new PlayerBuildingFactoryImpl());
    }

    @Override
    protected GameObject createGameObject(final VillageElementData type, VectorInt2D position) {
        return buildingFactoryMapper.getFactoryFor(type).apply(position);
    }
}
