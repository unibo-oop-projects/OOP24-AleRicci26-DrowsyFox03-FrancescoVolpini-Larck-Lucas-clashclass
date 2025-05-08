package clashclass.saveload;

import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactory;

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

    /**
     * Sets the component factory used to create components during decoding.
     *
     * @param componentFactory the factory used to create components
     */
    void setComponentFactory(ComponentFactory componentFactory);

}
