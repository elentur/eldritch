package gui;

import static gameBuild.Global.game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamesScreen {
	private static Group root;
	private static Scene screen;
	private static boolean build=true;
	private static GameBoardGui gameBoardGui;
	private static Group gameRoot;
	private static AncientOneInterface aOInterface;
	
	
	public static void setScreen(Group root1, Scene scene) {
		screen = scene;
		root = root1;
		if(build){
			buildButtons();
			buildGui();
		}

		gameRoot = new Group(gameBoardGui);
		root.getChildren().add(0, gameRoot);

		Animations.blendingUp();
		
	}
	private static void buildButtons() {
		build=false;
		gameBoardGui= new GameBoardGui();
	}
	
	private static void buildGui(){
		gameBoardGui= new GameBoardGui();
		aOInterface = new AncientOneInterface(game.getAncientOne());
	
	}
}
