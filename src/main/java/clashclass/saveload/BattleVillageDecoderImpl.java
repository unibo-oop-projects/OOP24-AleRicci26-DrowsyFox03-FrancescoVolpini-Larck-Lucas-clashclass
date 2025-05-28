package clashclass.saveload;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.BuildingFactoryMapper;
import clashclass.elements.buildings.BuildingFactory;
import clashclass.elements.buildings.VillageElementData;

import java.util.Objects;

public class BattleVillageDecoderImpl extends AbstractVillageDecoder {
    private final BuildingFactoryMapper<?> buildingFactoryMapper;

    public BattleVillageDecoderImpl(BuildingFactory buildingFactory) {
        this.buildingFactoryMapper = new BuildingFactoryMapper<>(Objects.requireNonNull(buildingFactory));
    }

    @Override
    protected GameObject createGameObject(VillageElementData type, Vector2D position) {
        return buildingFactoryMapper.getFactoryFor(type).apply(position);
    }
}