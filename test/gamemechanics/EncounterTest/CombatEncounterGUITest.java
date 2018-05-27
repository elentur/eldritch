package gamemechanics.EncounterTest;

import container.ItemContainer;
import factory.ItemFactory;
import gamemechanics.CombatEncounter;
import gui.CombatEncounterGui;
import gui.DialogGui;
import gui.Fonts;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Investigator;
import model.Item.Asset;
import model.Item.investigators.AgnesBaker;
import model.Item.monsters.Shan;
import model.Item.monsters.Vampire;
import model.Monster;

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
        VBox root = new VBox();
        Scene scene = new Scene(root);
        scene.setFill(Color.RED);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(e->primaryStage.close());
        primaryStage.show();
        DialogGui dlg = new CombatEncounterGui(encounter);
        dlg.showAndWait();
    }

    private CombatEncounter initCombatEncounter() {
        Investigator inv = new AgnesBaker();
        ItemContainer<Asset> assets = new ItemFactory().getAssets();
      //  inv.getInventory().remove(assets.get("&profaneTome"));
        inv.getInventory().add(assets.get("&lantern"));
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Vampire());
        monsters.add(new Shan());

        return new CombatEncounter(monsters, inv);
    }


}
