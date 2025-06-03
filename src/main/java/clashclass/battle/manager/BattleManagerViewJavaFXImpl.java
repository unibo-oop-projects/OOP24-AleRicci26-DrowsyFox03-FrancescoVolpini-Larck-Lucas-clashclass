package clashclass.battle.manager;

import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class BattleManagerViewJavaFXImpl implements BattleManagerView {
    private final AnchorPane root;
    private final Button endBattleButton;
    private final List<ToggleButton> troopToggles = new ArrayList<>();
    private HBox troopTogglesContainer;
    private BattleManagerController controller;

    public BattleManagerViewJavaFXImpl(final AnchorPane root) {
        this.root = root;
        this.root.setStyle("-fx-background-color: #0A8F32;");

        this.endBattleButton = new Button("End Battle");
        root.getChildren().add(endBattleButton);
        AnchorPane.setTopAnchor(endBattleButton, 20.0);
        AnchorPane.setLeftAnchor(endBattleButton, 20.0);
        endBattleButton.prefWidthProperty().bind(root.widthProperty().multiply(0.1));
        endBattleButton.prefHeightProperty().bind(root.heightProperty().multiply(0.15));

        endBattleButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() * 0.2f;
            endBattleButton.setStyle("-fx-font-size: " + newFontSize + "px;");
        });
        endBattleButton.setOnAction(event -> this.controller.endBattle());
    }

    @Override
    public void setController(final BattleManagerController controller) {
        this.controller = controller;
    }

    @Override
    public void setArmyCampTroops(final BattleManagerModel model) {
        final var player = model.getBattleVillage().getPlayer();
        final var troopTypes = player.getArmyCampTroopTypes();

        final var toggleGroup = new ToggleGroup();
        this.troopToggles.clear();
        troopTypes.forEach(troopType -> {
            final var toggle = new ToggleButton(troopType.toString().toUpperCase());
            toggle.setToggleGroup(toggleGroup);
            toggle.setOnAction(event -> {
                if (toggle.isSelected()) {
                    this.controller.setCurrentSelectedTroop(troopType);
                }
            });
            toggle.prefWidthProperty().bind(root.widthProperty().multiply(0.1));
            toggle.prefHeightProperty().bind(root.heightProperty().multiply(0.15));
            this.troopToggles.add(toggle);
        });

        if (!this.troopToggles.isEmpty()) {
            this.troopToggles.getFirst().setSelected(true);
        }

        this.troopTogglesContainer = new HBox(10);
        this.troopToggles.forEach(toggle -> this.troopTogglesContainer.getChildren().add(toggle));
        root.getChildren().add(troopTogglesContainer);
        AnchorPane.setBottomAnchor(troopTogglesContainer, 20.0);
        AnchorPane.setLeftAnchor(troopTogglesContainer, 20.0);
//        troopTogglesContainer.prefWidthProperty().bind(root.widthProperty().multiply(0.1));
//        troopTogglesContainer.prefHeightProperty().bind(root.heightProperty().multiply(0.15));
    }

    @Override
    public void clearScene() {
        this.root.getChildren().remove(this.endBattleButton);
        this.root.getChildren().remove(this.troopTogglesContainer);
    }
}
