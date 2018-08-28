package gui.encounters;

import Service.GameService;
import enums.EventTimeType;
import enums.TestType;
import gamemechanics.Action;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.StandardEncounter;
import gui.*;
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
        encounterPane.setAlignment(Pos.TOP_CENTER);
        encounterPane.setPadding(new Insets(10));
        StackPane.setAlignment(encounterPane, Pos.TOP_LEFT);

        bonusPane = new ItemScrollPane();
        bonusPane.setWidth1(background.getWidth() * 0.25);
        bonusPane.setHeight1(background.getHeight() * 0.40);
        StackPane.setAlignment(bonusPane, Pos.TOP_RIGHT);
        encounterMain.getChildren().addAll(encounterPane, bonusPane);

        populate();
    }

    void populate() {
        populateDicePane();
        populateCenterPane();
        if (!encounter.getTestType()[encounter.getEncounterPart()].equals(TestType.NONE)) {
            populateBonusPane();
        }
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


        // encounterPane.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));

        encounterPane.getChildren().clear();

        switch (encounter.getEncounterType()) {
            case COMBAT_ENCOUNTER:
                break;
            case ACTION:
                populateCenterForAction((Action) encounter);
                break;
            default:
                populateCenterForStandardEncounter((StandardEncounter) encounter);
                break;
        }

    }

    private void populateCenterForAction(Action encounter) {
        TextField startText = new TextField(encounter.getEncounterStartText() + "\n" + encounter.getEncounterEffectText());
        startText.setWrapText(true);
        startText.styleProperty().bind(Fonts.getFont(0.2, Fonts.DARK, Fonts.FontTyp.NORMAL));
        startText.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));

        encounterPane.getChildren().addAll(startText);
    }

    private void populateCenterForStandardEncounter(StandardEncounter encounter) {
        TextField startText = new TextField(encounter.getEncounterStartText() + "\n" + encounter.getEncounterEffectText());
        startText.setWrapText(true);
        startText.styleProperty().bind(Fonts.getFont(0.2, Fonts.DARK, Fonts.FontTyp.NORMAL));
        startText.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));

        encounterPane.getChildren().addAll(startText);
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

    protected void acceptHandler(MouseEvent e) {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            if (encounter.completeEncounterPart() == 3) {
                this.close();
                GameService.getInstance().setActiveInvestigator();
            } else {
                populate();
            }
        }
    }


}
