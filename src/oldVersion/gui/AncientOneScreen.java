package oldVersion.gui;

import java.awt.Menu;
import java.io.IOException;
import java.util.Map;

import com.sun.javafx.effect.EffectDirtyBits;

import oldVersion.ancientOnes.Cthulhu;
import oldVersion.enums.Screens;
import oldVersion.gameBuild.Game;
import oldVersion.gameBuild.Global;
import oldVersion.gameItems.AncientOne;
import oldVersion.gameMechanics.IO;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class AncientOneScreen {
	private static Group root;
	private static Group ancientRoot;
	private static Scene ancientOneScreen;
	public static boolean build;
	private static Rectangle btnAzathothFrame;
	private static Rectangle btnShubNiggurathFrame;
	private static Rectangle btnYogSothothFrame;
	private static Rectangle btnCthulhuFrame;
	private static Rectangle btnAzathothPicture;
	private static Rectangle btnShubNiggurathPicture;
	private static Rectangle btnYogSothothPicture;
	private static Rectangle btnCthulhuPicture;
	private static Button btnBack;
	private static Button btnAccept;
	private static Rectangle imgBackground;
	private static AncientOneCard ancientOneCard;
	
	
	
	private static AncientOne ancientOne;
	
	
	
	public static void setScreen(Group root1, Scene scene1) {
		ancientOneScreen=scene1;
		root =root1;
		if(build){
			ancientOne=null;
			buildButtons();
			buildOther();
			buildShowCase();
		}
		if(ancientOne ==null){
			btnAccept.setDisable(true);
			ancientOneCard.setVisible(false);
		}
		
		ancientRoot = new Group(imgBackground, btnCthulhuFrame,btnAzathothFrame,
				btnYogSothothFrame,btnShubNiggurathFrame,btnCthulhuPicture,btnAzathothPicture,
				btnYogSothothPicture,btnShubNiggurathPicture,btnBack,btnAccept,ancientOneCard);
		root.getChildren().add(0,ancientRoot);
		Animations.blendingUp();
		
	}

	private static void buildOther() {
		imgBackground = new Rectangle();
		imgBackground.widthProperty().bind(ancientOneScreen.widthProperty());
		imgBackground.heightProperty().bind(ancientOneScreen.heightProperty());
		imgBackground.setFill(new ImagePattern(MenueTextures.background));

	}

	private static void buildButtons() {
		build=false;
		
		Map<String,String> names=IO.readText(Global.language+"/Menu.txt");
	
		
		btnCthulhuFrame = new Rectangle();
		btnCthulhuFrame.setFill(new ImagePattern(MenueTextures.frame));
		btnCthulhuFrame.widthProperty().bind(ancientOneScreen.widthProperty().divide(8));
		btnCthulhuFrame.heightProperty().bind(ancientOneScreen.heightProperty().divide(4));
		btnCthulhuFrame.translateXProperty().bind(btnCthulhuFrame.widthProperty().multiply(1.05));
		btnCthulhuFrame.translateYProperty().bind(btnCthulhuFrame.heightProperty().divide(5));
		btnCthulhuFrame.setEffect(Effects.shadowBtn);
		//btnCthulhuFrame.setRotate(10);
		
		
		btnCthulhuPicture = new Rectangle();
		btnCthulhuPicture.setFill(new ImagePattern(MenueTextures.cthulhu));
		btnCthulhuPicture.widthProperty().bind(ancientOneScreen.widthProperty().divide(8));
		btnCthulhuPicture.heightProperty().bind(ancientOneScreen.heightProperty().divide(4));
		btnCthulhuPicture.translateXProperty().bind(btnCthulhuFrame.widthProperty().multiply(1.05));
		btnCthulhuPicture.translateYProperty().bind(btnCthulhuFrame.heightProperty().divide(5));
		btnCthulhuPicture.setOnMouseEntered(a-> Effects.highlightPct(a));
		btnCthulhuPicture.setOnMouseExited(a-> Effects.highlightPctOff(a,ancientOne,"Cthulhu"));
		btnCthulhuPicture.setOnMouseClicked(a->{
			loadAncientOne(a,"Cthulhu");
		});
		
		btnAzathothFrame = new Rectangle();
		btnAzathothFrame.setFill(new ImagePattern(MenueTextures.frame));
		btnAzathothFrame.widthProperty().bind(ancientOneScreen.widthProperty().divide(8));
		btnAzathothFrame.heightProperty().bind(ancientOneScreen.heightProperty().divide(4));
		btnAzathothFrame.translateXProperty().bind(btnCthulhuFrame.translateXProperty().multiply(2.5));
		btnAzathothFrame.translateYProperty().bind(btnCthulhuFrame.heightProperty().divide(5));
		btnAzathothFrame.setEffect(Effects.shadowBtn);
		//btnAzathothFrame.setRotate(-12);
		
		btnAzathothPicture = new Rectangle();
		btnAzathothPicture.setFill(new ImagePattern(MenueTextures.azathoth));
		btnAzathothPicture.widthProperty().bind(ancientOneScreen.widthProperty().divide(8));
		btnAzathothPicture.heightProperty().bind(ancientOneScreen.heightProperty().divide(4));
		btnAzathothPicture.translateXProperty().bind(btnCthulhuFrame.translateXProperty().multiply(2.5));
		btnAzathothPicture.translateYProperty().bind(btnCthulhuFrame.heightProperty().divide(5));
		btnAzathothPicture.setOnMouseEntered(a-> Effects.highlightPct(a));
		btnAzathothPicture.setOnMouseExited(a-> Effects.highlightPctOff(a,ancientOne,"Azathoth"));
		btnAzathothPicture.setOnMouseClicked(a->{
			loadAncientOne(a,"Azathoth");
		});
		
		
		btnShubNiggurathFrame = new Rectangle();
		btnShubNiggurathFrame.setFill(new ImagePattern(MenueTextures.frame));
		btnShubNiggurathFrame.widthProperty().bind(ancientOneScreen.widthProperty().divide(8));
		btnShubNiggurathFrame.heightProperty().bind(ancientOneScreen.heightProperty().divide(4));
		btnShubNiggurathFrame.translateXProperty().bind(btnCthulhuFrame.translateXProperty());
		btnShubNiggurathFrame.translateYProperty().bind(btnCthulhuFrame.heightProperty().divide(5).multiply(2).add(btnCthulhuFrame.heightProperty()));
		btnShubNiggurathFrame.setEffect(Effects.shadowBtn);
		//btnShubNiggurathFrame.setRotate(4);
		
		btnShubNiggurathPicture = new Rectangle();
		btnShubNiggurathPicture.setFill(new ImagePattern(MenueTextures.shubNiggurath));
		btnShubNiggurathPicture.widthProperty().bind(ancientOneScreen.widthProperty().divide(8));
		btnShubNiggurathPicture.heightProperty().bind(ancientOneScreen.heightProperty().divide(4));
		btnShubNiggurathPicture.translateXProperty().bind(btnShubNiggurathFrame.translateXProperty());
		btnShubNiggurathPicture.translateYProperty().bind(btnShubNiggurathFrame.translateYProperty());
		btnShubNiggurathPicture.setOnMouseEntered(a-> Effects.highlightPct(a));
		btnShubNiggurathPicture.setOnMouseExited(a-> Effects.highlightPctOff(a,ancientOne,"Shub-Niggurath"));
		btnShubNiggurathPicture.setOnMouseClicked(a->{
			loadAncientOne(a,"ShubNiggurath");
		});
		
		
		btnYogSothothFrame = new Rectangle();
		btnYogSothothFrame.setFill(new ImagePattern(MenueTextures.frame));
		btnYogSothothFrame.widthProperty().bind(ancientOneScreen.widthProperty().divide(8));
		btnYogSothothFrame.heightProperty().bind(ancientOneScreen.heightProperty().divide(4));
		btnYogSothothFrame.translateXProperty().bind(btnAzathothFrame.translateXProperty());
		btnYogSothothFrame.translateYProperty().bind(btnCthulhuFrame.heightProperty().divide(5).multiply(2).add(btnCthulhuFrame.heightProperty()));
		btnYogSothothFrame.setEffect(Effects.shadowBtn);
		//btnYogSothothFrame.setRotate(-8);
		
		btnYogSothothPicture = new Rectangle();
		btnYogSothothPicture.setFill(new ImagePattern(MenueTextures.yogSothoth));
		btnYogSothothPicture.widthProperty().bind(ancientOneScreen.widthProperty().divide(8));
		btnYogSothothPicture.heightProperty().bind(ancientOneScreen.heightProperty().divide(4));
		btnYogSothothPicture.translateXProperty().bind(btnYogSothothFrame.translateXProperty());
		btnYogSothothPicture.translateYProperty().bind(btnYogSothothFrame.translateYProperty());
		btnYogSothothPicture.setOnMouseEntered(a-> Effects.highlightPct(a));
		btnYogSothothPicture.setOnMouseExited(a-> Effects.highlightPctOff(a,ancientOne,"Yog-Sothoth"));
		btnYogSothothPicture.setOnMouseClicked(a->{
			loadAncientOne(a,"YogSothoth");
		});
		
		
		
		btnBack = new Button(names.get("back"));
		btnBack.translateXProperty().bind(btnBack.widthProperty().divide(2));
		btnBack.translateYProperty().bind(ancientOneScreen.heightProperty().subtract(btnBack.heightProperty().multiply(1.2)));
		btnBack.setOnMouseClicked(a->{
			Animations.blendingDown(Screens.StartScreen);;
		});
		
		btnAccept = new Button(names.get("accept"));
		btnAccept.translateXProperty().bind(ancientOneScreen.widthProperty().subtract(btnAccept.widthProperty().multiply(1.5)));
		btnAccept.translateYProperty().bind(ancientOneScreen.heightProperty().subtract(btnBack.heightProperty().multiply(1.2)));
		btnAccept.setOnMouseClicked(a->{
			Global.game=new Game(ancientOne);
			Animations.blendingDown(Screens.InvestigatorScreen);;
		});
		
	}

	private static void loadAncientOne(MouseEvent a,String ancient) {
		btnCthulhuPicture.setEffect(null);
		btnAzathothPicture.setEffect(null);
		btnShubNiggurathPicture.setEffect(null);
		btnYogSothothPicture.setEffect(null);
		Effects.highlightPct(a);
		ancientOne=new AncientOne(ancient);
		btnAccept.setDisable(false);
		ancientOneCard.setAncientOne(ancientOne);
		if(!ancientOneCard.isVisible()){
			ancientOneCard.setVisible(true);
			Animations.startFadeIn(ancientOneCard);
			
		}
		
		
		
		
	}
	
	private static void buildShowCase(){
		ancientOneCard= new AncientOneCard(false);
		ancientOneCard.translateXProperty().bind(ancientOneScreen.widthProperty().divide(1.8));
		ancientOneCard.translateYProperty().bind(ancientOneScreen.widthProperty().divide(50));
		
	}
	
}
