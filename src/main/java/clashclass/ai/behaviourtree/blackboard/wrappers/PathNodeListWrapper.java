package clashclass.ai.behaviourtree.blackboard.wrappers;

import clashclass.ai.pathfinding.PathNode;
import clashclass.ecs.GameObject;

import java.util.List;

/**
 * Represents a class that incapsulates a {@link List} of {@link PathNode}.
 * It's used in the {@link clashclass.ai.behaviourtree.blackboard.BlackboardProperty} to grant
 * the possibility to access the class type and pass it as a parameter.
 */
public record PathNodeListWrapper(List<PathNode> list) {}
