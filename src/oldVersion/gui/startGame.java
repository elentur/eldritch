package oldVersion.gui;




import java.util.ArrayList;
import java.util.List;


import oldVersion.elements.Investigator;
import oldVersion.enums.Screens;
import oldVersion.exceptions.CardNotFoundException;
import oldVersion.gameBuild.Game;
import oldVersion.gameBuild.Global;
import oldVersion.gameItems.AncientOne;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

public class startGame extends Application {

	@Override
	public void start(Stage primaryStage) {
		Global.init();
		Global.debug=false;
		
		Global.language="src/oldVersion.language/english";
	
		StageControll.setPrimaryStage(primaryStage);
		MenueTextures.load();
		GameTextures.load();
		Global.overlay= new Group();
		Global.screen= new Group(Global.overlay);
		Scene scene = new Scene(Global.screen,960,540);
		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreen(true);
		primaryStage.setResizable(false);
		Effects.init();
		Animations.init();
		primaryStage.show();
		if(Global.debug){
			Global.game=new Game(new AncientOne("Cthulhu"));
			
			List<Investigator> investigators = new ArrayList<Investigator>();
			investigators.add(new Investigator("AkachiOnyele"));
			investigators.add(new Investigator("CharlieKane"));
			investigators.add(new Investigator("DianaStanley"));
			investigators.add(new Investigator("MarkHarrigan"));
			investigators.add(new Investigator("TrishScarborough"));
			investigators.add(new Investigator("NormanWithers"));
			
			Global.game.setInvestigators(investigators);
			try {
				investigators.get(0).addClues(Global.game.getCluePool().drawNextCard());
				investigators.get(0).addClues(Global.game.getCluePool().drawNextCard());
				investigators.get(0).addClues(Global.game.getCluePool().drawNextCard());
				investigators.get(0).addClues(Global.game.getCluePool().drawNextCard());
			} catch (CardNotFoundException e) {
			}
			//Global.lbldebug.setFont(Effects.font);
			//Global.lbldebug.setTextFill(Effects.fontColorRed);
			Animations.blendingDown(Screens.GameScreen);
		}else{
			Animations.blendingDown(Screens.StartScreen);
		}
		
		
		
		
		

	}

	public static void main(String[] args) {
		launch(args);
	}
}
