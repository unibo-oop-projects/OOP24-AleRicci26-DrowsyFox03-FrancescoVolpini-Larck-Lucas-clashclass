package clashclass.saveload;

import clashclass.commons.Vector2D;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
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
    private PlayerVillageDecoderImpl playerDecoder;
    private BattleVillageDecoderImpl battleDecoder;
    private BuildingFactoryMapper<PlayerBuildingFactoryImpl> playerMapper;
    private BuildingFactoryMapper<BattleBuildingFactoryImpl> battleMapper;


    @BeforeEach
    void setUp() {
        fileWriter = new SimpleFileWriterImpl();
        encoder = new VillageEncoderImpl();

        PlayerBuildingFactoryImpl playerBuildingFactory = new PlayerBuildingFactoryImpl();
        BattleBuildingFactoryImpl battleBuildingFactory = new BattleBuildingFactoryImpl();

        playerDecoder = new PlayerVillageDecoderImpl(playerBuildingFactory);
        playerDecoder.setComponentFactory(new ComponentFactoryImpl());

        battleDecoder = new BattleVillageDecoderImpl(battleBuildingFactory);
        battleDecoder.setComponentFactory(new ComponentFactoryImpl());

        playerMapper = new BuildingFactoryMapper<>(playerBuildingFactory);
        battleMapper = new BuildingFactoryMapper<>(battleBuildingFactory);

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
        Set<GameObject> originalObjects = createTestGameObjects(playerMapper);
        String villageName = "default_village";

        // Save the village
        saveLoadManager.saveVillage(originalObjects, villageName);

        // Verify save file exists
        Path savePath = tempDir.resolve(villageName + ".csv");
        assertTrue(Files.exists(savePath));

        // Load and verify the village
        final var loadedObjects = saveLoadManager.loadPlayerVillage(villageName).getBuildings();
        assertEquals(originalObjects.size(), loadedObjects.size());
    }

    @Test
    void testBattleVillageLoading() throws IOException {
        // Create some game objects
        Set<GameObject> originalObjects = createTestGameObjects(battleMapper);

        // Save the village
        String fileName = "campaign_village";
        saveLoadManager.saveVillage(originalObjects, fileName);

        // Load as battle village
        final var loadedObjects = saveLoadManager.loadBattleVillage(fileName).getBuildings();

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
    void loadVillageFromResources() throws IOException {
        Path resourcePath = Path.of("src/main/resources/villages/village01.csv");
        String csv = Files.readString(resourcePath);

        final var objs = playerDecoder.decode(csv).getBuildings();

        assertFalse(objs.isEmpty(), "Il villaggio caricato dal file non deve essere vuoto");

        long townHallCount = objs.stream()
                .filter(go -> go.getComponents().stream()
                        .anyMatch(c -> c.getClass().getSimpleName().equals("TownHallComponent")
                                || c.getClass().getSimpleName().equals("TOWN_HALLComponent"))) // dipende da nome componente
                .count();

        assertTrue(townHallCount >= 1, "Almeno un Town Hall deve essere presente nel villaggio caricato");
    }
    private Set<GameObject> createTestGameObjects(BuildingFactoryMapper<?> factoryMapper) {
        Set<GameObject> objects = new HashSet<>();

        // Create buildings using the factory mapper
        objects.add(factoryMapper.getFactoryFor(VillageElementData.ARCHER_TOWER)
                .apply(new VectorInt2D(50, 55)));
        objects.add(factoryMapper.getFactoryFor(VillageElementData.GOLD_STORAGE)
                .apply(new VectorInt2D(60, 65)));
        objects.add(factoryMapper.getFactoryFor(VillageElementData.WALL)
                .apply(new VectorInt2D(70, 75)));
        objects.add(factoryMapper.getFactoryFor(VillageElementData.ELIXIR_EXTRACTOR)
                .apply(new VectorInt2D(80, 85)));

        return objects;
    }

}
