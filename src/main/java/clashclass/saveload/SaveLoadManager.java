package clashclass.saveload;

import clashclass.ai.pathfinding.PathNodeGrid;
import java.io.IOException;

public interface SaveLoadManager {
    /**
     *Saves the current grid state to a CSV file
     *
     * @param grid the grid to save
     * @param filePath the path where to save the CSV file
     * @throws IOException if there's an error writing the file
     */
    void saveGridToCSV(PathNodeGrid grid, String filePath) throws IOException;

    /**
     * Loads a grid from a CSV file.
     *
     * @param filePath the path of the CSV file to load
     * @return the loaded PathNodeGrid
     * @throws IOException if there's an error reading the file
     */
    PathNodeGrid loadGridFromCSV(String filePath) throws IOException;
}

