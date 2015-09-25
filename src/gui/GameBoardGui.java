package gui;

import java.util.ArrayList;
import java.util.List;

import gameBuild.Global;
import gameItems.Field;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
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
			
			Rectangle backBlack = new Rectangle();
			backBlack.setFill(Color.BLACK);
			backBlack.widthProperty().bind(scene.widthProperty().add(gameBoard.getWidth()));
			backBlack.heightProperty().bind(scene.heightProperty().add(gameBoard.getHeight()));
		Group backGroup = new Group(backBlack,group);
		group.translateXProperty().bind(scene.widthProperty().divide(2));
		group.translateYProperty().bind(scene.heightProperty().divide(2));
		Global.scrollPane =  new ScrollPane();
		Global.scrollPane.maxWidthProperty().bind(scene.widthProperty());
		Global.scrollPane.minWidthProperty().bind(scene.widthProperty());
		Global.scrollPane.maxHeightProperty().bind(scene.heightProperty());
		Global.scrollPane.minHeightProperty().bind(scene.heightProperty());
		
		Global.scrollPane.getStylesheets().add("/gui/MyScrollBar.css");
		Global.scrollPane.setPannable(true);
		Global.scrollPane.setContent(backGroup);
		

		Global.scrollPane.setFitToHeight(true);
		Global.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		Global.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		Global.game.getRound().activInvestigatorProperty().addListener(a->updateFocus(
				Global.game.getGameBoard().getInvestigatorField(
						Global.game.getInvestigators().get(((IntegerProperty)a).get()))));
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
		Animations.moveToMap((Group)Global.scrollPane.getContent(),field.getPosition().getX(), field.getPosition().getY());
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
