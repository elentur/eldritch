package gui.encounters;

import Service.GameService;
import enums.TestType;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.encounter.CombatEncounter;
import gui.Effects;
import gui.Fonts;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Item.Monster;
import utils.ResourceUtil;

import java.util.List;

public class CombatEncounterGui extends EncounterGui {

    private final static Image monsterShapeImage = new Image("images/ItemBack.png");
    private final static Image sanityImage = new Image("images/sanity.png");
    private final static Image healthImage = new Image("images/health.png");
    private final static Image toughnessImage = new Image("images/toughness.png");

    public CombatEncounterGui(CombatEncounter encounter) {
        super(encounter);

    }

    @Override
    void populate() {
        super.populate();

    }



    void populateCenterPane() {
        CombatEncounter encounter = (CombatEncounter)this.encounter;
        super.populateCenterPane();
        Monster m = encounter.getActiveMonster();
        Rectangle shape = new Rectangle(200, 80, new ImagePattern(monsterShapeImage));
        Rectangle monsterImage = new Rectangle(200, 150,
                new ImagePattern(new Image("images/monster/" + m.getId() + ".png")));
        monsterImage.setClip(shape);
        monsterImage.setEffect(Effects.innerShadow);

        Rectangle toughness = new Rectangle(80, 80, new ImagePattern(toughnessImage));
        Label toughnessValue = new Label(m.getActualToughness() + "");
        toughnessValue.getStyleClass().add("text-stroke-black");
        toughnessValue.setTranslateY(-10);
        toughnessValue.styleProperty().bind(Fonts.getFont(0.6, Fonts.GREEN, Fonts.FontTyp.BOLD));
        StackPane toughnessPane = new StackPane(toughness, toughnessValue);
     //   toughnessPane.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));

        HBox header = new HBox(monsterImage, toughnessPane);
        header.setAlignment(Pos.TOP_CENTER);
        header.setSpacing(20);
        header.setMaxHeight(shape.getHeight());
        header.setMinHeight(shape.getHeight());


        HBox leftSide = new HBox();
        leftSide.setAlignment(Pos.CENTER);
        leftSide.setPadding(new Insets(10));
        if (encounter.getEncounterPart() == 2) {
            leftSide.setBackground(new Background(new BackgroundFill(Color.rgb(205, 40, 43, 0.2), new CornerRadii(10.0), Insets.EMPTY)));

            Label strengthTestSymbol = new Label(TestType.STRENGTH.getSymbol());
            strengthTestSymbol.styleProperty().bind(Fonts.getFont(0.4, Fonts.RED, Fonts.FontTyp.NORMAL));
            strengthTestSymbol.setPadding(new Insets(0, 10, 0, 10));
            Label strengthTest = new Label(m.getStrengthTest() + "");
            strengthTest.styleProperty().bind(Fonts.getFont(0.4, Fonts.RED, Fonts.FontTyp.NORMAL));
            strengthTest.setPadding(new Insets(0, 10, 0, 10));
            Label attackValue = new Label(m.getDamage() + "");
            attackValue.setPadding(new Insets(0, 10, 0, 10));
            attackValue.styleProperty().bind(Fonts.getFont(0.4, Fonts.RED, Fonts.FontTyp.NORMAL));
            Rectangle health = new Rectangle(50, 50, new ImagePattern(healthImage));
            VBox symbols = new VBox(20, strengthTestSymbol, health);
            VBox values = new VBox(20, strengthTest, attackValue);
            values.setAlignment(Pos.CENTER_RIGHT);
            leftSide.getChildren().addAll(symbols, values);
        } else {
            leftSide.setBackground(new Background(new BackgroundFill(Color.rgb(88, 120, 200, 0.2), new CornerRadii(10.0), Insets.EMPTY)));

            Label willTestSymbol = new Label(TestType.WILL.getSymbol());
            willTestSymbol.styleProperty().bind(Fonts.getFont(0.4, Fonts.BLUE, Fonts.FontTyp.NORMAL));
            willTestSymbol.setPadding(new Insets(0, 10, 0, 10));
            Label willTest = new Label(m.getWillTest() + "");
            willTest.styleProperty().bind(Fonts.getFont(0.4, Fonts.BLUE, Fonts.FontTyp.NORMAL));
            willTest.setPadding(new Insets(0, 10, 0, 10));
            Label horrorValue = new Label(m.getHorror() + "");
            horrorValue.setPadding(new Insets(0, 10, 0, 10));
            horrorValue.styleProperty().bind(Fonts.getFont(0.4, Fonts.BLUE, Fonts.FontTyp.NORMAL));
            Rectangle sanity = new Rectangle(50, 50, new ImagePattern(sanityImage));
            VBox symbols = new VBox(20, willTestSymbol, sanity);
            VBox values = new VBox(20, willTest, horrorValue);
            values.setAlignment(Pos.CENTER_RIGHT);
            leftSide.getChildren().addAll(symbols, values);
        }
        HBox rightSide = new HBox();
        rightSide.setBackground(new Background(new BackgroundFill(Color.rgb(0, 170, 80, 0.2), new CornerRadii(10.0), Insets.EMPTY)));
        rightSide.setPadding(new Insets(10));

            Label testType = new Label(encounter.getPreparation().getTestTyp().getText() + ":");
            testType.styleProperty().bind(Fonts.getFont(0.25, Fonts.GREEN, Fonts.FontTyp.NORMAL));
            testType.setPadding(new Insets(0, 10, 0, 10));
            Label testValue = new Label(encounter.getPreparation().getInvestigator().getSkill(encounter.getPreparation().getTestTyp()) + "");
            testValue.styleProperty().bind(Fonts.getFont(0.25, Fonts.GREEN, Fonts.FontTyp.NORMAL));
            testValue.setPadding(new Insets(0, 10, 0, 10));

            Label bonusItem = new Label(ResourceUtil.get("${item_bonus}", "ui") + ":");
            bonusItem.styleProperty().bind(Fonts.getFont(0.25, Fonts.GREEN, Fonts.FontTyp.NORMAL));
            bonusItem.setPadding(new Insets(0, 10, 0, 10));
            Label bonusItemValue = new Label(encounter.getPreparation().getGainDiceBonus().getValue() +
                    (encounter.getPreparation().getGainDiceBonus().getValue()>0?"   (" + encounter.getPreparation().getGainDiceBonus().getParentName() + ")":""));
            bonusItemValue.styleProperty().bind(Fonts.getFont(0.25, Fonts.GREEN, Fonts.FontTyp.NORMAL));
            bonusItemValue.setPadding(new Insets(0, 10, 0, 10));

            Label additionalDice = new Label(ResourceUtil.get("${additional_dice}", "ui") + ":");
            additionalDice.styleProperty().bind(Fonts.getFont(0.25, Fonts.GREEN, Fonts.FontTyp.NORMAL));
            additionalDice.setPadding(new Insets(0, 10, 0, 10));
            Label additionalDiceValue = new Label(encounter.getPreparation().getAdditionDiceBoniSum() + "");
            additionalDiceValue.styleProperty().bind(Fonts.getFont(0.25, Fonts.GREEN, Fonts.FontTyp.NORMAL));
            additionalDiceValue.setPadding(new Insets(0, 10, 0, 10));

            VBox names = new VBox(20, testType, bonusItem, additionalDice);
            VBox valuesRight = new VBox(20, testValue, bonusItemValue, additionalDiceValue);
            rightSide.getChildren().addAll(names, valuesRight);



        HBox values = new HBox(20, leftSide, rightSide);
        values.setAlignment(Pos.TOP_CENTER);


      //  header.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        encounterPane.getChildren().addAll(header, values);
      //  encounterPane.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));

    }
    @Override
    protected void acceptHandler(MouseEvent e) {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            if (encounter.completeEncounterPart() == 3) {
                this.close();
                List<Monster> monsters = ((CombatEncounter)encounter).getMonsters();
                if(!monsters.isEmpty()){
                    GameService.getInstance().addChoice(new MonsterChoice(monsters));
                }
            } else {
                populate();
            }
        }
    }


}
