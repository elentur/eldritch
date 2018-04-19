package gamemechanics.EncounterTest;

import Service.GameService;
import enums.EventTimeType;
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
import model.Item.Bonus;
import model.Item.ItemBonus;
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
        CombatEncounter encounter = initCombatEncounter();

        HBox selectScreen = new HBox(100);
        Scene scene = new Scene(selectScreen, 960, 540);
        VBox monsters = new VBox(20);
        VBox bonusItems = new VBox(20);
        VBox attack = new VBox(20);
        Button attackButton = new Button("attack");
        attackButton.setDisable(true);
        attack.getChildren().add(attackButton);
        Label preparationInfo = new Label();
        preparationInfo.setWrapText(true);
        attack.getChildren().add(preparationInfo);
        selectScreen.getChildren().add(monsters);

        addMonsters(monsters, encounter, attackButton);
        CombatPreparation preparation = encounter.prepareForCombat();
        addItems(bonusItems, preparation,preparationInfo, encounter.getInvestigator());
        HBox horrorCheckScreen = new HBox(100);

        HBox attackScreen = new HBox(100);

        Label encounterInfo = new Label();
        encounterInfo.setMaxWidth(300);
        encounterInfo.setWrapText(true);



        attackScreen.getChildren().addAll(encounterInfo);

        attackButton.setOnAction(event->{
            scene.setRoot(attackScreen);
            encounterInfo.setText( encounter.toString());
        });
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void addItems(VBox bonusItems, CombatPreparation preparation,Label info, Investigator inv ) {

        for (Bonus bonus : preparation.getBoni(EventTimeType.BEFORE)) {
            if (bonus instanceof ItemBonus) {
                Button button = new Button(((ItemBonus) bonus).getParentItem().getName() + ":\n" + bonus.getText());
                button.setOnAction(event -> {
                    button.setDisable(true);
                    bonus.execute(preparation);
                    info.setText("Test: " + preparation.getTestTyp().getText()+"\nModification: " + preparation.getModification()+"\n" + inv.getSkillSet());

                });
                button.setMaxWidth(300);
                button.setWrapText(true);
                bonusItems.getChildren().add(button);
            }
        }

    }

    private void addMonsters(VBox monsters, CombatEncounter encounter, VBox screen, HBox parent) {
        VBox values = new VBox(10);
        Label willTest = new Label();
        Label horror = new Label();
        Label strengthTest = new Label();
        Label damage = new Label();
        Label toughness = new Label();
        values.getChildren().addAll(willTest, horror, strengthTest, damage, toughness);
        for (Monster monster : encounter.getAvailableMonster()) {
            Button button = new Button(monster.getName());
            button.setOnMouseEntered(event -> {

                willTest.setText("will test: " + monster.getWillTest());
                horror.setText("horror: " + monster.getHorror());
                strengthTest.setText("strength test: " + monster.getStrengthTest());
                damage.setText("damage: " + monster.getDamage());
                toughness.setText("toughness: " + monster.getToughness());


            });
            button.setOnMouseExited(event -> {

                willTest.setText("will test: " );
                horror.setText("horror: " );
                strengthTest.setText("strength test: " );
                damage.setText("damage: " );
                toughness.setText("toughness: " );

            });
            button.setOnAction(event->{
                encounter.setActiveMonster(monster);
                parent.getChildren().clear();
                parent.getChildren().add();
            });
            monsters.getChildren().add(button);
        }
        monsters.getChildren().add(values);
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
