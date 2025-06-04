package clashclass.ai.pathfinding;

import clashclass.ecs.GameObject;
import clashclass.village.Village;

public interface AiNodesBuilder {
    PathNodeGrid buildPathNodeList(Village village);
    PathNodeGrid buildPathNodeList(GameObject destroyedBuilding);
}
