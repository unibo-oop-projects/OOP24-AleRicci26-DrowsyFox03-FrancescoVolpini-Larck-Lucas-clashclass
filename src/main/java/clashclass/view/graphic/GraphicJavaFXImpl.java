package clashclass.view.graphic;

import java.util.*;

import clashclass.commons.GameConstants;
import clashclass.commons.GridTileData2D;
import clashclass.commons.Rect2D;
import clashclass.ecs.GameObject;
import clashclass.commons.Transform2D;
import clashclass.elements.buildings.VillageElementData;
import clashclass.view.graphic.components.GraphicComponent;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
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
    private final Canvas canvas;

    GraphicJavaFXImpl(final GraphicsContext gc, final Canvas canvas, final double dpiW, final double dpiH) {
        this.gc = gc;
        this.canvas = canvas;
        this.dpiW = dpiW;
        this.dpiH = dpiH;
        storeSprites();
    }

    private void storeSprites() {
        this.spritesMap = new HashMap<>();
        this.spritesMap.put("grass-tile", this.loadImage("grass-tile"));
        this.spritesMap.put(VillageElementData.ARCHER_TOWER.getName(), this.loadImage(VillageElementData.ARCHER_TOWER.getName()));
        this.spritesMap.put(VillageElementData.BARRACKS.getName(), this.loadImage(VillageElementData.BARRACKS.getName()));
        this.spritesMap.put(VillageElementData.ARMY_CAMP.getName(), this.loadImage(VillageElementData.ARMY_CAMP.getName()));
        this.spritesMap.put(VillageElementData.CANNON.getName(), this.loadImage(VillageElementData.CANNON.getName()));
        this.spritesMap.put(VillageElementData.ELIXIR_EXTRACTOR.getName(), this.loadImage(VillageElementData.ELIXIR_EXTRACTOR.getName()));
        this.spritesMap.put(VillageElementData.ELIXIR_STORAGE.getName(), this.loadImage(VillageElementData.ELIXIR_STORAGE.getName()));
        this.spritesMap.put(VillageElementData.GOLD_EXTRACTOR.getName(), this.loadImage(VillageElementData.GOLD_EXTRACTOR.getName()));
        this.spritesMap.put(VillageElementData.GOLD_STORAGE.getName(), this.loadImage(VillageElementData.GOLD_STORAGE.getName()));
        this.spritesMap.put(VillageElementData.TOWN_HALL.getName(), this.loadImage(VillageElementData.TOWN_HALL.getName()));
        this.spritesMap.put(VillageElementData.WALL.getName(), this.loadImage(VillageElementData.WALL.getName()));
    }

    private Image loadImage(final String path) {
        return new Image(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("sprites/" + path + ".png")));
    }

    @Override
    public void clearRect() {
        this.gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    @Override
    public void render(final Set<GraphicComponent> graphicComponents) {
        Platform.runLater(() -> {
            this.clearRect();
            graphicComponents.stream()
                    .sorted(Comparator
                            .comparingInt(this::getSortingLayer)
                            .thenComparingDouble(this::getSortingIsometricCoordinates)
//                            .thenComparingDouble(this::getSortingIsometricCoordinates)
                    )
                    .forEach(graphicComponent -> graphicComponent.draw(this));
        });
    }

    private int getSortingLayer(final GraphicComponent graphicComponent) {
        return graphicComponent.getLayer();
    }

    private double getSortingIsometricCoordinates(final GraphicComponent graphicComponent) {
        final var tileData = graphicComponent.getGameObject()
                .getComponentOfType(GridTileData2D.class).get();
        final var bottom = tileData.getPosition();

//        final var top = new VectorInt2D(
//                bottom.x() - (tileData.getRowSpan() - 1),
//                bottom.y() - (tileData.getColSpan() - 1)
//        );

        return bottom.x() + bottom.y();
    }

    private double getSortingGridSpanWeight(final GraphicComponent graphicComponent) {
        final var gridTileData = graphicComponent.getGameObject()
                .getComponentOfType(GridTileData2D.class).get();

        return gridTileData.getRowSpan() + gridTileData.getColSpan();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawSprites(GameObject go, String spriteName) {
        go.getComponentOfType(GraphicComponent.class).ifPresent(component -> {
//            computeGameObjectBounds(go, component);
//            this.gc.drawImage(
//                spritesMap.get(spriteName),
//                    gameObjectX - width / 2,
//                    gameObjectY - height / 2,
//                    width,
//                    height);

            final var position = go.getComponentOfType(Transform2D.class).get().getPosition();
            final var image = spritesMap.get(spriteName);

            double scaleX = canvas.getWidth() / dpiW;
            double scaleY = canvas.getHeight() / dpiH;
            this.gc.save();
            this.gc.scale(scaleX, scaleY);

            this.gc.drawImage(
                    image,
                    position.x() - (image.getWidth() * GameConstants.TILE_SCALE) / 2,
                    position.y() - (image.getHeight() * GameConstants.TILE_SCALE),
                    image.getWidth() * GameConstants.TILE_SCALE,
                    image.getHeight() * GameConstants.TILE_SCALE);
            this.gc.restore();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRectangle(final GameObject go, final String colorEx, final Rect2D rect) {
        go.getComponentOfType(GraphicComponent.class).ifPresent(graphicComponent -> {
//            computeGameObjectBounds(go, graphicComponent);

            double scaleX = canvas.getWidth() / dpiW;
            double scaleY = canvas.getHeight() / dpiH;
            this.gc.save();
            this.gc.scale(scaleX, scaleY);

            final var color = Color.web(colorEx);
            this.gc.setFill(color);
            this.gc.fillRect(
                    rect.position().x(),
                    rect.position().y(),
                    rect.size().x(),
                    rect.size().y());
            this.gc.restore();
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
