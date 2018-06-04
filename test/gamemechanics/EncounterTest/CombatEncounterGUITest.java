package gamemechanics.EncounterTest;

import Service.GameService;
import container.ItemContainer;
import factory.ItemFactory;
import gamemechanics.CombatEncounter;
import gui.Fonts;
import gui.InterfaceLinking;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.Monster;
import model.Item.investigators.AgnesBaker;
import model.Item.monsters.HoundOfTindalos;
import model.Item.monsters.Shan;
import model.Item.monsters.Vampire;

import java.util.ArrayList;
import java.util.List;

public class CombatEncounterGUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Fonts.init(primaryStage);

        CombatEncounter encounter = initCombatEncounter();
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        scene.setFill(Color.RED);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(e->{
            primaryStage.close();
            System.exit(0);
        });
        InterfaceLinking.init(primaryStage);
        primaryStage.show();
        primaryStage.setMaximized(true);
        GameService.getInstance().addEncounter(initCombatEncounter());
    }

    private CombatEncounter initCombatEncounter() {
        Investigator inv = new AgnesBaker();
        ItemContainer<Asset> assets = new ItemFactory().getAssets();
      //  inv.getInventory().remove(assets.get("&profaneTome"));
        inv.getInventory().add(assets.get("&lantern"));
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Vampire());
        monsters.add(new Shan());
        monsters.add(new HoundOfTindalos());
        monsters.add(new Vampire());
        monsters.add(new Shan());
        monsters.add(new HoundOfTindalos());

        monsters.add(new Vampire());
        monsters.add(new Shan());
        monsters.add(new HoundOfTindalos());

        monsters.add(new Vampire());
        monsters.add(new Shan());
        monsters.add(new HoundOfTindalos());

        monsters.add(new Vampire());
        monsters.add(new Shan());
        monsters.add(new HoundOfTindalos());


        return new CombatEncounter(monsters, inv);
    }


}
