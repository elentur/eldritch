package gui;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GraphicButton extends Group {

	private Rectangle btnButton;
	private Label lblButton;

	public GraphicButton(String text){
		Scene scene= StageControll.getPrimaryStage().getScene();
		btnButton = new Rectangle();
		btnButton.setFill(new ImagePattern(MenueTextures.graphicButton));
		btnButton.heightProperty().bind(scene.heightProperty().divide(15));
		btnButton.widthProperty().bind(btnButton.heightProperty().multiply(4.3));
		
		btnButton.setEffect(Effects.shadowBtn);
		
		
		
		lblButton=new Label(text);
		lblButton.setAlignment(Effects.fontPosLeft);
		lblButton.setTextFill(Effects.fontColorDark);
		lblButton.setFont(Effects.fontGraphicButton);
		lblButton.setContentDisplay(ContentDisplay.LEFT);
		lblButton.minWidthProperty().bind(btnButton.widthProperty());
		lblButton.minHeightProperty().bind(btnButton.heightProperty());
		lblButton.setOnMouseEntered(a-> Effects.highlight(a));
		lblButton.setOnMouseExited(a-> Effects.highlightOff(a));
		lblButton.translateXProperty().bind(btnButton.heightProperty().divide(2));
		lblButton.translateYProperty().bind(btnButton.heightProperty().divide(4));
		this.getChildren().addAll(btnButton,lblButton);
	}
	public void setGraphic(Rectangle graphic){
		double width =((ImagePattern)graphic.getFill()).getImage().getWidth();
		double height =((ImagePattern)graphic.getFill()).getImage().getHeight();
		double ratio = width/height;
		graphic.heightProperty().bind(btnButton.heightProperty().divide(1.8));
		graphic.widthProperty().bind(graphic.heightProperty().multiply(ratio));
		graphic.setOnMouseClicked(null);
		lblButton.setGraphic(graphic);
	}
	public DoubleProperty widthProperty() {
		return btnButton.widthProperty();
	}

	public DoubleProperty heightProperty() {

		return btnButton.heightProperty();
	}
	public void setFontColor(Color color){
		lblButton.setTextFill(color);
	}
	
}
