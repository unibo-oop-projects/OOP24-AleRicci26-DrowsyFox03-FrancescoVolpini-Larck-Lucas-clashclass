package clashclass.saveload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SimpleFileWriterImpl implements FileWriter {
    /**
     * @param data     the data to write
     * @param filePath the path where to write the file
     * @throws IOException
     */
    @Override
    public void writeToFile(String data, Path filePath) throws IOException {
        // Create parent directories if they don't exist
        if (filePath.getParent() != null) {
            Files.createDirectories(filePath.getParent());
        }

        // Write the data to the file
        Files.writeString(filePath, data,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);


    }
}
