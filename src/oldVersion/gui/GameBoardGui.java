package oldVersion.gui;

import java.util.ArrayList;
import java.util.List;

import oldVersion.gameBuild.Global;
import oldVersion.gameItems.Field;
import oldVersion.gameMechanics.TextAppearsTransition;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameBoardGui extends Group {
private Rectangle gameBoard;
private List<FieldGui>  fields;

	public GameBoardGui(){
		Scene scene = StageControll.getPrimaryStage().getScene();
		gameBoard = new Rectangle();
		gameBoard.setFill(new ImagePattern(GameTextures.gameBoard));
		gameBoard.setWidth(2902);
		gameBoard.setHeight(1797);
		
		buildFields();
		
		
		Group group = new Group(gameBoard);
			group.getChildren().addAll(fields);
			
			HBox backBlack = new HBox();
			backBlack.setBackground(new Background(new BackgroundImage(GameTextures.backgroundPattern, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, null, null)));
			backBlack.prefWidthProperty().bind(scene.widthProperty().add(gameBoard.getWidth()));
			backBlack.prefHeightProperty().bind(scene.heightProperty().add(gameBoard.getHeight()));
		Group backGroup = new Group(backBlack,group);
		group.translateXProperty().bind(scene.widthProperty().divide(2));
		group.translateYProperty().bind(scene.heightProperty().divide(2));
		Global.scrollPane =  new ScrollPane();
		Global.scrollPane.maxWidthProperty().bind(scene.widthProperty());
		Global.scrollPane.minWidthProperty().bind(scene.widthProperty());
		Global.scrollPane.maxHeightProperty().bind(scene.heightProperty());
		Global.scrollPane.minHeightProperty().bind(scene.heightProperty());
		
		Global.scrollPane.getStylesheets().add("/oldVersion/gui/MyScrollBar.css");
		Global.scrollPane.setPannable(true);
		Global.scrollPane.setContent(backGroup);
		

		Global.scrollPane.setFitToHeight(true);
		Global.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		Global.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		Global.game.getRound().activInvestigatorProperty().addListener(a->updateFocus(
				Global.game.getGameBoard().getInvestigatorField(
						Global.game.getActiveInvestigator())));
		Global.scrollPane.setVmax(1797);
		Global.scrollPane.setHmax(2902);
//		Global.scrollPane.setOnMouseClicked(a->{Global.lbldebug.setText(
//				Global.scrollPane.getHvalue()+"   "+
//						Global.scrollPane.getVvalue()
//				);
//		
//		});
//	

		this.getChildren().addAll(Global.scrollPane);
		updateFocus(
				Global.game.getGameBoard().getInvestigatorField(
						Global.game.getInvestigators().get(Global.game.getRound().getActiveInvestigator())));
	}

	public void updateFocus(Field field) {
		Animations.moveToMap(field.getPosition().getX(), field.getPosition().getY());
	}

	private void buildFields() {
		List<Field>fieldList =new ArrayList<Field>(Global.game.getGameBoard().getFields().keySet());
		fields= new ArrayList<FieldGui>();
		FieldGui fieldGui;
		for(Field f: fieldList){
			 fieldGui = new FieldGui(f);
			fields.add(fieldGui);
		}
		
		
	}
}
