package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import utils.ResourceUtil;

class RollDiceButton extends Button {

    private final static Image backgroundImage = new Image("images/RoundButton.png");


    RollDiceButton() {
        super(new Rectangle(150, 150, new ImagePattern(backgroundImage)));
        Label label=new Label(ResourceUtil.get("${diceButton}","ui"));
        label.styleProperty().bind(Fonts.getFont(0.4, Fonts.DARK, Fonts.FontTyp.BOLD));
        label.setMouseTransparent(true);
        this.getChildren().add(label);

    }


}
