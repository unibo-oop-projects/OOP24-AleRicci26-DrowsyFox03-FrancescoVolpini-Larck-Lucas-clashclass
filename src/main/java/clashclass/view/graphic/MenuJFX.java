package clashclass.view.graphic;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JavaFX implementation of a menu scene.
 * Responsible for initializing and displaying the main menu on the provided Stage.
 */
public class MenuJFX extends AbstractBaseScene{
    private final Stage stage;

    MenuJFX(final Stage stage, final Window window) {
        super(window);
        this.stage = stage;
    }
    /**
     * Method that initialize the BaseScene.
     */
    @Override
    public void initializeScene() {
        Image image = new Image(ClassLoader.getSystemResourceAsStream("sprites/town-hall.png"));

        ImageView imageView = new ImageView(image);
        imageView.setX(100);
        imageView.setY(150);
        imageView.setFitWidth(64);
        imageView.setFitHeight(64);

        Pane root = new Pane(imageView);
        Scene scene = new Scene(root, 800, 600);

        this.stage.setTitle("clashclass");
        this.stage.setScene(scene);
        this.stage.show();
    }
}
