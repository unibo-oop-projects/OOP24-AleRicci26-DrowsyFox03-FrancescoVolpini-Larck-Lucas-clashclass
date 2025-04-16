package clashclass.saveload;

/**
 * Interface for writing data to files.
 */
public interface FileWriter {
    /**
     * Writes the provided data to a file.
     *
     * @param data the data to write
     * @param filePath the path where to write the file
     * @throws IOException if there's an error writing the file
     */
    void writeToFile(String data, String filePath) throws IOException;
}

