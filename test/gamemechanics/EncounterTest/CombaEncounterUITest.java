package gamemechanics.EncounterTest;

import Service.GameService;
import container.Die;
import container.Result;
import enums.EventTimeType;
import factory.InvestigatorFactory;
import factory.MonsterFactory;
import gamemechanics.CombatEncounter;
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

import java.util.ArrayList;
import java.util.List;

public class CombaEncounterUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CombatEncounter encounter = initCombatEncounter();

        BorderPane selectScreen = new BorderPane();
        buildMonster(encounter,selectScreen);
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
        FlowPane monsterView = new FlowPane(10,10);
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
                buildHorrorCheck(encounter, pane);
            });
            monsterView.getChildren().add(button);
        }
        VBox vbox = new VBox(20, monsterView, info);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(50));
        pane.setCenter(vbox);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,BorderWidths.DEFAULT)));
    }

    private void buildHorrorCheck(CombatEncounter encounter, BorderPane pane) {
        VBox horrorCheckView = new VBox(20);
        horrorCheckView.setAlignment(Pos.TOP_CENTER);
        horrorCheckView.setPadding(new Insets(50));
        pane.setCenter(horrorCheckView);
        VBox beforeBoni = new VBox(50,new Label("Before Test Boni"));
        VBox afterBoni = new VBox(50,new Label("After Test Boni"));
        HorrorPreparation preparation = encounter.prepareForHorrorCheck();
        Label checkDataLabel = new Label("Horror: " + encounter.getActiveMonster().getHorror()+" Dice: " + preparation.getModifiedSkill());
        for(Bonus bonus :preparation.getBoni(EventTimeType.BEFORE)){
            Button button = new Button(bonus.getText());
            button.setWrapText(true);
            button.setMaxWidth(150);
            button.setOnAction(event -> {
                bonus.execute(preparation);
                button.setDisable(true);
                checkDataLabel.setText("Horror: " + encounter.getActiveMonster().getHorror()+" Dice: " + preparation.getModifiedSkill());
            });
            beforeBoni.getChildren().add(button);
        }
        pane.setLeft(beforeBoni);
        for(Bonus bonus :preparation.getBoni(EventTimeType.AFTER)){
            Button button = new Button(bonus.getText());
            button.setWrapText(true);
            button.setMaxWidth(150);
            button.setOnAction(event -> {
                bonus.execute(preparation);
                button.setDisable(true);
                checkDataLabel.setText("Horror: " + encounter.getActiveMonster().getHorror()+" Dice: " + preparation.getModifiedSkill());
            });
            afterBoni.getChildren().add(button);
        }


        Button rollDice = new Button("Roll dice");
        FlowPane resultDice = new FlowPane(10,10);
        resultDice.setMaxWidth(300);
        resultDice.setAlignment(Pos.CENTER);
        Button toAttack = new Button("to Attack");
        toAttack.setOnAction(event-> buildAttackCheck(encounter, pane));
Label succsessInfo = new Label();
        rollDice.setOnAction(event->{
           Result result = encounter.horrorCheck(preparation);
           for (Die die : result){
               Button dieButton = new Button(die.getValue()+"");
               resultDice.getChildren().add(dieButton);
               rollDice.setDisable(true);
           }
           succsessInfo.setText(result.isSuccess()?"Success":"Fail");
           pane.setLeft(null);
           pane.setRight(afterBoni);
            horrorCheckView.getChildren().add(toAttack);
        });
        horrorCheckView.getChildren().addAll(checkDataLabel,rollDice,resultDice,succsessInfo);

    }

    private void buildAttackCheck(CombatEncounter encounter, BorderPane pane) {
        VBox attackCheckView = new VBox(20);
        attackCheckView.setAlignment(Pos.TOP_CENTER);
        attackCheckView.setPadding(new Insets(50));
        pane.setCenter(attackCheckView);
        pane.setLeft(null);
        pane.setRight(null);

        VBox beforeBoni = new VBox(50,new Label("Before Test Boni"));
        VBox afterBoni = new VBox(50,new Label("After Test Boni"));
        CombatPreparation preparation = encounter.prepareForCombat();
        Label checkDataLabel = new Label("Damage: " + encounter.getActiveMonster().getDamage()+" Dice: " + preparation.getModifiedSkill()+"\nToughness: " +encounter.getActiveMonster().getToughness());

        for(Bonus bonus :preparation.getBoni(EventTimeType.BEFORE)){
            Button button = new Button(bonus.getText());
            button.setWrapText(true);
            button.setMaxWidth(150);
            button.setOnAction(event -> {
                bonus.execute(preparation);
                button.setDisable(true);
                checkDataLabel.setText("Damage: " + encounter.getActiveMonster().getDamage()+" Dice: " + preparation.getModifiedSkill()+"\nToughness: " +encounter.getActiveMonster().getToughness());
            });
            beforeBoni.getChildren().add(button);
        }
        pane.setLeft(beforeBoni);
        for(Bonus bonus :preparation.getBoni(EventTimeType.AFTER)){
            Button button = new Button(bonus.getText());
            button.setWrapText(true);
            button.setMaxWidth(150);
            button.setOnAction(event -> {
                bonus.execute(preparation);
                button.setDisable(true);
                checkDataLabel.setText("Damage: " + encounter.getActiveMonster().getHorror()+" Dice: " + preparation.getModifiedSkill()+"\nToughness: " +encounter.getActiveMonster().getToughness());
            });
            afterBoni.getChildren().add(button);
        }

         Button rollDice = new Button("Roll dice");
        FlowPane resultDice = new FlowPane(10,10);
        resultDice.setMaxWidth(300);
        resultDice.setAlignment(Pos.CENTER);
        Button toSelect = new Button(encounter.getAvailableMonster().isEmpty()?"Exit":"to Select");
        toSelect.setOnAction(event-> {

            encounter.removeActiveMonster();
            if(encounter.getAvailableMonster().isEmpty()) {
               System.exit(0);
            }else{
                buildMonster(encounter, pane);
            }
        });
        Label succsessInfo = new Label();
        rollDice.setOnAction(event->{
            Result result = encounter.attackMonster(preparation);
            for (Die die : result){
                Button dieButton = new Button(die.getValue()+"");
                resultDice.getChildren().add(dieButton);
                rollDice.setDisable(true);
            }
            succsessInfo.setText(result.isSuccess()?"Success":"Fail");
            pane.setLeft(null);
            pane.setRight(afterBoni);
            attackCheckView.getChildren().add(toSelect);
        });
        attackCheckView.getChildren().addAll(checkDataLabel,rollDice,resultDice,succsessInfo);

    }




    private CombatEncounter initCombatEncounter() {
        Investigator inv = new InvestigatorFactory().getInvestigators().get(0).getInstance();
        GameService game = GameService.getInstance();
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new MonsterFactory().getMonster().get(0).getInstance());
        monsters.add(new MonsterFactory().getMonster().get(1).getInstance());
        monsters.add(new MonsterFactory().getMonster().get(2).getInstance());
        return new CombatEncounter(monsters, inv);
    }
}
