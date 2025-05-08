package clashclass.saveload;

import clashclass.commons.Vector2D;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactory;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.*;
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
    private VillageDecoder playerDecoder;
    private VillageDecoder battleDecoder;
    private BuildingFactoryMapper playerFactoryMapper;
    private BuildingFactoryMapper battleFactoryMapper;

    @BeforeEach
    void setUp() {
        fileWriter = new SimpleFileWriterImpl();
        encoder = new VillageEncoderImpl();
        playerDecoder = new PlayerVillageDecoderImpl();
        battleDecoder = new BattleVillageDecoderImpl();
        BuildingFactory playerBuildingFactory = new PlayerBuildingFactoryImpl();
        BuildingFactory battleBuildingFactory = new BattleBuildingFactoryImpl();
        playerFactoryMapper = new BuildingFactoryMapper(playerBuildingFactory);
        battleFactoryMapper = new BuildingFactoryMapper(battleBuildingFactory);


        saveLoadManager = new VillageSaveLoadManager(
                encoder,
                playerDecoder,
                battleDecoder,
                fileWriter,
                tempDir
        );
    }
    @Test
    void testSaveLoadVillage() throws IOException {
        Set<GameObject> originalObjects = createTestGameObjects(playerFactoryMapper);
        String villageName = "testVillage";

        // Save the village
        saveLoadManager.saveVillage(originalObjects, villageName);

        // Verify save file exists
        Path savePath = tempDir.resolve(villageName + ".csv");
        assertTrue(Files.exists(savePath));

        // Load and verify the village
        Set<GameObject> loadedObjects = saveLoadManager.loadPlayerVillage(villageName);
        assertEquals(originalObjects.size(), loadedObjects.size());
    }

    @Test
    void testBattleVillageLoading() throws IOException {
        // Create some game objects
        Set<GameObject> originalObjects = createTestGameObjects(battleFactoryMapper);

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
    private Set<GameObject> createTestGameObjects(BuildingFactoryMapper factoryMapper) {
        Set<GameObject> objects = new HashSet<>();

        // Create buildings using the factory mapper
        objects.add(factoryMapper.getFactoryFor(VillageElementData.ARCHER_TOWER)
                .apply(new Vector2D(50, 55)));
        objects.add(factoryMapper.getFactoryFor(VillageElementData.GOLD_STORAGE)
                .apply(new Vector2D(60, 65)));
        objects.add(factoryMapper.getFactoryFor(VillageElementData.WALL)
                .apply(new Vector2D(70, 75)));
        objects.add(factoryMapper.getFactoryFor(VillageElementData.ELIXIR_EXTRACTOR)
                .apply(new Vector2D(80, 85)));

        return objects;
    }

}
