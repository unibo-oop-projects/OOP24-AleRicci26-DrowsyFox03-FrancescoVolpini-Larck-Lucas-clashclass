package clashclass.ecs;

import clashclass.view.graphic.Graphic;

public interface DrawableComponent extends Component {
    void draw(Graphic graphics);
}
