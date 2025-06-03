package clashclass.village.manager;

import clashclass.commons.Rect2D;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.commons.CommonGameObjectFactoryImpl;
import clashclass.engine.GameEngine;
import clashclass.view.graphic.components.UIRendererImpl;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PlayerVillageViewJavaFXImpl implements PlayerVillageView {
    private final GameObject uiGameObject;
    private final AnchorPane root;
    private PlayerVillageController controller;

    public PlayerVillageViewJavaFXImpl(final GameEngine gameEngine, final AnchorPane root) {
        this.root = root;
        final var factory = new CommonGameObjectFactoryImpl();
        this.uiGameObject = factory.createUIElement();
        gameEngine.addGameObject(this.uiGameObject);

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
        battleButton.setOnAction(event -> this.controller.openBattleMode());

        Button shopButton = new Button("Shop");
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

    private void redraw(final PlayerVillageModel model) {
        uiGameObject.getComponentOfType(UIRendererImpl.class).ifPresent(uiRenderer -> {
            uiRenderer.setDrawFunction(graphics -> {
//                graphics.drawRectangle(this.uiGameObject, "#FF0000", new Rect2D(
//                        new VectorInt2D(20, 100),
//                        new VectorInt2D(300, 50)));
                // TODO ...
            });
        });
    }
}
