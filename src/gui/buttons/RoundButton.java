package gui.buttons;

import javafx.scene.image.Image;

public class RoundButton extends TextButton {

    private final static Image backgroundImage = new Image("images/RoundButton.png",150,150,true,true,false);


    public RoundButton(String key) {
        super(key, backgroundImage);
      //  this.setBorder(new Border(new BorderStroke(Fonts.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderStroke.MEDIUM)));

    }


}
