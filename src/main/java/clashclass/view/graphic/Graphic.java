package clashclass.view.graphic;
import clashclass.commons.Rect2D;
import clashclass.ecs.GameObject;
import clashclass.view.graphic.components.BaseGraphicComponent;

import java.util.Set;

/**
 * Interface to draw GameObjects.
 */
public interface Graphic {

    void clearRect();

    void render(Set<BaseGraphicComponent> graphicComponents);

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
    void drawRectangle (GameObject gameObject, String colorEx, Rect2D rect);
}
