package clashclass.view.graphic;
import clashclass.ecs.GameObject;
import clashclass.ecs.GraphicComponent;

import java.util.Set;

/**
 * Interface to draw GameObjects.
 */
public interface Graphic {

    void clearRect();

    void render(Set<GraphicComponent> graphicComponents);

    /**
     * Method to draw a sprite.
     * @param gameObject the GameObject to draw
     * @param spriteName the key to identify the sprite
     */
    void drawSprites (GameObject gameObject, String spriteName);

    /**
     * Method to draw a rectangle.
     * @param gameObject the GameObject to draw
     */
    void drawRectangle (GameObject gameObject);
}
