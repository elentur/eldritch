package gui;



import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;

import elements.Investigator;
import enums.Screens;
import gameBuild.Game;
import gameBuild.Global;
import gameItems.AncientOne;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class startGame extends Application {

	@Override
	public void start(Stage primaryStage) {
		Global.debug=true;
		
		Global.language="src/language/english";
	
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
