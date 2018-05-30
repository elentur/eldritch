package gui.choice;

import enums.YesNo;
import gamemechanics.choice.YesNoChoice;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class YesNoDialog extends ChoiceDialog {


    public YesNoDialog( YesNoChoice choice) {
        super(0.3, 0.3,choice);
        YesNoButton yesButton = new YesNoButton(YesNo.YES);
        YesNoButton noButton = new YesNoButton(YesNo.NO);

        yesButton.setOnMouseClicked(e->choice.setValue(YesNo.YES));
        noButton.setOnMouseClicked(e->choice.setValue(YesNo.NO));

        StackPane.setAlignment(yesButton, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(noButton, Pos.BOTTOM_RIGHT);
        main.getChildren().addAll(yesButton,noButton);

      //  main.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
    }


}
