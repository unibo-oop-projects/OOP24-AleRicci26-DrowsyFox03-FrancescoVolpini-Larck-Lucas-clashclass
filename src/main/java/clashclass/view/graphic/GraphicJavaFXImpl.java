package clashclass.view.graphic;

import java.util.HashMap;
import java.util.Map;

import clashclass.ecs.GameObject;
import clashclass.commons.Transform2D;
import clashclass.ecs.GraphicComponent;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class that implements Graphic using JavaFX.
 */
public class GraphicJavaFXImpl implements Graphic {
    private final GraphicsContext gc;
    private Map<String, Image> spritesMap;
    private double gameObjectX;
    private double gameObjectY;
    private final double dpiW;
    private final double dpiH;
    private double width;
    private double height;

    GraphicJavaFXImpl(final GraphicsContext gc, final double dpiW, final double dpiH) {
        this.gc = gc;
        this.dpiW = dpiW;
        this.dpiH = dpiH;
        storeSprites();
    }

    private void storeSprites() {
        spritesMap = new HashMap<>();
        this.spritesMap.put("grass",
                new Image(ClassLoader.getSystemResourceAsStream("sprites/grass.jpg")));
        this.spritesMap.put("wall",
                new Image(ClassLoader.getSystemResourceAsStream("sprites/wall.jpg")));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawSprites(GameObject go, String spriteName) {
        go.getComponentOfType(GraphicComponent.class).ifPresent(component -> {
            computeGameObjectBounds(go, component);
            this.gc.drawImage(
                spritesMap.get(spriteName),
                    gameObjectX - width / 2,
                    gameObjectY - height / 2,
                    width,
                    height);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRectangle(GameObject go) {
        go.getComponentOfType(GraphicComponent.class).ifPresent(graphicComponent -> {
            computeGameObjectBounds(go, graphicComponent);
            this.gc.setFill(Color.GREEN);
            this.gc.fillRect(
                    gameObjectX - width / 2,
                    gameObjectY - height / 2,
                    width,
                    height);
        });
    }

    private void computeGameObjectBounds(GameObject go, GraphicComponent graphicComponent) {
        go.getComponentOfType(Transform2D.class).ifPresent(t2DComponent -> {
            gameObjectX = t2DComponent.getPosition().x() * this.dpiW;
            gameObjectY = t2DComponent.getPosition().y() * this.dpiH;
        });
        width = graphicComponent.getWidth() * this.dpiW;
        height = graphicComponent.getHeight() * this.dpiH;
    }
}
