package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ScrollBar extends Group {
private Rectangle frame;
private ScrollPane scrollPane;
private HBox newNodes=new HBox();

	public ScrollBar(){
		Scene scene= StageControll.getPrimaryStage().getScene();
		frame= new Rectangle(0,0,new ImagePattern(MenueTextures.scrollBarFrame));
		frame.widthProperty().bind(scene.widthProperty().divide(1.86));
		frame.heightProperty().bind(scene.widthProperty().divide(8.42));
		frame.setMouseTransparent(true);
		scrollPane =  new ScrollPane();
		scrollPane.maxWidthProperty().bind(frame.widthProperty().subtract(frame.widthProperty().divide(20)));
		scrollPane.minWidthProperty().bind(frame.widthProperty().subtract(frame.widthProperty().divide(20)));
		scrollPane.maxHeightProperty().bind(frame.heightProperty());
		scrollPane.translateXProperty().bind(frame.widthProperty().subtract(scrollPane.widthProperty()).divide(2));
		scrollPane.translateYProperty().bind(frame.heightProperty().subtract(scrollPane.heightProperty()).divide(1.8));
		scrollPane.getStylesheets().add("/gui/MyScrollBar.css");
		scrollPane.setPannable(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		this.getChildren().addAll(scrollPane,frame);
		frame.setEffect(Effects.shadowBtn);
		
	}
	
	public void setContent(Node...nodes){
		newNodes.getChildren().addAll(nodes); 
		for(Node node : newNodes.getChildren()){
			if(node instanceof Rectangle){
				((Rectangle)node).widthProperty().bind(StageControll.getPrimaryStage().getScene().widthProperty().divide(12));
				((Rectangle)node).heightProperty().bind(StageControll.getPrimaryStage().getScene().widthProperty().divide(12));
			}
				
		}
		newNodes.setSpacing(10);
		scrollPane.setContent(newNodes);
	}
	public void addContent(Node node){
		if(node instanceof Rectangle){
			((Rectangle)node).widthProperty().bind(StageControll.getPrimaryStage().getScene().widthProperty().divide(12));
			((Rectangle)node).heightProperty().bind(StageControll.getPrimaryStage().getScene().widthProperty().divide(12));
		}
		newNodes.getChildren().add(node); 
		deselectAll();
	}
	
	public void deselectAll(){
		for(Node picture: newNodes.getChildren()){
			((Rectangle)picture).setStroke(Color.TRANSPARENT);
		}
	}

	public void removeContent(Rectangle flatToken) {
		newNodes.getChildren().remove(flatToken);
	}
	public DoubleProperty widthProperty(){
		return frame.widthProperty();
	}
	public DoubleProperty heightProperty(){
		return frame.heightProperty();
	}
}
