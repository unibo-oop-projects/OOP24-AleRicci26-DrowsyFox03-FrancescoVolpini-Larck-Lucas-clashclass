package clashclass.ai.pathfinding;

import clashclass.ai.behaviourtree.blackboard.wrappers.PathNodeListWrapper;
import clashclass.village.Village;

public interface AiNodesBuilder {
    PathNodeGrid buildPathNodeList(Village village);
}
