package clashclass.view.graphic;
import clashclass.ecs.GameObject;

/**
 * Interface to draw GameObjects.
 */
public interface Graphic {

    /**
     * Method to draw a sprite.
     * @param gameObject the Gameobject to draw
     * @param spriteName the key to identify the sprite
     */
    void drawSprites (GameObject gameObject, String spriteName);

    /**
     * Method to draw a rectangle.
     * @param gameObject the Gameobject to draw
     */
    void drawRectangle (GameObject gameObject);
}
