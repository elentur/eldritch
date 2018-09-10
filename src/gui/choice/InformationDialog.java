package gui.choice;

import enums.YesNo;
import gamemechanics.choice.InformationChoice;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class InformationDialog extends ChoiceDialog {


    public InformationDialog(InformationChoice choice) {
        super(0.3, 0.3,choice);
        YesNoButton yesButton = new YesNoButton(YesNo.YES);
        yesButton.setOnMouseClicked(e->choice.setValue(true));
        StackPane.setAlignment(yesButton, Pos.BOTTOM_RIGHT);
        main.getChildren().addAll(yesButton);
        if(choice.getInfo()== null || choice.getInfo().trim().equals("")){
            choice.setValue(true);
        }

     //   main.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
    }


}
