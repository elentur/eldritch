package gui.buttons;

import enums.YesNo;
import javafx.scene.image.Image;

public class YesNoButton extends Button {

    private final static Image yes = new Image("images/OK.png",50,70,false,true,true);
    private final static Image no = new Image("images/Cancel.png",50,70,false,true,true);



    public YesNoButton(YesNo choice) {
        super(getBackgroundImage(choice));
        actualNode.setPickOnBounds(true);

    }

    private static Image getBackgroundImage(YesNo choice) {
        if (choice.equals(YesNo.YES)) {
           return yes;
        } else {
            return no;
        }
    }


}
