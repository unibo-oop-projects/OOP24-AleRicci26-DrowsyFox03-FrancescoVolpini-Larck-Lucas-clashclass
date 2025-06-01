package clashclass.elements.commons;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;

public interface CommonGameObjectsFactory {
    GameObject createVillageGroundTile(Vector2D position);
}
