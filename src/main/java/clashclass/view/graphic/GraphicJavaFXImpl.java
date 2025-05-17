package clashclass.view.graphic;

import java.util.HashMap;
import java.util.Map;

import clashclass.ecs.GameObject;
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
                new Image(ClassLoader.getSystemResourceAsStream("sprites/grass.png")));
        this.spritesMap.put("wall",
                new Image(ClassLoader.getSystemResourceAsStream("sprites/wall.png")));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawSprites(GameObject go, String spriteName) {
        go.getComponentOfType(GraphicComponent.class).ifPresent(component -> {
            gameObjectX = GameObject.getPosition.getX() * this.dpiW;    //TODO getPosition
            gameObjectY = GameObject.getPosition.getY() * this.dpiH;
            width = component.getWidth() * this.dpiW;
            height = component.getHeight() * this.dpiH;
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
        go.getComponentOfType(GraphicComponent.class).ifPresent(component -> {
            gameObjectX = GameObject.getPosition.getX() * this.dpiW;
            gameObjectY = GameObject.getPosition.getY() * this.dpiH;
            width = component.getWidth() * this.dpiW;
            height = component.getHeight() * this.dpiH;
            this.gc.setFill(Color.GREEN);
            this.gc.fillRect(
                    gameObjectX - width / 2,
                    gameObjectY - height / 2,
                    width,
                    height);
        });
    }
}
