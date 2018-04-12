package oldVersion.gui;

import oldVersion.gameBuild.Global;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class OkCancelBtn extends Group {

	private Rectangle btnButton;

	public OkCancelBtn(boolean ok){
		Scene scene= StageControll.getPrimaryStage().getScene();
		btnButton = new Rectangle();
		if(ok){
			btnButton.setFill(new ImagePattern(MenueTextures.okBtn));
		}else{
			btnButton.setFill(new ImagePattern(MenueTextures.cancelBtn));
		}
		
		btnButton.widthProperty().bind(scene.widthProperty().divide(25.6));
		btnButton.heightProperty().bind(scene.widthProperty().divide(20.21));
		btnButton.setOnMouseEntered(a-> Effects.highlight(a));
		btnButton.setOnMouseExited(a-> Effects.highlightOff(a));		
		
		
		this.getChildren().addAll(btnButton);
		        
	}

	public DoubleProperty widthProperty() {
		return btnButton.widthProperty();
	}

	public DoubleProperty heightProperty() {

		return btnButton.heightProperty();
	}

	public void setNode(Node node) {
		btnButton.setOnMouseClicked(a->close(node));
		
	}

	public void close(Node node) {
		((Group)node.getParent()).getChildren().remove(node);
		if(Global.overlay.getChildren().isEmpty()){
			Global.screen.getChildren().get(0).setDisable(false);
			Global.screen.getChildren().get(0).setEffect(null);
		}
	}
	
}
