package gamemechanics.EncounterTest;

import Service.GameService;
import factory.InvestigatorFactory;
import factory.MonsterFactory;
import gamemechanics.CombatEncounter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Investigator;
import model.Item.Item;
import model.Monster;
import preparation.CombatPreparation;

import java.util.ArrayList;
import java.util.List;

public class CombaEncounterUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CombatEncounter encounter = intiCombadEncounter();

        HBox selectScreen = new HBox(100);
        Scene scene = new Scene(selectScreen,960,540);
        VBox monsters = new VBox(20);
        VBox bonusItems = new VBox(20);
        selectScreen.getChildren().addAll(monsters,bonusItems);
        addMonsters(monsters,encounter);
        CombatPreparation preparation = encounter.prepareForCombat();
        addItems(bonusItems,preparation);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void addItems(VBox bonusItems, CombatPreparation preparation) {

        for(Item item : preparation.getBonusItems()) {
            Button button = new Button(item.getName());
            button.setOnAction(event->{


            });
            bonusItems.getChildren().add(button);
        }

    }

    private void addMonsters(VBox monsters,CombatEncounter encounter) {
        VBox values = new VBox(10);
        Label  willTest=new Label();
        Label horror=new Label();
        Label strengthTest=new Label();
        Label damage=new Label();
        Label toughness=new Label();
        values.getChildren().addAll(willTest,horror,strengthTest,damage,toughness);
        for(Monster monster : encounter.getAvailableMonster()) {
            Button button = new Button(monster.getName());
            button.setOnAction(event->{
                encounter.setActiveMonster(monster);
                willTest.setText("will test: " + monster.getWillTest());
                horror.setText("horror: " + monster.getHorror());
                strengthTest.setText("strength test: " + monster.getStrengthTest());
                damage.setText("damage: " + monster.getDamage());
                toughness.setText("toughness: " + monster.getToughness());

            });
            monsters.getChildren().add(button);
        }
        monsters.getChildren().add(values);
    }

    private CombatEncounter intiCombadEncounter() {
        Investigator inv = new InvestigatorFactory().getInvestigators().get(0).getInstance();
        GameService game = GameService.getInstance();
        List<Monster> monsters = new ArrayList<>();
        monsters.add( new MonsterFactory().getMonster().get(0).getInstance());
        monsters.add( new MonsterFactory().getMonster().get(1).getInstance());
        monsters.add( new MonsterFactory().getMonster().get(2).getInstance());
       return new CombatEncounter(monsters, inv);
    }
}
