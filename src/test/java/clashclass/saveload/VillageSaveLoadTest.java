package clashclass.saveload;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactory;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.VillageElementData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VillageSaveLoadTest {

    @TempDir
    Path tempDir;

    private VillageSaveLoadManager saveLoadManager;
    private FileWriter fileWriter;
    private VillageEncoder encoder;
    private VillageDecoder decoder;

    @BeforeEach
    void setUp() {
        fileWriter = new SimpleFileWriterImpl();
        encoder = new VillageEncoderImpl();
        var componentFactory = new ComponentFactoryImpl();

        // The componentFactory will be set inside the VillageSaveLoadManager constructor
        saveLoadManager = new VillageSaveLoadManager(
                encoder,
                decoder, // Using the same decoder for both player and battle
                decoder, // Using the same decoder for both player and battle
                fileWriter,
                componentFactory,
                tempDir
        );
    }

    @Test
    void testFileWriterCreatesFile() throws IOException {
        Path testFile = tempDir.resolve("test.csv");
        String testData = "test,data\n1,2,3";

        fileWriter.writeToFile(testData, testFile);

        assertTrue(Files.exists(testFile));
        assertEquals(testData, Files.readString(testFile));
    }

    @Test
    void testVillageEncodingDecoding() {
        // Create some game objects
        // Set<GameObject> originalObjects = createTestGameObjects();

        // Encode and then decode
        String encoded = encoder.encode(originalObjects);
        Set<GameObject> decodedObjects = decoder.decode(encoded);

        // Test the number of objects matches
        assertEquals(originalObjects.size(), decodedObjects.size());

    }

    @Test
    void testSaveLoadVillage() throws IOException {
        // Create some game objects
        Set<GameObject> originalObjects = createTestGameObjects();

        // Save the village
        String fileName = "test_village";
        saveLoadManager.saveVillage(originalObjects, fileName);

        // Verify the file was created
        Path savedFile = tempDir.resolve(fileName + ".csv");
        assertTrue(Files.exists(savedFile));

        // Load the village
        Set<GameObject> loadedObjects = saveLoadManager.loadPlayerVillage(fileName);

        // Check that we have the same number of objects
        assertEquals(originalObjects.size(), loadedObjects.size());
    }

    @Test
    void testBattleVillageLoading() throws IOException {
        // Create some game objects
        Set<GameObject> originalObjects = createTestGameObjects();

        // Save the village
        String fileName = "test_battle";
        saveLoadManager.saveVillage(originalObjects, fileName);

        // Load as battle village
        Set<GameObject> loadedObjects = saveLoadManager.loadBattleVillage(fileName);

        // Check that we have the same number of objects
        assertEquals(originalObjects.size(), loadedObjects.size());
    }

    @Test
    void testFileNotFound() {
        String nonExistentFile = "does_not_exist";

        // Test both loading methods
        assertThrows(IOException.class, () ->
                saveLoadManager.loadPlayerVillage(nonExistentFile));

        assertThrows(IOException.class, () ->
                saveLoadManager.loadBattleVillage(nonExistentFile));
    }

    @Test
    void testMultipleSaveLoad() throws IOException {
        // Create two different sets of objects
        Set<GameObject> village1 = createTestGameObjects();
        Set<GameObject> village2 = createSecondTestGameObjects();

        // Save both villages
        saveLoadManager.saveVillage(village1, "village1");
        saveLoadManager.saveVillage(village2, "village2");

        // Load both villages
        Set<GameObject> loadedVillage1 = saveLoadManager.loadPlayerVillage("village1");
        Set<GameObject> loadedVillage2 = saveLoadManager.loadPlayerVillage("village2");

        // Verify the correct number of objects
        assertEquals(village1.size(), loadedVillage1.size());
        assertEquals(village2.size(), loadedVillage2.size());

        // Verify the villages have different numbers of objects
        assertNotEquals(loadedVillage1.size(), loadedVillage2.size());
    }

    /**
     * Helper method to create a set of game objects for testing
     *
    private Set<GameObject> createTestGameObjects() {
        Set<GameObject> objects = new HashSet<>();

        // Create a couple of different building types at different positions
        // Note: You'll need to adjust this based on your actual VillageElementData enum values
        objects.add(VillageElementData.getFactory(VillageElementData.TOWN_HALL)
                .apply(new VectorInt2D(10, 15)));
        objects.add(VillageElementData.getFactory(VillageElementData.BARRACKS)
                .apply(new VectorInt2D(20, 25)));
        objects.add(VillageElementData.getFactory(VillageElementData.GOLD_EXTRACTOR)
                .apply(new VectorInt2D(30, 35)));


     * Helper method to create a different set of game objects for testing
     */
    private Set<GameObject> createSecondTestGameObjects() {
        Set<GameObject> objects = new HashSet<>();

        objects.add(VillageElementData.getFactory(VillageElementData.TOWN_HALL)
                .apply(new VectorInt2D(5, 5)));

        return objects;
    }
}