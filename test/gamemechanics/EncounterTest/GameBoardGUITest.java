package gamemechanics.EncounterTest;

import Service.DiceRollerService;
import Service.GameService;
import container.InvestigatorContainer;
import factory.GameBoardFactory;
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
import model.Item.ancientOnes.Azathoth;
import model.Item.assets.ProfaneTome;
import model.Item.conditions.*;
import model.Item.investigators.AgnesBaker;
import model.Item.investigators.AkachiOnyele;
import model.Item.investigators.CharlieKane;
import model.Item.investigators.DianaStanley;
import model.effects.Reckoning;

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
        GameService.getInstance().setGameBoardAndAncientOne( new Azathoth(), gameBoard);
        GameService.getInstance().addInvestigators(new InvestigatorContainer(Arrays.asList(new AgnesBaker(), new AkachiOnyele(), new CharlieKane(), new DianaStanley())));
      //  GameService.getInstance().addRumor(new RumorToken(FieldID.PYRAMIDS,new Mythos0()));


     //   GameService.getInstance().getGameBoard().getField(FieldID.ARKHAM).getMonster().add(new Shan());
     //
        Investigator inv = GameService.getInstance().getActiveInvestigator();
        DiceRollerService.debug=true;
     /*   inv.getInventory().clear();
        inv.getInventory().add(new CultesDesGoules());
        inv.getInventory().add(new AighteenthDerringer());
        inv.getInventory().add(new Wither());
        inv.getInventory().add(new Amnesia0());
        inv.getInventory().add(new ArcaneManuscript());
        inv.getInventory().add(new BullWhip());
        inv.getInventory().add(new CultistJournal());
        inv.getInventory().add(new ProfaneTome());*/

       // gameboardGUI.getChildren().add(new ImproveEffectOverlay(new Improve(TestType.LORE,2,GameService.getInstance().getActiveInvestigator())));


        StackPane root = new StackPane();
        Interface inter = new Interface();
        InterfaceLinking.init(primaryStage,root,inter,gameboardGUI);
       inter.init(root);

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

        primaryStage.show();
        primaryStage.setMaximized(true);
        GameService.getInstance().startGame();
        GameService.getInstance().getEncounteringInvestigator().addToInventory(new Paranoia1());
        GameService.getInstance().addEffect(new Reckoning());
       // GameService.getInstance().addEffect(new GainAsset(
      //          (Asset) GameService.getInstance().getAssets().get("&bandages"),
       //         GameService.getInstance().getEncounteringInvestigator()));
       // GameService.getInstance().addEffect(new LooseOrGainHealthSanity(SpendType.HEALTH,-4,GameService.getInstance().getEncounteringInvestigator()));
        //GameService.getInstance().addEffect(new SpawnClue(1,FieldID.LONDON));
    }


}
