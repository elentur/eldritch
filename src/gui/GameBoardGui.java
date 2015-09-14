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
		ScrollPane scrollPane =  new ScrollPane();
		scrollPane.maxWidthProperty().bind(scene.widthProperty());
		scrollPane.minWidthProperty().bind(scene.widthProperty());
		scrollPane.maxHeightProperty().bind(scene.heightProperty());
		scrollPane.minHeightProperty().bind(scene.heightProperty());
		
		scrollPane.getStylesheets().add("/gui/MyScrollBar.css");
		scrollPane.setPannable(true);
		scrollPane.setContent(group);

		scrollPane.setFitToHeight(true);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	
		this.getChildren().add(scrollPane);
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
