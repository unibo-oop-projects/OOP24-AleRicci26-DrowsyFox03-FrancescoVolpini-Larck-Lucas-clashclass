package clashclass.view.graphic;
import clashclass.ecs.GameObject;

/**
 * Interface to draw GameObjects.
 */
public interface Graphic {

    void clearRect();

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
