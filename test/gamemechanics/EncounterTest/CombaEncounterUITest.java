package gamemechanics.EncounterTest;

import container.Die;
import container.ItemContainer;
import container.Result;
import enums.EventTimeType;
import expetions.EncounterException;
import factory.InvestigatorFactory;
import factory.ItemFactory;
import factory.MonsterFactory;
import gamemechanics.CombatEncounter;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Investigator;
import model.Item.Asset;
import model.Item.Bonus;
import model.Item.Item;
import model.Monster;
import preparation.Preparation;

import java.util.ArrayList;
import java.util.List;

public class CombaEncounterUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Button rollDice;
    private FlowPane resultDice;
    private Label checkDataLabel;
    private Label succsessInfo;
    private VBox beforeBoni;
    private VBox afterBoni;

    @Override
    public void start(Stage primaryStage) {
        CombatEncounter encounter = initCombatEncounter();

        BorderPane selectScreen = new BorderPane();
        buildMonster(encounter, selectScreen);
        Scene scene = new Scene(selectScreen, 960, 540);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void buildMonster(CombatEncounter encounter, BorderPane pane) {
        pane.setLeft(null);
        pane.setRight(null);
        Label info = new Label();
        info.setWrapText(true);
        info.setMaxWidth(300);
        FlowPane monsterView = new FlowPane(10, 10);
        monsterView.setMaxWidth(400);
        for (Monster monster : encounter.getAvailableMonster()) {
            Button button = new Button(monster.getName());
            button.setWrapText(true);
            button.setMaxWidth(100);
            button.setMinWidth(100);
            button.setMaxHeight(100);
            button.setMinHeight(100);
            button.setOnMouseEntered(event -> info.setText(monster.toString()));
            button.setOnMouseExited(event -> info.setText(""));
            button.setOnAction(event -> {
                encounter.setActiveMonster(monster);
                buildCheck(encounter, encounter.getPreparation(), pane);
            });
            monsterView.getChildren().add(button);
        }
        VBox vbox = new VBox(20, monsterView, info);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(50));
        pane.setCenter(vbox);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));


    }

    private void buildCheck(CombatEncounter encounter, Preparation preparation, BorderPane pane) {
        pane.setRight(null);
        pane.setLeft(null);
        VBox horrorCheckView = new VBox(20);
        horrorCheckView.setAlignment(Pos.TOP_CENTER);
        horrorCheckView.setPadding(new Insets(50));
        pane.setCenter(horrorCheckView);
        beforeBoni = new VBox(50, new Label("Before Test Boni"));
        afterBoni = new VBox(50, new Label("After Test Boni"));
        afterBoni.setDisable(true);
        checkDataLabel = new Label();
        setCheckText(encounter, preparation, checkDataLabel);
        setItemBoni(EventTimeType.BEFORE, beforeBoni, preparation, encounter);
        setItemBoni(EventTimeType.AFTER, afterBoni, preparation, encounter);

        pane.setLeft(beforeBoni);
        pane.setRight(afterBoni);

        rollDice = new Button();
        resultDice = new FlowPane(10, 10);
        resultDice.setMaxWidth(300);
        resultDice.setAlignment(Pos.CENTER);
        Button toAttack = new Button(encounter.getEncounterPart()==2 ?  "To Monster Selection": "To Attack");

        succsessInfo = new Label();
        rollDice.setOnAction(event -> {
            Result result = encounter.check();
            for (Die die : result) {
                DiceButton dieButton = new DiceButton(die, result);
                dieButton.setOnMouseClicked(event1 -> refresh(encounter, preparation));

                resultDice.getChildren().add(dieButton);


            }
            rollDice.setDisable(true);
            afterBoni.setDisable(false);
            beforeBoni.setDisable(true);

            horrorCheckView.getChildren().add(toAttack);
            refresh(encounter, preparation);
        });
        horrorCheckView.getChildren().addAll(checkDataLabel, rollDice, resultDice, succsessInfo);
        refresh(encounter, preparation);
    }

    private void setItemBoni(EventTimeType timeType, VBox boni, Preparation preparation, CombatEncounter encounter) {
        Node node = boni.getChildren().get(0);
        boni.getChildren().clear();
        boni.getChildren().add(node);
        for (Bonus bonus : preparation.getBoni(timeType)) {
            Button button = new Button(bonus.getParentName() + ": " + bonus.getText());
            button.setWrapText(true);
            button.setMaxWidth(150);
            button.setOnAction(event -> {
                try {
                    bonus.execute(encounter);
                } catch (EncounterException e) {
                    button.setText(e.getMessage());
                }

                button.setDisable(true);
                refresh(encounter, preparation);
            });
            boni.getChildren().add(button);
        }
    }

    private void refresh(CombatEncounter encounter, Preparation preparation) {
        String text = "";

        rollDice.setText("Roll " + preparation.getNumberOfDice() + " Dice." + text);
        resultDice.getChildren().forEach(item -> ((DiceButton) item).refresh());
        if (encounter.getResult() != null) {
            String damage = encounter.getEncounterPart()==2 ? ": Loose " + (encounter.getActiveMonster().getDamage() - encounter.getResult().getNumberOfSuccess()) + " health" :
                    ": Loose " + (encounter.getActiveMonster().getHorror() - encounter.getResult().getNumberOfSuccess()) + " sanity";
            succsessInfo.setText(encounter.getResult().isSuccess() ? "Success" : "Fail" + damage);
        }
        setCheckText(encounter, preparation, checkDataLabel);
    }

    private void setCheckText(CombatEncounter encounter, Preparation preparation, Label label) {
        String bonusName = "";
        if (encounter.getEncounterPart()==2) {
            Item parent = encounter.getAttackPreparation().getGainDiceBonus().getParentItem();
            if (parent != null) {
                bonusName = " (" + parent.getName() + ")";
            }
            label.setText(preparation.getTestTyp().getText() + ": " + preparation.getInvestigator().getSkill(preparation.getTestTyp()) + "\nBonus: " + encounter.getAttackPreparation().getGainDiceBonus().getValue() + bonusName + "\nAdditional Dice: " + encounter.getAttackPreparation().getAdditionDiceBoniSum() + "\nMalus: " + encounter.getActiveMonster().getStrengthTest() + "\nDamage: " + encounter.getActiveMonster().getDamage() + "\nToughness: " + (encounter.getMonsterLive()));
        } else {
            Item parent = encounter.getHorrorPreparation().getGainDiceBonus().getParentItem();
            if (parent != null) {
                bonusName = " (" + parent.getName() + ")";
            }
            label.setText(preparation.getTestTyp().getText() + ": " + preparation.getInvestigator().getSkill(preparation.getTestTyp()) + "\nBonus: " + encounter.getHorrorPreparation().getGainDiceBonus().getValue() + bonusName + "\nAdditional Dice: " + encounter.getHorrorPreparation().getAdditionDiceBoniSum() + "\nMalus: " + encounter.getActiveMonster().getWillTest() + "\nHorror: " + encounter.getActiveMonster().getHorror());
        }
    }


    private CombatEncounter initCombatEncounter() {
        Investigator inv = new InvestigatorFactory().getInvestigators().get(0).getInstance();
        ItemContainer<Asset> assets = new ItemFactory().getAssets();
        inv.getInventory().remove(assets.get("&profaneTome"));
        inv.getInventory().addAll(assets);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new MonsterFactory().getMonster().get(0).getInstance());
        monsters.add(new MonsterFactory().getMonster().get(1).getInstance());
        monsters.add(new MonsterFactory().getMonster().get(2).getInstance());
        return new CombatEncounter(monsters, inv);
    }


    class DiceButton extends HBox {
        Label value = new Label();

        Button reroll = new Button("@");
        Button shift = new Button("^");
        Die die;
        Result result;

        DiceButton(Die die, Result result) {
            this.die = die;
            this.result = result;
            value.setPrefWidth(50);
            value.setPrefHeight(50);
            value.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            value.setFont(Font.font(28.0));
            value.setAlignment(Pos.CENTER);
            this.getChildren().add(value);
            this.getChildren().add(new VBox(reroll, shift));
            reroll.setOnMouseClicked(event -> {
                result.rerollDie(die);
                Event.fireEvent(this, event);
            });
            shift.setOnMouseClicked(event -> {
                result.shiftDie(die);
                Event.fireEvent(this, event);
            });

            refresh();
        }

        void refresh() {
            value.setText(die.getValue() + "");
            reroll.setVisible(result.getReroll() > 0);
            shift.setVisible(die.isShiftable());
        }
    }


}
