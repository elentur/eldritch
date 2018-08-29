package gamemechanics.EncounterTest;

import Service.GameService;
import container.InvestigatorContainer;
import container.ItemContainer;
import enums.FieldID;
import factory.GameBoardFactory;
import factory.ItemFactory;
import gamemechanics.encounter.rumorencounter.RumorEncounter0;
import gui.EffectLayer;
import gui.Fonts;
import gui.Interface;
import gui.gameboard.GameBoardGUI;
import gui.InterfaceLinking;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.GameBoard;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.investigators.AgnesBaker;
import model.Item.investigators.AkachiOnyele;
import model.Item.investigators.CharlieKane;
import model.Item.investigators.DianaStanley;
import model.Item.monsters.Shan;
import model.Item.monsters.Vampire;
import model.Item.token.EldritchToken;
import model.Item.token.RumorToken;

import java.util.Arrays;

public class GameBoardGUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Investigator inv = new AgnesBaker();
        ItemContainer<Asset> assets = new ItemFactory().getAssets();
        //  inv.getInventory().remove(assets.get("&profaneTome"));
        inv.getInventory().add(assets.get("&lantern"));
        Fonts.init(primaryStage);
        GameBoard gameBoard = GameBoardFactory.getGameBoard();
        GameBoardGUI gameboardGUI = new GameBoardGUI(gameBoard);
       // Group effectLayer = new Group();
        StackPane root = new StackPane();
        root.getChildren().addAll(gameboardGUI,new Interface(root),new EffectLayer());
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

        GameService.getInstance().startGame(new InvestigatorContainer(Arrays.asList(new Investigator[]{inv, new AkachiOnyele(), new CharlieKane(), new DianaStanley()})), gameBoard);

        gameBoard.addMonster(new Shan(), FieldID.ARKHAM);
        gameBoard.addMonster(new Vampire(), FieldID.ARKHAM);

        GameService.getInstance().addGate(FieldID.PYRAMIDS);
        GameService.getInstance().addClue();
        GameService.getInstance().addRumor(new RumorToken(FieldID.PYRAMIDS, new RumorEncounter0(inv)));
        GameService.getInstance().addMystery(FieldID.PYRAMIDS);
        GameService.getInstance().addEldritchToken(FieldID.PYRAMIDS, new EldritchToken(new RumorEncounter0(inv)));
        GameService.getInstance().addExpedition();
    }


}
