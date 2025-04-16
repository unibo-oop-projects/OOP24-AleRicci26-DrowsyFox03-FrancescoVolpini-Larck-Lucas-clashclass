package clashclass.saveload;

/**
 * Interface for decoding CSV String representations back to Village objects.
 */
public interface VillageDecoder {
    /**
     * Decodes a CSV String representation back into a Village object.
     *
     * @param encodedVillage the CSV String to decode
     * @return the decoded Village object
     */
    Village decode(String encodedVillage);
}
