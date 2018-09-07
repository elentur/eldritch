package gamemechanics.EncounterTest;

import Service.GameService;
import container.Inventory;
import container.InvestigatorContainer;
import enums.FieldID;
import factory.GameBoardFactory;
import gamemechanics.encounter.rumorencounter.RumorEncounter0;
import gui.EffectLayer;
import gui.Fonts;
import gui.Interface;
import gui.InterfaceLinking;
import gui.gameboard.GameBoardGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.GameBoard;
import model.Item.Investigator;
import model.Item.Mythos;
import model.Item.artifacts.CultesDesGoules;
import model.Item.assets.*;
import model.Item.conditions.AmnesiaCondition0;
import model.Item.conditions.BlessedCondition1;
import model.Item.investigators.AgnesBaker;
import model.Item.investigators.AkachiOnyele;
import model.Item.investigators.CharlieKane;
import model.Item.investigators.DianaStanley;
import model.Item.monsters.Shan;
import model.Item.monsters.Vampire;
import model.Item.mythos.Mythos0;
import model.Item.mythos.MythosTestRumor;
import model.Item.spells.Wither;
import model.Item.token.EldritchToken;
import model.Item.token.RumorToken;
import model.effects.SpawnGate;

import java.util.Arrays;

public class GameBoardGUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Fonts.init(primaryStage);
        GameBoard gameBoard = GameBoardFactory.getGameBoard();
        GameBoardGUI gameboardGUI = new GameBoardGUI(gameBoard);
        StackPane root = new StackPane();
        Interface inter = new Interface(root);

        root.getChildren().addAll(gameboardGUI,inter,new EffectLayer());
        Scene scene = new Scene(root);
        scene.setFill(Color.RED);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(e -> {
            primaryStage.close();
            System.exit(0);
        });
        scene.getStylesheets().add("css/rootStyle.css");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        InterfaceLinking.init(primaryStage);
        primaryStage.show();
        primaryStage.setMaximized(true);




        GameService.getInstance().startGame(new InvestigatorContainer(Arrays.asList(new AgnesBaker(), new AkachiOnyele(), new CharlieKane(), new DianaStanley())), gameBoard);

        GameService.getInstance().addRumor(new RumorToken(FieldID.PYRAMIDS,new MythosTestRumor()));
        GameService.getInstance().addMystery(FieldID.PYRAMIDS);
        GameService.getInstance().addEldritchToken(FieldID.PYRAMIDS, new EldritchToken(new RumorEncounter0()));
        GameService.getInstance().addExpedition();
        GameService.getInstance().getGameBoard().getField(FieldID.ARKHAM).getMonster().add(new Shan());
        Investigator inv = GameService.getInstance().getActiveInvestigator();
        inv.getInventory().clear();
        inv.getInventory().add(new CultesDesGoules());
        inv.getInventory().add(new AighteenthDerringer());
        inv.getInventory().add(new Wither());
        inv.getInventory().add(new AmnesiaCondition0());
        inv.getInventory().add(new ArcaneManuscript());
        inv.getInventory().add(new BullWhip());
        inv.getInventory().add(new CultistJournal());
        inv.getInventory().add(new ProfaneTome());



       // gameboardGUI.getChildren().add(new ImproveEffectOverlay(new Improve(TestType.LORE,2,GameService.getInstance().getActiveInvestigator())));
    }


}
