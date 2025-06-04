package clashclass.view.graphic;

import clashclass.ecs.GameObject;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class PlayerVillageSceneJFX extends VillageSceneJFX {

    public PlayerVillageSceneJFX(Window window, Stage stage, Path playerCsvPath, Path battleCsvPath) throws IOException {
        super(window, stage, playerCsvPath, battleCsvPath);
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
        return "Clash Of Class";
    }
}
