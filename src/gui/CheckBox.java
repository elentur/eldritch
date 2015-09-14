package gui;



import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class CheckBox extends Group {
private boolean on;
private Rectangle picture;
private Label lblText;

	public CheckBox(){
		on = false;
		Scene scene = StageControll.getPrimaryStage().getScene();
		picture=new Rectangle();
		picture.setFill(new ImagePattern(MenueTextures.checkBoxOff));
		picture.setOnMouseClicked(a->activate(a));
		picture.widthProperty().bind(scene.widthProperty().divide(60));
		picture.heightProperty().bind(scene.widthProperty().divide(60));
		lblText = new Label();
		lblText.setAlignment(Effects.fontPos);
		lblText.setTextFill(Effects.fontColor);
		lblText.setFont(Effects.fontMedium2);
		lblText.setPadding(new Insets(0,0,0,20));
		lblText.translateXProperty().bind(picture.widthProperty());
		this.getChildren().addAll(picture,lblText);
		this.setEffect(Effects.shadowBtn);
	}

	private void activate(MouseEvent a) {
		if(a.getButton()==MouseButton.PRIMARY){
			if(on){
				picture.setFill(new ImagePattern(MenueTextures.checkBoxOff));
			}else{
				picture.setFill(new ImagePattern(MenueTextures.checkBoxOn));
			}
			on=!on;
		}
	}
	public void setText(String text){
		lblText.setText(text);
	}
	
	
}
