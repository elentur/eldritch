package gameMechanics;

import gui.Animations;
import gui.Effects;
import gui.StageControll;
import javafx.animation.Transition;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class TextAppearsTransition {
	private Transition transition;
	private Label newText;
	private boolean shown;
	public TextAppearsTransition(String text , Color color){
		this(text,color,Effects.fontBig);
			}
	public TextAppearsTransition(String text , Color color,StringProperty size){
		newText = new Label(text);
		newText.styleProperty().bind(size);
		newText.setTextAlignment(TextAlignment.CENTER);
		newText.setAlignment(Effects.fontPos);
		newText.setTextFill(color);
		newText.setEffect(Effects.shadowBtn);
		newText.setWrapText(true);
		newText.setMouseTransparent(true);
		Scene scene = StageControll.getPrimaryStage().getScene();
		newText.maxWidthProperty().bind(scene.widthProperty().divide(1.5));
		newText.translateXProperty().bind(scene.widthProperty().subtract(newText.widthProperty()).divide(2));
		newText.translateYProperty().bind(scene.heightProperty().divide(3));
		transition = Animations.newTextAppearsTransition(newText,Duration.millis(60*text.length()));
		shown =false;
	}
	public Transition getTransition(){
		return transition;
	}
	public Node getNode() {
		return newText;
	}
	
	public void setGraphicNode(Node node){
		newText.setGraphic(node);
	}
	public boolean isShown() {
		return shown;
	}
	public void setShown(boolean shown) {
		this.shown = shown;
	}
	
}
