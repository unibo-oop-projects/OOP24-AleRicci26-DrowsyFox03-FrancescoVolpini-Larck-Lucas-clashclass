package clashclass.saveload;

import clashclass.ecs.GameObject;

import java.util.Set;

/**
 * Interface for decoding CSV String representations back to Village objects.
 */
public interface VillageDecoder {
    /**
     * Decodes a CSV String representation back into a Village object.
     *
     * @param encodedVillage the CSV String to decode
     * @return Set of reconstructed GameObjects
     */
    Set<GameObject> decode(String encodedVillage);
}
