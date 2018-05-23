package gui;

import gamemechanics.CombatEncounter;
import gamemechanics.Encounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import preparation.Preparation;

class EncounterGui extends DialogGui {
    final Encounter encounter;
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
        bonusPane = new VBox();
        bonusPane.setBorder(new Border(new BorderStroke(Fonts.DARK, BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderStroke.THIN)));
        main.getChildren().clear();
        main.getChildren().add(encounterMain);
        BorderPane.setMargin(encounterPane, new Insets(0, 10, 10, 0));
        BorderPane.setMargin(bonusPane, new Insets(0, 0, 10, 0));
        populate();
    }

    void populate() {
        populateDicePane();
        populateCenterPane();
        populateBonusPane();
    }

    void populateDicePane() {
        dicePane = new DicePane(encounter, background.getWidth() * 0.42, background.getHeight() * 0.25);
        dicePane.getAcceptButton().setOnMouseClicked(this::acceptHandler);
        encounterMain.setBottom(dicePane);

    }

    void populateCenterPane() {
        //TODO füllen der Encounter pane
        encounterPane.getChildren().clear();
        encounterMain.setCenter(encounterPane);
    }

    void populateBonusPane() {
        bonusPane.getChildren().clear();
        bonusPane.getChildren().add(new RoundButton("${dice_Button}"));
        encounterMain.setRight(bonusPane);
    }

    private void acceptHandler(MouseEvent e) {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            encounter.completeEncounterPart();
            populate();
        }
    }
    private void refresh() {

    }

}
