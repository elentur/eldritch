package gui.buttons;

import javafx.scene.image.Image;

public class ArrowButton extends Button {

    private final static Image backgroundImage = new Image("images/arrow.png",50,70,false,true,true);
    public enum ArrowDir {
        UP, DOWN, LEFT, RIGHT
    }

    public ArrowButton(ArrowDir dir) {
        super(backgroundImage);

        if (dir.equals(ArrowDir.LEFT)) {
            imageView.setRotate(-90);
        } else if (dir.equals(ArrowDir.DOWN)) {
            imageView.setScaleY(-1);
        } else if (dir.equals(ArrowDir.RIGHT)) {
            imageView.setScaleY(-1);
            imageView.setRotate(-90);
        }
    }


}
