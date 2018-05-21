package gui;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

class ArrowButton extends Button {

    private final static Image backgroundImage = new Image("images/arrow.png");
    enum ArrowDir {
        UP, DOWN, LEFT, RIGHT
    }

    ArrowButton(ArrowDir dir) {
        super(new Rectangle(50, 70, new ImagePattern(backgroundImage)));

        if (dir.equals(ArrowDir.LEFT)) {
            image.setRotate(-90);
        } else if (dir.equals(ArrowDir.DOWN)) {
            image.setScaleY(-1);
        } else if (dir.equals(ArrowDir.RIGHT)) {
            image.setScaleY(-1);
            image.setRotate(-90);
        }
    }


}
