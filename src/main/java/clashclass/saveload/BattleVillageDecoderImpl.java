package clashclass.saveload;


import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.buildings.BuildingFactoryMapper;
import clashclass.elements.buildings.VillageElementData;
import java.util.Objects;


public class BattleVillageDecoderImpl extends AbstractVillageDecoder {

    private final BuildingFactoryMapper<BattleBuildingFactoryImpl> mapper;

    public BattleVillageDecoderImpl(BattleBuildingFactoryImpl factory) {
        this.mapper = new BuildingFactoryMapper<>(Objects.requireNonNull(factory));

    }
    @Override
    protected GameObject createGameObject(VillageElementData type, Vector2D position) {
        return mapper.getFactoryFor(type).apply(position);
    }
}