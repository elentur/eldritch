package gui;

import java.util.ArrayList;
import java.util.List;

import gameBuild.Global;
import gameItems.Field;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
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
		Global.scrollPane =  new ScrollPane();
		Global.scrollPane.maxWidthProperty().bind(scene.widthProperty());
		Global.scrollPane.minWidthProperty().bind(scene.widthProperty());
		Global.scrollPane.maxHeightProperty().bind(scene.heightProperty());
		Global.scrollPane.minHeightProperty().bind(scene.heightProperty());
		
		Global.scrollPane.getStylesheets().add("/gui/MyScrollBar.css");
		Global.scrollPane.setPannable(true);
		Global.scrollPane.setContent(group);

		Global.scrollPane.setFitToHeight(true);
		Global.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		Global.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		//Global.scrollPane.setVmax(1797);
		//Global.scrollPane.setHmax(2902);
//		Global.scrollPane.setOnMouseClicked(a->{Global.lbldebug.setText(
//				Global.scrollPane.getHvalue()+"   "+
//						Global.scrollPane.getVvalue()
//				);
//		
//		});
//	
		this.getChildren().addAll(Global.scrollPane);
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
