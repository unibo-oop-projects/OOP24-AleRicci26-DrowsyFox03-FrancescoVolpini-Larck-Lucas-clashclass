package clashclass.saveload;

/**
 * Interface for encoding Village objects to CSV String format.
 */
public interface VillageEncoder {
    /**
     * Encodes a Village object into a CSV String representation.
     *
     * @param village the Village to encode
     * @return the encoded CSV String representation
     */
    String encode(Village village);
}

