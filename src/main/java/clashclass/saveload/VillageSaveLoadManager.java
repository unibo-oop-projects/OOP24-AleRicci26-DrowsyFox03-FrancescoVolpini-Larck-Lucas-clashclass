package clashclass.saveload;

import clashclass.village.Village;
import clashclass.ecs.GameObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
/**
 * Main manager class for handling village save/load operations.
 */
public class VillageSaveLoadManager {
    private final VillageEncoder encoder;
    private final VillageDecoder playerDecoder;
    private final VillageDecoder battleDecoder;
    private final FileWriter fileWriter;
    private final Path savesDirectory;

    public VillageSaveLoadManager(
            VillageEncoder encoder,
            VillageDecoder playerDecoder,
            VillageDecoder battleDecoder,
            FileWriter fileWriter,
            Path savesDirectory
    ) {
        this.encoder = encoder;
        this.playerDecoder = playerDecoder;
        this.battleDecoder = battleDecoder;
        this.fileWriter = fileWriter;
        this.savesDirectory = savesDirectory;
    }

    /**
     * Saves a collection of GameObjects to file.
     * @param gameObjects The GameObjects to save
     * @param fileName The name of the save file
     * @throws IOException If saving fails
     */
    public void saveVillage(Set<GameObject> gameObjects, String fileName) throws IOException {
        String encoded = encoder.encode(gameObjects);
        Path filePath = savesDirectory.resolve(fileName + ".csv");
        fileWriter.writeToFile(encoded, filePath);
    }

    /**
     * Loads GameObjects for a player village from file.
     * @param fileName The name of the save file
     * @return Set of loaded GameObjects
     * @throws IOException If loading fails
     */
    public Village loadPlayerVillage(String fileName) throws IOException {
        String data = Files.readString(savesDirectory.resolve(fileName + ".csv"));
        return playerDecoder.decode(data);
    }

    /**
     * Loads GameObjects for a battle village from file.
     * @param fileName The name of the save file
     * @return Set of loaded GameObjects
     * @throws IOException If loading fails
     */
    public Village loadBattleVillage(String fileName) throws IOException {
        String data = Files.readString(savesDirectory.resolve(fileName + ".csv"));
        return battleDecoder.decode(data);
    }
}

