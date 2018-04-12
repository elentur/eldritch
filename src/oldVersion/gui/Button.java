package oldVersion.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Button extends Group {

	private Rectangle btnButton;
	private Label lblButton;

	public Button(String text){
		Scene scene= StageControll.getPrimaryStage().getScene();
		btnButton = new Rectangle();
		btnButton.setFill(new ImagePattern(MenueTextures.mainMenueBtn));
		btnButton.widthProperty().bind(scene.widthProperty().divide(5));
		btnButton.heightProperty().bind(scene.heightProperty().divide(10));
		btnButton.setEffect(Effects.shadowBtn);
		
		
		
		lblButton=new Label(text);
		lblButton.setAlignment(Effects.fontPos);
		lblButton.styleProperty().bind(Effects.fontBig);
		lblButton.setTextFill(Effects.fontColor);
		lblButton.minWidthProperty().bind(btnButton.widthProperty());
		lblButton.minHeightProperty().bind(btnButton.heightProperty());
		lblButton.setOnMouseEntered(a-> Effects.highlight(a));
		lblButton.setOnMouseExited(a-> Effects.highlightOff(a));
		this.getChildren().addAll(btnButton,lblButton);
		        
	}

	public DoubleProperty widthProperty() {
		return btnButton.widthProperty();
	}

	public DoubleProperty heightProperty() {

		return btnButton.heightProperty();
	}
	
}
