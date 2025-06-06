package clashclass.village.manager;

import clashclass.elements.commons.CommonGameObjectFactoryImpl;
import clashclass.shop.ShopMenuJavaFXImpl;
import clashclass.shop.ShopMenuView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PlayerVillageViewJavaFXImpl implements PlayerVillageView {
    private final AnchorPane root;
    private final Button battleButton;
    private final Button shopButton;
    private PlayerVillageController controller;

    public PlayerVillageViewJavaFXImpl(
            final Scene scene,
            final AnchorPane root,
            final double widowWidth,
            final double windowHeight) {
        final var factory = new CommonGameObjectFactoryImpl();

        this.root = root;
        this.root.setStyle("-fx-background-color: #0A8F32;");

        this.battleButton = new Button("Battle");
        root.getChildren().add(battleButton);
        AnchorPane.setBottomAnchor(battleButton, 20.0);
        AnchorPane.setLeftAnchor(battleButton, 20.0);
        battleButton.prefWidthProperty().bind(root.widthProperty().multiply(0.1));
        battleButton.prefHeightProperty().bind(root.heightProperty().multiply(0.15));

        battleButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() * 0.2f;
            battleButton.setStyle("-fx-font-size: " + newFontSize + "px;");
        });
        battleButton.setOnAction(event -> this.controller.openBattleMode());

        this.shopButton = new Button("Shop");
        root.getChildren().add(shopButton);
        AnchorPane.setBottomAnchor(shopButton, 20.0);
        AnchorPane.setRightAnchor(shopButton, 20.0);
        shopButton.prefWidthProperty().bind(root.widthProperty().multiply(0.1));
        shopButton.prefHeightProperty().bind(root.heightProperty().multiply(0.15));

        shopButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newFontSize = newVal.doubleValue() * 0.2;
            shopButton.setStyle("-fx-font-size: " + newFontSize + "px;");
        });
        shopButton.setOnAction(event -> this.controller.openShop());
    }

    @Override
    public void setController(final PlayerVillageController controller) {
        this.controller = controller;
    }

    @Override
    public void update(final PlayerVillageModel model) {
        this.redraw(model);
    }

    @Override
    public void clearScene() {
        Platform.runLater(() -> {
            this.root.getChildren().remove(this.battleButton);
            this.root.getChildren().remove(this.shopButton);
        });
    }

    @Override
    public ShopMenuView getShopMenuView() {
        return new ShopMenuJavaFXImpl(this.root);
    }

    private void redraw(final PlayerVillageModel model) {
//        uiGameObject.getComponentOfType(UIRendererImpl.class).ifPresent(uiRenderer -> {
//            uiRenderer.setDrawFunction(graphics -> {
////                graphics.drawRectangle(this.uiGameObject, "#FF0000", new Rect2D(
////                        new VectorInt2D(20, 100),
////                        new VectorInt2D(300, 50)));
//                // TODO ...
//            });
//        });
    }
}
