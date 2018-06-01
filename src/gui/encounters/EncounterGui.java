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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Item.Bonus;

public class EncounterGui extends DialogGui {
    final Encounter encounter;
    private ItemScrollPane bonusPane;
    VBox encounterPane;
    DicePane dicePane;
    private final static Image frameImage = new Image("images/ShowCaseFrame.png");
    final BorderPane encounterMain;

  public  EncounterGui(Encounter encounter) {
        super("", 0.7, 0.7);
        this.encounter = encounter;

        encounterMain = new BorderPane();
        encounterPane = new VBox(20);
        encounterPane.setAlignment(Pos.CENTER);
        encounterPane.setPrefWidth(background.getHeight() * 0.50);

        bonusPane = new ItemScrollPane();
        bonusPane.setWidth(background.getWidth() * 0.20);
        bonusPane.setHeight(background.getHeight() * 0.50);


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
        dicePane.getRolleIsDoneProperty().addListener(e -> populateBoni(EventTimeType.AFTER));
        dicePane.getAcceptButton().setOnMouseClicked(this::acceptHandler);
        encounterMain.setBottom(dicePane);

    }

    void populateCenterPane() {
        //TODO füllen der Encounter pane
        encounterPane.getChildren().clear();
        encounterMain.setCenter(encounterPane);
    }

    void populateBonusPane() {

        populateBoni(EventTimeType.BEFORE);
        encounterMain.setRight(bonusPane);
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
