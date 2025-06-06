package clashclass.battle.manager;

import clashclass.battle.battlereport.BattleReportView;
import clashclass.battle.battlereport.BattleReportViewImpl;
import clashclass.battle.battlereport.BattleReportViewJavaFXImpl;
import clashclass.commons.GameConstants;
import clashclass.commons.Vector2D;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Represents a {@link BattleManagerView} JavaFX implementation.
 */
public class BattleManagerViewJavaFXImpl implements BattleManagerView {
    private Scene scene;
    private AnchorPane root;
    private Button endBattleButton;
    private Label battleTimeLabel;
    private final List<ToggleButton> troopToggles = new ArrayList<>();
    private HBox troopTogglesContainer;
    private BattleManagerController controller;
    private double togglesFontSize;

    /**
     * Constructs the view.
     *
     * @param scene the scene reference
     * @param root the root reference
     */
    public BattleManagerViewJavaFXImpl(final Scene scene, final AnchorPane root) {
        this.scene = scene;
        this.root = root;

        this.root.setStyle("-fx-background-color: #0A8F32;");

        this.endBattleButton = new Button("End Battle");
        root.getChildren().add(endBattleButton);
        AnchorPane.setTopAnchor(endBattleButton, 20.0);
        AnchorPane.setLeftAnchor(endBattleButton, 20.0);
        endBattleButton.prefWidthProperty().bind(root.widthProperty().multiply(0.15));
        endBattleButton.prefHeightProperty().bind(root.heightProperty().multiply(0.15));

        endBattleButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() * 0.1;
            endBattleButton.setStyle("-fx-font-size: " + newFontSize + "px;");
        });
        endBattleButton.setOnAction(event -> {
            this.controller.endBattle();
        });

        final var canvas = (Canvas) this.root.lookup("#canvas");
        canvas.setOnMousePressed(event -> {
            if (event.isConsumed()) return;

            double scaleX = canvas.getWidth() / GameConstants.SCREEN_WIDTH;
            double scaleY = canvas.getHeight() / GameConstants.SCREEN_HEIGHT;
            double worldX = event.getSceneX() / scaleX;
            double worldY = event.getSceneY() / scaleY;

            this.controller.createTroop(new Vector2D(worldX, worldY));
        });

        this.battleTimeLabel = new Label("");
        root.getChildren().add(battleTimeLabel);
        AnchorPane.setTopAnchor(battleTimeLabel, 20.0);
        AnchorPane.setRightAnchor(battleTimeLabel, 20.0);
        battleTimeLabel.prefWidthProperty().bind(root.widthProperty().multiply(0.15));
        battleTimeLabel.prefHeightProperty().bind(root.heightProperty().multiply(0.15));
        battleTimeLabel.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() * 0.1;
            battleTimeLabel.setStyle("-fx-font-size: " + newFontSize + "px;");
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final BattleManagerController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArmyCampTroops(final BattleManagerModel model) {
        final var player = model.getBattleVillage().getPlayer();
        final var troopTypes = player.getArmyCampTroopTypes();

        final var toggleGroup = new ToggleGroup();
        this.troopToggles.clear();
        troopTypes.forEach(troopType -> {
            final var toggle = new ToggleButton(troopType.toString().toUpperCase() + "\n" +
                    "[" + player.getArmyCampTroopCount(troopType) + "]");
            toggle.setWrapText(true);
            toggle.setToggleGroup(toggleGroup);
            this.togglesFontSize = toggle.getWidth() * 0.1;
            toggle.widthProperty().addListener((obs, oldVal, newVal) -> {
                this.togglesFontSize = newVal.doubleValue() * 0.1;
                if (toggle.isSelected()) {
                    toggle.setStyle("-fx-font-size: " + this.togglesFontSize + "px;-fx-border-width: 3px; -fx-border-color: blue;");
                } else {
                    toggle.setStyle("-fx-font-size: " + this.togglesFontSize + "px;");
                }
            });
            toggle.setOnAction(event -> {
                if (event.isConsumed()) return;
                if (toggle.isSelected()) {
                    this.controller.setCurrentSelectedTroop(troopType);
                }
                event.consume();
            });
            toggle.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                if (isSelected) {
                    toggle.setStyle("-fx-font-size: " + this.togglesFontSize + "px;-fx-border-width: 3px; -fx-border-color: blue;");
                } else {
                    toggle.setStyle("-fx-font-size: " + this.togglesFontSize + "px;");
                }
            });
            toggle.prefWidthProperty().bind(root.widthProperty().multiply(0.15));
            toggle.prefHeightProperty().bind(root.heightProperty().multiply(0.15));
            this.troopToggles.add(toggle);
        });

        if (!this.troopToggles.isEmpty()) {
            this.controller.setCurrentSelectedTroop(troopTypes.stream().findFirst().get());
            this.troopToggles.getFirst().setSelected(true);
        }

        this.troopTogglesContainer = new HBox(10);
        this.troopToggles.forEach(toggle -> this.troopTogglesContainer.getChildren().add(toggle));
        root.getChildren().add(1, troopTogglesContainer);
        AnchorPane.setBottomAnchor(troopTogglesContainer, 20.0);
        AnchorPane.setLeftAnchor(troopTogglesContainer, 20.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateArmyCampTroopsCount(final BattleManagerModel model) {
        final var player = model.getPlayerVillage().getPlayer();
        final var troopTypes = player.getArmyCampTroopTypes().stream().toList();

        IntStream.range(0, troopTypes.size()).forEach(i -> {
            final var troopType = troopTypes.get(i);
            this.troopToggles.get(i).setText(troopType.toString().toUpperCase() + "\n" +
                    "[" + player.getArmyCampTroopCount(troopType) + "]");
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.root.getChildren().remove(this.endBattleButton);
        this.root.getChildren().remove(this.troopTogglesContainer);
        this.root.getChildren().remove(this.battleTimeLabel);
    }

    @Override
    public void endBattle(final BattleManagerModel model) {
        Platform.runLater(model::showBattleReport);
    }

    @Override
    public BattleReportView buildBattleReportView() {
        return new BattleReportViewJavaFXImpl(this.root);
    }
}
