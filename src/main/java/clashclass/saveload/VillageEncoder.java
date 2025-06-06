package clashclass.saveload;

import clashclass.ecs.GameObject;
import java.util.Set;

/**
 * Interface for encoding Village objects to CSV String format.
 */
public interface VillageEncoder {
    /**
     * Encodes a Village object into a CSV String representation.
     *
     * @param gameObjects the gameObjects to encode
     * @return the encoded CSV String representation
     */
    String encode(Set<GameObject> gameObjects);

    /**
     * Gets the header of the file.
     *
     * @return the header of the file
     */
    String getHeader();
}

