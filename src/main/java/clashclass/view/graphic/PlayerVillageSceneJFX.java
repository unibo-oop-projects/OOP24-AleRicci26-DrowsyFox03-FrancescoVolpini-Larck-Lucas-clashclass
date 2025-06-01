package clashclass.view.graphic;

import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.saveload.PlayerVillageDecoderImpl;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public class PlayerVillageSceneJFX extends VillageSceneJFX {

    public PlayerVillageSceneJFX(Window window, Stage stage, Path csvPath) throws IOException {
        super(window, stage, csvPath);
    }

    /**
     * @param go
     * @return
     */
    @Override
    protected String mapTypeToSprite(GameObject go) {
        return "";
    }

    /**
     * @return
     */
    @Override
    protected String getSceneTitle() {
        return "Player Village";
    }
}
