package clashclass.view.graphic.components;

import clashclass.ecs.Component;
import clashclass.view.graphic.Graphic;

public interface DrawableComponent extends Component {
    void draw(Graphic graphics);
}
