package gui;

import gamemechanics.Encounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Effect;

class EncounterGui extends DialogGui {
    private final Encounter encounter;
    private VBox bonusPane;
    VBox encounterPane;
    private DicePane dicePane;
    private final static Image frameImage = new Image("images/ShowCaseFrame.png");
    final BorderPane encounterMain;

    EncounterGui(Encounter encounter) {
        super("", 0.7, 0.7);
        this.encounter = encounter;

        encounterMain = new BorderPane();

        encounterPane = new VBox(20);
        encounterPane.setAlignment(Pos.CENTER);
      //  encounterPane.setBorder(new Border(new BorderStroke(Fonts.DARK, BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderStroke.THIN)));
        bonusPane = new VBox();
        bonusPane.setBorder(new Border(new BorderStroke(Fonts.DARK, BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderStroke.THIN)));
        main.getChildren().clear();
        main.getChildren().add(encounterMain);
      //  encounterMain.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        BorderPane.setMargin(encounterPane, new Insets(0, 10, 10, 0));
        BorderPane.setMargin(bonusPane, new Insets(0, 0, 10, 0));
       //bonusPane.setBackground((new Background(new BackgroundFill(Color.rgb(0,0,0,0.2),CornerRadii.EMPTY,Insets.EMPTY))));
       // encounterMain.setEffect(Effects.innerShadow);//?
       // bonusPane.getStyleClass().add("show-case");
       // encounterPane.getStyleClass().add("show-case");
    }

    void populate() {
        populateDicePane();
        populateCenterPane();
        populateBonusPane();
    }

    void populateDicePane() {
        dicePane = new DicePane(encounter, background.getWidth() * 0.42, background.getHeight() * 0.25);


        encounterMain.setBottom(dicePane);
    }

    void populateCenterPane() {
        //TODO füllen der Encounter pane
        encounterMain.setCenter(encounterPane);
    }

    void populateBonusPane() {
        bonusPane.getChildren().add(new RollDiceButton());
        encounterMain.setRight(bonusPane);
    }

}
