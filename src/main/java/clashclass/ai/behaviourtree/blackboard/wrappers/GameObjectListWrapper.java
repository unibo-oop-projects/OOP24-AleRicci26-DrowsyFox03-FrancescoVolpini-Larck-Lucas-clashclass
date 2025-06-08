package clashclass.ai.behaviourtree.blackboard.wrappers;

import clashclass.ecs.GameObject;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;

/**
 * Represents a class that incapsulates a {@link List} of {@link GameObject}.
 * It's used in the {@link clashclass.ai.behaviourtree.blackboard.BlackboardProperty} to grant
 * the possibility to access the class type and pass it as a parameter.
 *
 * @param list the list of game objects
 */
@SuppressFBWarnings(value = {"EI", "EI2"}, justification = "Intentional access")
public record GameObjectListWrapper(List<GameObject> list) { }
