package gui;

import enums.Screens;
import gameBuild.Global;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Animations {
	private static Scene scene;
	private static Group root;
	private static Rectangle blendscreenUp;
	private static Rectangle blendscreenDown;
	private static TranslateTransition uperScreen;
	private static TranslateTransition lowerScreen;
	private static ParallelTransition closeScreen;
	private static ParallelTransition openScreen;
	private static FadeTransition fadeIn;
	private static TranslateTransition inventoryUp;
	private static TranslateTransition inventoryDown;
	private static TranslateTransition moveMap;
	
	public static void init(){
		scene=StageControll.getPrimaryStage().getScene();
		inventoryUp = new TranslateTransition(Duration.millis(200));
		inventoryUp.setFromY(scene.getHeight()/1.8);
		inventoryUp.setToY(0);
		inventoryDown = new TranslateTransition(Duration.millis(200));
		inventoryDown.setFromY(0);
		inventoryDown.setToY(scene.getHeight()/1.8);
		moveMap = new TranslateTransition(Duration.millis(600)); 
	}
	
	public static void moveToMap(Group group, double x, double y ){
		moveMap.setNode(group);
		group.setTranslateX(group.getTranslateX()-Global.scrollPane.getHvalue());
		group.setTranslateY(group.getTranslateY()-Global.scrollPane.getVvalue());
		Global.scrollPane.setHvalue(0);
		Global.scrollPane.setVvalue(0);
		group.setCache(true);
		group.setCacheHint(CacheHint.SPEED);
		moveMap.setFromX(group.getTranslateX());
		moveMap.setFromY(group.getTranslateY());
		moveMap.setToX(-x);
		moveMap.setToY(-y);
		moveMap.setOnFinished(a->{
			group.setTranslateX(0);
			group.setTranslateY(0);
			Global.scrollPane.setHvalue(x);
			Global.scrollPane.setVvalue(y);
			group.setCacheHint(CacheHint.QUALITY);
		});
		moveMap.playFromStart();
	}
	
	public static void inventoryIn(Node node){
		inventoryUp.setNode(node);
		inventoryUp.playFromStart();
	}
	public static void inventoryOut(Node node){
		inventoryDown.setNode(node);
		inventoryDown.playFromStart();
		inventoryDown.setOnFinished(a->{
			((Group)node.getParent().getParent()).getChildren().remove(node.getParent());
		});
		
	
	}
	
	public static void PopOut(Node node){
		fadeIn = new FadeTransition(Duration.millis(300),node);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeIn.playFromStart();
	}
	public static void startFadeIn(Node node){
		fadeIn = new FadeTransition(Duration.millis(300),node);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeIn.playFromStart();
	}
	
	public static void startRotateFromTo(Node oldNode,Node node,Group group){
		 ScaleTransition st1 = new ScaleTransition(Duration.millis(100), group);
	     st1.setByX(-1);
	     st1.playFromStart();
	     st1.setOnFinished(a->{
	    	 group.getChildren().remove(oldNode);
	    	 group.getChildren().add(1,node);
	    	 //st1.setRate(-1.0);
	    	 st1.setByX(1.0f);
		     st1.play();
		     st1.setOnFinished(null);
	     });
	}
	
	public static void blendingDown(Screens from){
		new Runnable(){

			@Override
			public void run() {
				Platform.runLater(new Runnable(){

					@Override
					public void run() {
						if(blendscreenDown==null)createBlendScreen();
						root.setDisable(true);
						closeScreen.playFromStart();
						closeScreen.setOnFinished(a->{load(from);});
					}
					
				});
				
			}
			
		}.run();
		
	}
	public static void blendingUp(){
		new Runnable(){

			@Override
			public void run() {
				Platform.runLater(new Runnable(){

					@Override
					public void run() {
						root.setDisable(false);
						openScreen.setDelay(Duration.millis(150));
						openScreen.playFrom(Duration.millis(600));
					}
					
				});
			}
			
		}.run();
		
	}
	private static void load(Screens from){
		if(root.getChildren().size()>2)root.getChildren().remove(0);
		try {
			Thread.sleep(200);
		} catch (Exception e) {	}
		if(from ==Screens.StartScreen){
			StartScreen.setScreen(root,scene);
		}else if(from ==Screens.AncientOneScreen){
			AncientOneScreen.setScreen(root, scene);
		}else if(from ==Screens.InvestigatorScreen){
			InvestigatorScreen.setScreen(root, scene);
		}else if(from ==Screens.GameScreen){
			GamesScreen.setScreen(root, scene);
		}
	}
	
	private static void createBlendScreen() {
		
		root =(Group) scene.getRoot();
		
		blendscreenUp = new Rectangle();
		
		blendscreenUp.widthProperty().bind(scene.widthProperty());
		blendscreenUp.heightProperty().bind(scene.heightProperty().divide(2));
		blendscreenUp.setFill(new ImagePattern(MenueTextures.blendscreen_Up));
		blendscreenUp.setCache(true);
		blendscreenUp.setCacheHint(CacheHint.SPEED);


		
		
		blendscreenDown = new Rectangle();
		root.getChildren().add(new Group(blendscreenUp,blendscreenDown));
		blendscreenDown.widthProperty().bind(scene.widthProperty());
		blendscreenDown.heightProperty().bind(scene.heightProperty().divide(2));
		blendscreenDown.setFill(new ImagePattern(MenueTextures.blendscreen_Down));
		blendscreenDown.setCache(true);
		blendscreenDown.setCacheHint(CacheHint.SPEED);
		
		uperScreen = new TranslateTransition(Duration.millis(600), blendscreenUp);
		uperScreen.setFromY(-blendscreenUp.getHeight());
		uperScreen.setToY(0);
		lowerScreen = new TranslateTransition(Duration.millis(600), blendscreenDown);
		lowerScreen.setFromY(scene.getHeight());
		lowerScreen.setToY(blendscreenDown.getHeight());
		closeScreen = new ParallelTransition(uperScreen, lowerScreen);
		openScreen = new ParallelTransition(uperScreen, lowerScreen);
		openScreen.setRate(-1.0);
		closeScreen.setInterpolator(Interpolator.EASE_BOTH);
		openScreen.setInterpolator(Interpolator.EASE_BOTH);
		
			
	}

}
