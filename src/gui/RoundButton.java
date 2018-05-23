package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import utils.ResourceUtil;

class RoundButton extends TextButton {

    private final static Image backgroundImage = new Image("images/RoundButton.png");


    RoundButton(String key) {
        super(key,new Rectangle(150, 150, new ImagePattern(backgroundImage)));
        this.setBorder(new Border(new BorderStroke(Fonts.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderStroke.MEDIUM)));

    }


}
