package gui;

import java.io.IOException;
import java.util.Map;

import gameBuild.Global;
import gameItems.AncientOne;
import gameMechanics.IO;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class AncientOneCard extends Group {
	private Label lblName;
	private Label lblDoom;
	private Label lblAtmosphereText;
	private Label lblSpecialText;
	private Label lblReckoningText;
	private Label lblHeadline;
	private Rectangle picture;
	private Rectangle ancientOnePicture;
	private Scene scene;
	private MonsterToken grpCultist ;
	private OkCancelBtn btnClose;
	private Label cultistName;
	
	private AncientOne ancientOne=null;
	
	public AncientOneCard( boolean CloseButton){
		
		scene = StageControll.getPrimaryStage().getScene();
		Map<String,String> names = IO.readText(Global.language+"/Menu.txt");
	
		btnClose= new OkCancelBtn(false);
		btnClose.setNode(this);
		btnClose.setVisible(CloseButton);
		Animations.startFadeIn(this);
		picture = new Rectangle();
		picture.setFill(new ImagePattern(MenueTextures.ancientOneCard));
		picture.widthProperty().bind(scene.widthProperty().divide(3));
		picture.heightProperty().bind(scene.widthProperty().divide(2.43));
		picture.setEffect(Effects.shadowBtn);
		this.setEffect(Effects.shadowBtn);
		
		ancientOnePicture= new Rectangle();
		ancientOnePicture.widthProperty().bind(picture.widthProperty());
		ancientOnePicture.heightProperty().bind(picture.widthProperty().multiply(0.49));
		
		cultistName=new Label();
		cultistName.translateXProperty().bind(picture.widthProperty().divide(6));
		cultistName.translateYProperty().bind(picture.heightProperty().divide(2.6));
		cultistName.styleProperty().bind(Effects.fontSmall);
		cultistName.setTextFill(Effects.fontColorDark);
		cultistName.setAlignment(Effects.fontPos);
		
		grpCultist=new MonsterToken();
		grpCultist.translateXProperty().bind(picture.widthProperty().divide(15));
		grpCultist.translateYProperty().bind(picture.heightProperty().divide(2.4));
		
		lblHeadline = new Label();
		lblHeadline.styleProperty().bind(Effects.fontSmallCursive);
		lblHeadline.setTextFill(Effects.fontColorDark);
		lblHeadline.setAlignment(Effects.fontPos);
		lblHeadline.translateXProperty().bind(picture.widthProperty().divide(2));
		lblHeadline.translateYProperty().bind(picture.widthProperty().divide(2.1));
		lblHeadline.minWidthProperty().bind(picture.widthProperty().divide(2));
		
		lblDoom=new Label();
		lblDoom.styleProperty().bind(Effects.fontBig);
		lblDoom.setTextFill(Effects.fontColor);
		lblDoom.setAlignment(Effects.fontPos);
		lblDoom.translateXProperty().bind(picture.translateXProperty().add(picture.widthProperty()));
		lblDoom.translateXProperty().bind(picture.widthProperty().divide(6));
		lblDoom.translateYProperty().bind(picture.widthProperty().divide(40));
	
		
		lblName = new Label();
		lblName.styleProperty().bind(Effects.fontBig);
		lblName.setEffect(Effects.shadowBtn);
		lblName.setTextFill(Effects.fontColor);
		lblName.setAlignment(Effects.fontPos);
		lblName.translateXProperty().bind(picture.widthProperty().divide(3));
		lblName.translateYProperty().bind(picture.widthProperty().divide(4));
		//lblName.minWidthProperty().bind(texts.widthProperty());
		lblAtmosphereText = new Label();
		lblAtmosphereText.styleProperty().bind(Effects.fontSmallCursive);
		lblAtmosphereText.setTextFill(Effects.fontColorDark);
		lblAtmosphereText.setAlignment(Effects.fontPosLeft);
		lblAtmosphereText.setWrapText(true);
		lblSpecialText= new Label();
		lblSpecialText.styleProperty().bind(Effects.fontSmall);
		lblSpecialText.setTextFill(Effects.fontColorDark);
		lblSpecialText.setAlignment(Effects.fontPosLeft);
		lblSpecialText.setWrapText(true);
		lblReckoningText= new Label();
		lblReckoningText.styleProperty().bind(Effects.fontSmall);
		lblReckoningText.setTextFill(Effects.fontColorDark);
		lblReckoningText.setAlignment(Effects.fontPosLeft);
		lblReckoningText.setWrapText(true);
		VBox texts = new VBox(lblAtmosphereText,lblSpecialText,lblReckoningText);
		texts.translateXProperty().bind(picture.widthProperty().divide(2.2));
		texts.translateYProperty().bind(picture.widthProperty().divide(1.8));
		texts.maxWidthProperty().bind(picture.widthProperty().divide(1.85));
		VBox card = new VBox(new Group(ancientOnePicture,grpCultist,picture,lblName,lblDoom,texts,lblHeadline,cultistName),btnClose);
		card.setAlignment(Effects.fontPos);
		this.getChildren().addAll(card);
		
		
		Global.overlay.getChildren().add(this);
		if(CloseButton){
			Global.screen.getChildren().get(0).setDisable(true);
			Global.screen.getChildren().get(0).setEffect(Effects.blure);
		}
	}
	
	public void setAncientOne(AncientOne ancientOne2) {
		ancientOne=ancientOne2;
		ancientOnePicture.setFill(new ImagePattern(ancientOne.getBigPicture()));
		
		lblName.setText(ancientOne.getName());
		lblHeadline.setText(ancientOne.getHeadline1());
		lblAtmosphereText.setText(ancientOne.getAmtosphereText());
		lblSpecialText.setText(ancientOne.getSpecialText());
		lblSpecialText.setContentDisplay(ContentDisplay.TOP);
		lblSpecialText.setGraphic(null);
		if(lblSpecialText.getText()!=null)lblSpecialText.setGraphic(new Circle(picture.getHeight()/100,Effects.fontColorDark));
		lblReckoningText.setText(ancientOne.getReckoningText1());
		lblReckoningText.setGraphic(null);
		if(lblReckoningText.getText()!=null)lblReckoningText.setGraphic(new Rectangle(picture.getHeight()/36,picture.getHeight()/36,new ImagePattern(MenueTextures.reckoningBlack)));
		lblReckoningText.setContentDisplay(ContentDisplay.TOP);
		lblDoom.setText(ancientOne.getDoom()+"");
		this.grpCultist.setMonster(ancientOne.getCultist1());
		cultistName.setText(ancientOne.getCultist1().getName());
		
		
	}
	public OkCancelBtn getCloseButton(){
		if(btnClose.isVisible())return null;
		return btnClose;
	}

	public DoubleExpression widthProperty() {
		// TODO Auto-generated method stub
		return picture.widthProperty();
	}
}
