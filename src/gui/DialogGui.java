package gui;

import java.awt.ScrollPaneAdjustable;
import java.io.IOException;
import java.util.Map;

import elements.Investigator;
import gameBuild.Global;
import gameMechanics.IO;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DialogGui extends Group {
	private Label lblHeadline;
	private Label lblText;
	private Rectangle picture;
	private Scene scene;
	private OkCancelBtn btnClose;
	private ScrollPane scrollPane;
	private Map<String,String> names;
	
	public DialogGui( boolean CloseButton){
		
		scene = StageControll.getPrimaryStage().getScene();
		names = IO.readText(Global.language+"/GameScreen.txt");
		
		btnClose= new OkCancelBtn(false);
		btnClose.setNode(this);
		btnClose.setVisible(CloseButton);
		Animations.startFadeIn(this);
		picture = new Rectangle();
		picture.setFill(new ImagePattern(MenueTextures.infoBox));
		picture.widthProperty().bind(scene.widthProperty().divide(5));
		picture.heightProperty().bind(scene.widthProperty().divide(5*1.3));
		picture.setMouseTransparent(true);
		this.setEffect(Effects.shadowBtn);
		
		lblHeadline = new Label();
		lblHeadline.styleProperty().bind(Effects.fontMedium);
		lblHeadline.setTextFill(Effects.fontColorDark);
		lblHeadline.setAlignment(Effects.fontPos);
		lblHeadline.translateXProperty().bind(picture.widthProperty().divide(2.3));
		lblHeadline.translateYProperty().bind(lblHeadline.heightProperty().divide(1.2));

		

		lblText = new Label();
		lblText.styleProperty().bind(Effects.fontSmall);
		lblText.setTextFill(Effects.fontColorDark);
		lblText.setAlignment(Effects.fontPosLeft);
		lblText.setCenterShape(true);
		lblText.setWrapText(true);
		lblText.maxWidthProperty().bind(picture.widthProperty().divide(1.5));

		
		scrollPane =  new ScrollPane();
		scrollPane.maxWidthProperty().bind(picture.widthProperty().divide(1.45));
		scrollPane.minWidthProperty().bind(picture.widthProperty().divide(1.45));
		scrollPane.maxHeightProperty().bind(picture.heightProperty().divide(1.45));
		scrollPane.minHeightProperty().bind(picture.heightProperty().divide(1.45));
		scrollPane.translateXProperty().bind(picture.widthProperty().divide(6));
		scrollPane.translateYProperty().bind(picture.heightProperty().divide(6));
		scrollPane.getStylesheets().add("/gui/MyScrollBar.css");
		scrollPane.setPannable(true);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setContent(lblText);
		
		VBox elements = new VBox(new Group(picture,lblHeadline,scrollPane),btnClose);
		elements.setAlignment(Effects.fontPos);
		this.getChildren().addAll(elements);
		this.translateXProperty().bind(scene.widthProperty().subtract(this.widthProperty()).divide(2));
		this.translateYProperty().bind(scene.heightProperty().divide(10));
		
		Global.overlay.getChildren().add(this);
		if(CloseButton){
			Global.screen.getChildren().get(0).setDisable(true);
			Global.screen.getChildren().get(0).setEffect(Effects.blure);
		}
	}
	
	
	public void setTextFont(Font font){
		lblText.setFont(font);
	}
	public void setText(String path, String headline, String text) {
			
		
	}
	public void setText(String headline, String text) {
		lblText.setText(text);
		lblHeadline.setText(headline);
		
	}
	
	public void setText( String text) {
		lblText.setText(names.get(text));
	
	}

	public OkCancelBtn getCloseButton(){
		if(btnClose.isVisible())return null;
		return btnClose;
	}

	public DoubleExpression widthProperty() {
		return picture.widthProperty();
	}
}
