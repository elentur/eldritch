package gui;

import static gameBuild.Global.game;

import java.util.Map;

import gameBuild.Global;
import gameItems.Round;
import gameMechanics.IO;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class GamesScreen {
	private static Group root;
	private static Scene screen;
	private static boolean build=true;
	private static GameBoardGui gameBoardGui;
	private static Group gameRoot;
	private static AncientOneInterface aOInterface;
	private static InvestigatorInterface invInterface;
	private static Map<String,String> names;
	private static Rectangle phase;
	private static Label lblPhase;
	private static Round round;
	
	
	public static void setScreen(Group root1, Scene scene) {
		screen = scene;
		root = root1;
		if(build){
			buildButtons();
			
			buildGui();
		}

		gameRoot = new Group(gameBoardGui,aOInterface,invInterface,phase,lblPhase);
		root.getChildren().add(0, gameRoot);
		
		Animations.blendingUp().setOnFinished(a->{
			Animations.tokenAppearsTransition(Global.tokenAppearsTransitionList);
		});
		
		if(Global.debug){
			
			//Global.game.spawnGates(1);
			//Global.game.spawnClues(3);
			
		}
		
	}
	private static void buildButtons() {
		build=false;
		names= IO.readText(Global.language + "/GameScreen.txt");
	}
	
	private static void buildGui(){
		gameBoardGui= new GameBoardGui();
		aOInterface = new AncientOneInterface(game.getAncientOne());
		invInterface= new InvestigatorInterface();
		invInterface.translateYProperty().bind(screen.heightProperty().subtract(invInterface.heightProperty()));
		phase=new Rectangle();
		phase.setFill(new ImagePattern(MenueTextures.graphicButton));
		phase.heightProperty().bind(screen.heightProperty().divide(13));
		phase.widthProperty().bind(phase.heightProperty().multiply(4.3));
		phase.translateXProperty().bind(screen.widthProperty().subtract(phase.widthProperty()).divide(2));
		round=Global.game.getRound();
		lblPhase = new Label(names.get(round.getPhase().toString()));
		lblPhase.translateXProperty().bind(phase.translateXProperty());
		lblPhase.translateYProperty().bind(phase.heightProperty().divide(8));
		lblPhase.prefWidthProperty().bind(phase.widthProperty());
		lblPhase.styleProperty().bind(Effects.fontMedium);
		lblPhase.setTextFill(Effects.fontColorDark);
		lblPhase.setAlignment(Effects.fontPos);
		lblPhase.setTextAlignment(TextAlignment.CENTER);
		Global.game.getRound().phaseProperty().addListener(a->{
			lblPhase.setText(names.get(round.getPhase().toString()));
		});
		//invInterface.translateXProperty().bind(screen.widthProperty().divide(-40));
	}
}
