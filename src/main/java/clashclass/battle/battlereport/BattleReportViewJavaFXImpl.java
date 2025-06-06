package clashclass.battle.battlereport;

import clashclass.resources.ResourceManager;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class BattleReportViewJavaFXImpl implements BattleReportView {
    private final AnchorPane root;
    private final StackPane menuContainer;
    private final Label starsLabel;
    private final Label destructionPercentageLabel;
    private final Label troopCountLabel;
    private final Label battleResultLabel;
    private BattleReportController controller;

    public BattleReportViewJavaFXImpl(final AnchorPane root) {
        this.root = root;

        Label titleLabel = new Label("BATTLE REPORT");
        this.starsLabel = new Label("");
        this.destructionPercentageLabel = new Label("");
        this.troopCountLabel = new Label("");
        this.battleResultLabel = new Label("");

        this.setLabelAutoResize(titleLabel);
        this.setLabelAutoResize(this.starsLabel);
        this.setLabelAutoResize(this.destructionPercentageLabel);
        this.setLabelAutoResize(this.troopCountLabel);
        this.setLabelAutoResize(this.battleResultLabel);

        Button button = new Button("Go Back To Village");
        button.prefWidthProperty().bind(root.widthProperty().multiply(0.15));
        button.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() * 0.1;
            button.setStyle("-fx-font-size: " + newFontSize + "px;");
        });
        button.setOnAction(event -> this.controller.goBackToVillage());

        VBox menuBox = new VBox(20);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setStyle("-fx-background-color: #FFFFFFBB; -fx-padding: 20px; -fx-border-color: black;");

        menuBox.getChildren().add(titleLabel);
        menuBox.getChildren().add(starsLabel);
        menuBox.getChildren().add(destructionPercentageLabel);
        menuBox.getChildren().add(troopCountLabel);
        menuBox.getChildren().add(battleResultLabel);
        menuBox.getChildren().add(button);

        this.menuContainer = new StackPane(menuBox);
        this.menuContainer.setVisible(false);

        AnchorPane.setTopAnchor(this.menuContainer, 0.0);
        AnchorPane.setBottomAnchor(this.menuContainer, 0.0);
        AnchorPane.setLeftAnchor(this.menuContainer, 0.0);
        AnchorPane.setRightAnchor(this.menuContainer, 0.0);

        this.root.getChildren().add(this.menuContainer);
    }

    private void setLabelAutoResize(final Label label) {
        label.prefWidthProperty().bind(root.widthProperty().multiply(0.15));
        label.widthProperty().addListener((obs, oldVal, newVal) -> {
            final double newFontSize = newVal.doubleValue() * 0.1;
            label.setStyle("-fx-font-size: " + newFontSize + "px;");
        });
    }

    @Override
    public void update(final BattleReportModel model) {
        this.displayStars(model.getStars());
        this.displayDestructionPercentage(model.getDestructionPercentage());
        this.displayTroopCount(model.getTroopCount());
        this.displayBattleResult(model.isVictory());
    }

    @Override
    public void displayDestructionPercentage(final double percentage) {
        Platform.runLater(() -> this.destructionPercentageLabel.setText("Destruction: " + (int) percentage + "%"));
    }

    @Override
    public void displayStars(final int stars) {
        Platform.runLater(() ->this.starsLabel.setText("Stars: " + stars + " / 3"));
    }

    @Override
    public void displayStolenResources(final ResourceManager resources) {

    }

    @Override
    public void displayBattleResult(final boolean isVictory) {
        Platform.runLater(() ->this.battleResultLabel.setText(isVictory ? "VICTORY!" : "DEFEAT!"));
    }

    @Override
    public void displayTroopCount(int troopCount) {
        Platform.runLater(() ->this.troopCountLabel.setText("Used Troops: " + troopCount));
    }

    @Override
    public void show() {
        this.menuContainer.setVisible(true);
    }

    @Override
    public void clearScene() {
        this.root.getChildren().remove(this.menuContainer);
    }

    @Override
    public void setController(final BattleReportController controller) {
        this.controller = controller;
    }
}
