package clashclass.view.graphic;

import clashclass.commons.ConversionUtility;
import clashclass.commons.GridTileData2D;
import clashclass.commons.Vector2D;
import clashclass.village.Village;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.engine.GameEngine;
import clashclass.engine.GameEngineImpl;
import clashclass.resources.Player;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.saveload.VillageDecoder;
import clashclass.village.manager.PlayerVillageController;
import clashclass.village.manager.PlayerVillageControllerImpl;
import clashclass.village.manager.PlayerVillageModelImpl;
import clashclass.village.manager.PlayerVillageViewJavaFXImpl;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public abstract class VillageSceneJFX extends AbstractBaseScene {
    private final VillageDecoder decoder;
    protected final GraphicsContext gc;
    protected final Village village;

    public VillageSceneJFX(Window window, Stage stage, Path csvPath) throws IOException {
        super(window);

        decoder = new PlayerVillageDecoderImpl(new PlayerBuildingFactoryImpl());
        decoder.setComponentFactory(new ComponentFactoryImpl());

        String csvData = Files.readString(csvPath);
        this.village = decodeVillage(csvData);

        Canvas canvas = new Canvas(getWindowWidth(), getWindowHeight());
        this.gc = canvas.getGraphicsContext2D();
        this.setGraphics(new GraphicJavaFXImpl(gc, canvas, getWindowWidth(), getWindowHeight()));

        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #0a8f32;");
        root.getChildren().add(canvas);
        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);

        Button battleButton = new Button("Battle");
        root.getChildren().add(battleButton);
        AnchorPane.setBottomAnchor(battleButton, 20.0);
        AnchorPane.setLeftAnchor(battleButton, 20.0);
        battleButton.prefWidthProperty().bind(root.widthProperty().multiply(0.1));
        battleButton.prefHeightProperty().bind(root.heightProperty().multiply(0.15));

        battleButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() * 0.2f;
            battleButton.setStyle("-fx-font-size: " + newFontSize + "px;");
        });

        Button shopButton = new Button("Shop");
        root.getChildren().add(shopButton);
        AnchorPane.setBottomAnchor(shopButton, 20.0);
        AnchorPane.setRightAnchor(shopButton, 20.0);
        shopButton.prefWidthProperty().bind(root.widthProperty().multiply(0.1));
        shopButton.prefHeightProperty().bind(root.heightProperty().multiply(0.15));

        shopButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() * 0.2f;
            shopButton.setStyle("-fx-font-size: " + newFontSize + "px;");
        });

        Scene scene = new Scene(root, getWindowWidth(), getWindowHeight());
        stage.setScene(scene);
        stage.setTitle(getSceneTitle());
        stage.show();

        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());

//        canvas.widthProperty().addListener((obs, oldVal, newVal) -> redraw());
//        canvas.heightProperty().addListener((obs, oldVal, newVal) -> redraw());

        final GameEngine gameEngine = new GameEngineImpl(Optional.of(this.getGraphics()));

        stage.setOnCloseRequest(event -> {
            gameEngine.stop();
        });

        scene.setOnMouseClicked(event -> {
            double worldX = event.getSceneX();
            double worldY = event.getSceneY();

            final var gridPosition = ConversionUtility
                    .convertWorldToGridPosition(new Vector2D(worldX, worldY));

            System.out.println(gridPosition.x() + " " + gridPosition.y());
        });

        final var player = new Player();
        final var village = new Village(player);

        this.village.getBuildings().forEach(gameObject -> {
            final var gridTileData = gameObject.getComponentOfType(GridTileData2D.class).get();
            village.placeBuilding(
                    gameObject,
                    gridTileData.getPosition(),
                    gridTileData.getRowSpan(),
                    gridTileData.getColSpan());
        });

        village.getGroundObjects().forEach(gameEngine::addGameObject);
        village.getGameObjects().forEach(gameEngine::addGameObject);

        final var playerVillageController = new PlayerVillageControllerImpl(
                new PlayerVillageModelImpl(),
                new PlayerVillageViewJavaFXImpl(gameEngine)
        );

        gameEngine.start();

        initializeScene();
    }
    /**
     *
     */
    @Override
    public void initializeScene() {

    }

    private Village decodeVillage(String csvData) {
        return this.decoder.decode(csvData);
    }

    // Da implementare per mappare correttamente i tipi in sprite
    protected abstract String mapTypeToSprite(GameObject go);

    // Titolo finestra specifico
    protected abstract String getSceneTitle();
}
