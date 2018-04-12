package oldVersion.gui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import oldVersion.enums.Screens;
import oldVersion.gameBuild.Global;
import oldVersion.gameMechanics.IO;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class StartScreen {
	private static Group root;
	private static Group startRoot;
	private static Scene startScreen;
	private static Button btnStartGame;
	private static Button btnJoinGame;
	private static Button btnLoadGame;
	private static Button btnOptions;
	private static Button btnExit;
	private static Rectangle imgBackground;

	private static boolean build=true;
	




	private static void buildButtons() {
		build=false;
		Map<String,String> names=IO.readText(Global.language+"/Menu.txt");
	
		
		
		
		btnStartGame = new Button(names.get("startGame"));
		btnStartGame.translateXProperty().bind(btnStartGame.widthProperty().divide(2));
		btnStartGame.translateYProperty().bind(btnStartGame.heightProperty());
		btnStartGame.setOnMouseClicked(a->{
			AncientOneScreen.build=true;
			Animations.blendingDown(Screens.AncientOneScreen);
		});
		
		
		btnJoinGame = new Button(names.get("joinGame"));
		btnJoinGame.translateXProperty().bind(btnStartGame.translateXProperty());
		btnJoinGame.translateYProperty().bind(btnStartGame.translateYProperty().multiply(2.5));

		
		
		btnLoadGame = new Button(names.get("loadGame"));
		btnLoadGame.translateXProperty().bind(btnStartGame.translateXProperty());
		btnLoadGame.translateYProperty().bind(btnStartGame.translateYProperty().multiply(4));

		
		
		btnOptions = new Button(names.get("options"));
		btnOptions.translateXProperty().bind(btnStartGame.translateXProperty());
		btnOptions.translateYProperty().bind(btnStartGame.translateYProperty().multiply(5.5));

		
		
		btnExit = new Button(names.get("exit"));
		btnExit.translateXProperty().bind(btnStartGame.translateXProperty());
		btnExit.translateYProperty().bind(btnStartGame.translateYProperty().multiply(7));
		btnExit.setOnMouseClicked(a->{
			System.exit(0);
		});
		
		
		
	}

	

	public static void setScreen(Group root1, Scene scene1) {
		startScreen=scene1;
		root =root1;
		
		
		if(build){
			buildButtons();
			buildOther();
		}
		startRoot = new Group(imgBackground, btnStartGame,btnJoinGame,
				btnLoadGame,btnOptions,btnExit);
		root.getChildren().add(0,startRoot);
		Animations.blendingUp();
		
	}



	private static void buildOther() {
		imgBackground = new Rectangle(10,10,Color.BLACK);
		imgBackground.widthProperty().bind(startScreen.widthProperty());
		imgBackground.heightProperty().bind(startScreen.heightProperty());
		imgBackground.setFill(new ImagePattern(MenueTextures.background));

	}
}
