package gui.choice;

import enums.YesNo;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.YesNoChoice;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class InformationDialog extends ChoiceDialog {


    public InformationDialog(InformationChoice choice) {
        super(0.3, 0.3,choice);
        YesNoButton yesButton = new YesNoButton(YesNo.YES);
        yesButton.setOnMouseClicked(e->choice.setValue(true));
        StackPane.setAlignment(yesButton, Pos.BOTTOM_RIGHT);
        main.getChildren().addAll(yesButton);

     //   main.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
    }


}
