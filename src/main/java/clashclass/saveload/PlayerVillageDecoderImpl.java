package clashclass.saveload;

import clashclass.commons.Vector2D;

import clashclass.ecs.GameObject;
import clashclass.elements.buildings.BuildingFactoryMapper;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.elements.buildings.VillageElementData;
import java.util.Objects;


public class PlayerVillageDecoderImpl extends AbstractVillageDecoder {

    private final BuildingFactoryMapper<PlayerBuildingFactoryImpl> mapper;

    public PlayerVillageDecoderImpl(PlayerBuildingFactoryImpl factory) {
        this.mapper = new BuildingFactoryMapper<>(Objects.requireNonNull(factory));
    }

    @Override
    protected GameObject createGameObject(VillageElementData type, Vector2D position) {
        return mapper.getFactoryFor(type).apply(position);
    }
}
