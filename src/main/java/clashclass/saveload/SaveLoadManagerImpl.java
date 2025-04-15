package clashclass.saveload;

import clashclass.ai.pathfinding.*;
import clashclass.ecs.GameObject;

import java.io.*;
import java.util.*;


public class SaveLoadManagerImpl implements SaveLoadManager {
    private static final String CSV_DELIMITER = ",";
    private static final String CSV_HEADER = "x,y,cost,hasGameObject";
    private static final int EXPECTED_CSV_COLUMNS = 4;
    private static final int INDEX_X = 0;
    private static final int INDEX_Y = 1;
    private static final int INDEX_COST = 2;
    private static final int INDEX_HAS_GAMEOBJECT = 3;


    @Override
    public void saveGridToCSV(PathNodeGrid grid, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write(CSV_HEADER);
            writer.newLine();

            // Write nodes data
            for (PathNode node : grid.getNodes()) {
                String line = String.format("%d,%d,%.1f,%b",
                        node.getX(),
                        node.getY(),
                        node.getCost(),
                        node.getRefGameObject().isPresent()
                );
                writer.write(line);
                writer.newLine();
            }
        }
    }

    @Override
    public PathNodeGrid loadGridFromCSV(String filePath) throws IOException {

        Set<PathNode> nodes = new HashSet<>();
        int maxSize = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(CSV_DELIMITER);
                if (values.length != EXPECTED_CSV_COLUMNS) {
                    throw new IOException("Invalid CSV format");
                }

                int x = Integer.parseInt(values[INDEX_X]);
                int y = Integer.parseInt(values[INDEX_Y]);
                float cost = Float.parseFloat(values[INDEX_COST]);
                boolean hasGameObject = Boolean.parseBoolean(values[INDEX_HAS_GAMEOBJECT]);

                // Keep track of grid size
                maxSize = Math.max(maxSize, Math.max(x, y) + 1);

                // Create node
                PathNode node = new PathNodeImpl(x, y, cost, null);
                nodes.add(node);
            }
        }

        return new PathNodeGridImpl(maxSize, nodes);
    }
}

