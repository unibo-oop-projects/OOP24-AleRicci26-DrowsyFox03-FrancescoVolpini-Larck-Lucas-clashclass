package clashclass.saveload;

import clashclass.commons.BuildingTypeComponent;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.*;
import clashclass.resources.Player;
import clashclass.village.Village;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

        playerDecoder = new PlayerVillageDecoderImpl();
        playerDecoder.setComponentFactory(new ComponentFactoryImpl());

        battleDecoder = new BattleVillageDecoderImpl();
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
        Path resourcePath = Path.of("src/main/resources/Villages-Data/player-village.csv");
        String csv = Files.readString(resourcePath);

        final var objs = playerDecoder.decode(csv).getBuildings();

        assertFalse(objs.isEmpty(), "Il villaggio caricato dal file non deve essere vuoto");

        long townHallCount = objs.stream()
                .filter(go -> go.getComponentOfType(BuildingTypeComponent.class)
                        .map(BuildingTypeComponent::getBuildingType)
                        .filter(type -> type == VillageElementData.TOWN_HALL)
                        .isPresent())
                .count();

        assertTrue(townHallCount >= 1, "Almeno un Town Hall deve essere presente nel villaggio caricato");
    }

}
