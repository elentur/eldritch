package gamemechanics.EncounterTest;

import Service.GameService;
import container.Die;
import container.Result;
import enums.EventTimeType;
import factory.InvestigatorFactory;
import factory.MonsterFactory;
import gamemechanics.CombatEncounter;
import gamemechanics.Encounter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Investigator;
import model.Item.Bonus;
import model.Monster;
import preparation.CombatPreparation;
import preparation.HorrorPreparation;
import preparation.Preparation;

import java.util.ArrayList;
import java.util.List;

public class CombaEncounterUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Button rollDice;
    FlowPane resultDice;
    Label checkDataLabel;

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
                buildCheck(encounter, encounter.prepareForHorrorCheck(), pane);
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
        VBox horrorCheckView = new VBox(20);
        horrorCheckView.setAlignment(Pos.TOP_CENTER);
        horrorCheckView.setPadding(new Insets(50));
        pane.setCenter(horrorCheckView);
        VBox beforeBoni = new VBox(50, new Label("Before Test Boni"));
        VBox afterBoni = new VBox(50, new Label("After Test Boni"));
        checkDataLabel = new Label();
        setCheckText(encounter, preparation, checkDataLabel);
        setItemBoni(EventTimeType.BEFORE, beforeBoni, preparation, encounter);
        setItemBoni(EventTimeType.AFTER, afterBoni, preparation, encounter);
        pane.setLeft(beforeBoni);

        rollDice = new Button();
        resultDice = new FlowPane(10, 10);
        resultDice.setMaxWidth(300);
        resultDice.setAlignment(Pos.CENTER);
        Button toAttack = new Button(preparation instanceof HorrorPreparation ? "To Attack" : "To Monster Selection");
        if (preparation instanceof HorrorPreparation) {
            toAttack.setOnAction(event -> buildCheck(encounter, encounter.prepareForCombat(), pane));
        } else {
            toAttack.setOnAction(event -> {
                if (encounter.getAvailableMonster().isEmpty()) {
                    System.exit(0);
                } else {
                    encounter.removeActiveMonster();
                    buildMonster(encounter, pane);
                }
            });
        }
        Label succsessInfo = new Label();
        rollDice.setOnAction(event -> {
            Result result = encounter.check(preparation);
            for (Die die : result) {
                Button dieButton = new Button(die.getValue() + "");
                resultDice.getChildren().add(dieButton);
                dieButton.setDisable(true);
            }
            rollDice.setDisable(true);
            succsessInfo.setText(result.isSuccess() ? "Success" : "Fail");
            pane.setLeft(null);
            pane.setRight(afterBoni);
            horrorCheckView.getChildren().add(toAttack);
        });
        horrorCheckView.getChildren().addAll(checkDataLabel, rollDice, resultDice, succsessInfo);
        refresh(encounter, preparation);
    }

    private void setItemBoni(EventTimeType timeType, VBox boni, Preparation preparation, CombatEncounter encounter) {
        for (Bonus bonus : preparation.getBoni(EventTimeType.BEFORE)) {
            Button button = new Button(bonus.getText());
            button.setWrapText(true);
            button.setMaxWidth(150);
            button.setOnAction(event -> {
                bonus.execute(encounter);
                button.setDisable(true);
                setCheckText(encounter, preparation, checkDataLabel);
                refresh(encounter, preparation);
            });
            boni.getChildren().add(button);
        }
    }

    private void refresh(CombatEncounter encounter, Preparation preparation) {
        rollDice.setText("Roll " + preparation.getModifiedSkill() + " Dice");
        for(int i =0; encounter.getResult().)
    }

    private void setCheckText(CombatEncounter encounter, Preparation preparation, Label label) {
        if (preparation instanceof HorrorPreparation) {
            label.setText(preparation.getTestTyp().getText() + ": " + preparation.getInvestigator().getSkill(preparation.getTestTyp()) + "\n Montser: Horror: " + encounter.getActiveMonster().getHorror());
        } else {
            label.setText(preparation.getTestTyp().getText() + ": " + preparation.getInvestigator().getSkill(preparation.getTestTyp()) + "\n Montser: Damage: " + encounter.getActiveMonster().getDamage() + " Toughness: " + encounter.getActiveMonster().getToughness());
        }
    }


    private CombatEncounter initCombatEncounter() {
        Investigator inv = new InvestigatorFactory().getInvestigators().get(0).getInstance();
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new MonsterFactory().getMonster().get(0).getInstance());
        monsters.add(new MonsterFactory().getMonster().get(1).getInstance());
        monsters.add(new MonsterFactory().getMonster().get(2).getInstance());
        return new CombatEncounter(monsters, inv);
    }
}
