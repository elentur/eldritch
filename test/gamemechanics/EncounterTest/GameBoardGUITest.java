package gamemechanics.EncounterTest;

import Service.GameService;
import container.ItemContainer;
import factory.GameBoardFactory;
import factory.ItemFactory;
import gui.Fonts;
import gui.GameBoard.GameBoardGUI;
import gui.InterfaceLinking;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.GameBoard;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.investigators.AgnesBaker;

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
        GameService.getInstance().setActiveInvestigator(inv);
        Fonts.init(primaryStage);
        GameBoard gameBoard = GameBoardFactory.getGameBoard();

        GameBoardGUI root = new GameBoardGUI(gameBoard);
        Scene scene = new Scene(root);
        scene.setFill(Color.RED);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(e->{
            primaryStage.close();
            System.exit(0);
        });
        scene.getStylesheets().add("css/rootStyle.css");
        primaryStage.setOnCloseRequest(e->   System.exit(0));
        InterfaceLinking.init(primaryStage);
        primaryStage.show();
        primaryStage.setMaximized(true);


        gameBoard.addInvestigator(inv);
    }




}
