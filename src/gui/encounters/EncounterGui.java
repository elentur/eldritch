package gui.encounters;

import enums.EventTimeType;
import gamemechanics.Encounter;
import gui.DialogGui;
import gui.DicePane;
import gui.ItemScrollPane;
import gui.buttons.BonusButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Item.Bonus;

public class EncounterGui extends DialogGui {
    final Encounter encounter;
    private ItemScrollPane bonusPane;
    VBox encounterPane;
    DicePane dicePane;
    StackPane encounterMain;
    private final static Image frameImage = new Image("images/ShowCaseFrame.png");


    public EncounterGui(Encounter encounter) {
        super("", 0.7, 0.7);
        this.encounter = encounter;
        encounterMain = new StackPane();
        main.getChildren().clear();
        main.getChildren().add(encounterMain);
        encounterPane = new VBox(20);
        encounterPane.setMaxWidth(background.getWidth() * 0.50);
        encounterPane.setMaxHeight(background.getHeight() * 0.40);
        StackPane.setAlignment(encounterPane, Pos.TOP_LEFT);

        bonusPane = new ItemScrollPane();
        bonusPane.setWidth1(background.getWidth() * 0.25);
        bonusPane.setHeight1(background.getHeight() * 0.40);
        StackPane.setAlignment(bonusPane, Pos.TOP_RIGHT);
encounterMain.getChildren().addAll(encounterPane,bonusPane);

        populate();
    }

    void populate() {
        populateDicePane();
        populateCenterPane();
        populateBonusPane();
    }

    void populateDicePane() {
        if (dicePane != null) {
            encounterMain.getChildren().remove(dicePane);
        }
        dicePane = new DicePane(encounter, background.getWidth() * 0.75, background.getHeight() * 0.25);
        dicePane.getRolleIsDoneProperty().addListener(e -> populateBoni(EventTimeType.AFTER));
        dicePane.getAcceptButton().setOnMouseClicked(this::acceptHandler);
        StackPane.setAlignment(dicePane, Pos.BOTTOM_LEFT);
        encounterMain.getChildren().add(dicePane);


    }

    void populateCenterPane() {


        //encounterPane.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));

        //TODO füllen der Encounter pane

        encounterPane.getChildren().clear();

    }

    void populateBonusPane() {



        populateBoni(EventTimeType.BEFORE);

    }

    private void populateBoni(EventTimeType timeType) {
        bonusPane.getScrollableChildren().clear();
        for (Bonus bonus : encounter.getPreparation().getBoni(timeType)) {
            BonusButton button = new BonusButton(bonus);

            button.setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    bonus.execute(encounter);
                    if (timeType.equals(EventTimeType.BEFORE)) {
                        populate();
                    } else {
                        dicePane.refresh();
                    }
                    button.setEnabled(false);
                }

            });
            bonusPane.getScrollableChildren().add(button);
        }
    }

    private void acceptHandler(MouseEvent e) {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            encounter.completeEncounterPart();
            populate();
        }
    }


}
