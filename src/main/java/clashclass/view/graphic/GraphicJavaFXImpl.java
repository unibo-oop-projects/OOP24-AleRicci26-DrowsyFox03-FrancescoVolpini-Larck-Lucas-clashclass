package clashclass.view.graphic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        this.spritesMap = new HashMap<>();
        this.spritesMap.put("grass-tile", this.loadImage("grass-tile.png"));
        this.spritesMap.put("archer-tower", this.loadImage("archer-tower.png"));
        this.spritesMap.put("barracks", this.loadImage("barracks.png"));
        this.spritesMap.put("campfire", this.loadImage("campfire.png"));
        this.spritesMap.put("cannon", this.loadImage("cannon.png"));
        this.spritesMap.put("elisir-extractor", this.loadImage("elisir-extractor.png"));
        this.spritesMap.put("elisir-storage", this.loadImage("elisir-storage.png"));
        this.spritesMap.put("gold-extractor", this.loadImage("gold-extractor.png"));
        this.spritesMap.put("gold-storage", this.loadImage("gold-storage.png"));
        this.spritesMap.put("town-hall", this.loadImage("town-hall.png"));
        this.spritesMap.put("wall", this.loadImage("wall.png"));
    }

    private Image loadImage(final String path) {
        return new Image(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("sprites/" + path)));
    }

    @Override
    public void clearRect() {
        this.gc.clearRect(0, 0, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawSprites(GameObject go, String spriteName) {
        go.getComponentOfType(GraphicComponent.class).ifPresent(component -> {
            computeGameObjectBounds(go, component);
//            this.gc.drawImage(
//                spritesMap.get(spriteName),
//                    gameObjectX - width / 2,
//                    gameObjectY - height / 2,
//                    width,
//                    height);

            final var position = go.getComponentOfType(Transform2D.class).get().getPosition();
            final var image = spritesMap.get(spriteName);

            this.gc.drawImage(
                    image,
                    position.x() * 23 - image.getWidth() / 2,
                    position.y() * 23 - image.getHeight() / 2);
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
            gameObjectX = t2DComponent.getPosition().x();// * this.dpiW;
            gameObjectY = t2DComponent.getPosition().y();// * this.dpiH;
        });
        width = graphicComponent.getWidth(); // * this.dpiW;
        height = graphicComponent.getHeight(); // * this.dpiH;
    }
}
