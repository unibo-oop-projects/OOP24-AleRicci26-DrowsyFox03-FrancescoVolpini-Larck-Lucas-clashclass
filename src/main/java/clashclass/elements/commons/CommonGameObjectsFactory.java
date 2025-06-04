package clashclass.elements.commons;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;

public interface CommonGameObjectsFactory {
    GameObject createVillageGroundTile(VectorInt2D position);
    GameObject createUIElement();
}
