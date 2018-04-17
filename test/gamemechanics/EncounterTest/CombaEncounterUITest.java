package gamemechanics.EncounterTest;

import Service.GameService;
import factory.InvestigatorFactory;
import factory.MonsterFactory;
import gamemechanics.CombatEncounter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Investigator;
import model.Monster;
import oldVersion.gameBuild.Global;

import java.util.ArrayList;
import java.util.List;

public class CombaEncounterUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CombatEncounter encounter = intiCombadEncounter();

        GridPane root = new GridPane();
        Scene scene = new Scene(root,960,540);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private CombatEncounter intiCombadEncounter() {
        Investigator inv = new InvestigatorFactory().getInvestigators().get(0).getInstance();
        Monster monster = new MonsterFactory().getMonster().get(0).getInstance();
        GameService game = GameService.getInstance();
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
       return new CombatEncounter(monsters, inv);
    }
}
