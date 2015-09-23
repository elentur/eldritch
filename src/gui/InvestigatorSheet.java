package gui;

import java.io.IOException;
import java.util.Map;

import elements.Investigator;
import gameBuild.Global;
import gameMechanics.IO;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class InvestigatorSheet extends Group {
	private Label lblName;
	private Label lblOccupation;
	private Label lblAction;
	private Label lblAbility;
	private Label lblQuote;
	private Label lblSanity;
	private Label lblHealth;
	private Label lblLore;
	private Label lblInfluence;
	private Label lblObservation;
	private Label lblStrength;
	private Label lblWill;
	private Rectangle picture;
	private Rectangle investigatorPicture;
	private Scene scene;
	private OkCancelBtn btnClose;
	
	private Investigator investigator=null;
	
	public InvestigatorSheet( boolean CloseButton){
		
		scene = StageControll.getPrimaryStage().getScene();
		Map<String,String> names = IO.readText(Global.language+"/Menu.txt");
	
		btnClose= new OkCancelBtn(false);
		btnClose.setNode(this);
		btnClose.setVisible(CloseButton);
			Animations.startFadeIn(this);
		picture = new Rectangle();
		picture.setFill(new ImagePattern(MenueTextures.investigatorSheet));
		picture.widthProperty().bind(scene.widthProperty().divide(1.82*1.3));
		picture.heightProperty().bind(scene.widthProperty().divide(2.26*1.3));
		picture.setMouseTransparent(true);
		this.setEffect(Effects.shadowBtn);
		
		btnClose.translateXProperty().bind(picture.widthProperty().subtract(btnClose.widthProperty()));
		btnClose.translateYProperty().bind(picture.heightProperty().subtract(btnClose.heightProperty()));
	
		
		investigatorPicture= new Rectangle();
		investigatorPicture.widthProperty().bind(picture.widthProperty().divide(3));
		investigatorPicture.heightProperty().bind(picture.widthProperty().divide(3));
		investigatorPicture.translateXProperty().bind(investigatorPicture.widthProperty().divide(3));
		investigatorPicture.translateYProperty().bind(investigatorPicture.widthProperty().divide(4.1));
		investigatorPicture.setRotate(-5);
		investigatorPicture.setOnMouseClicked(a->showText());
		
	
		lblHealth = new Label();
		lblHealth.styleProperty().bind(Effects.fontBig);
		lblHealth.setTextFill(Effects.fontColorRed);
		lblHealth.setAlignment(Effects.fontPos);
		lblHealth.translateXProperty().bind(picture.widthProperty().divide(1.57));
		lblHealth.translateYProperty().bind(picture.heightProperty().divide(2.15));
		lblHealth.setRotate(-5);
		
		lblSanity = new Label();
		lblSanity.styleProperty().bind(Effects.fontBig);
		lblSanity.setTextFill(Effects.fontColorBlue);
		lblSanity.setAlignment(Effects.fontPos);
		lblSanity.translateXProperty().bind(picture.widthProperty().divide(1.19));
		lblSanity.translateYProperty().bind(picture.heightProperty().divide(1.98));
		lblSanity.setRotate(7);
		
		
		lblName = new Label();
		lblName.styleProperty().bind(Effects.fontMedium);
		lblName.setTextFill(Effects.fontColorDark);
		lblName.setAlignment(Effects.fontPos);
		lblName.translateXProperty().bind(picture.widthProperty().divide(1.8));
		lblName.translateYProperty().bind(lblName.heightProperty().divide(1.5));
		lblName.setRotate(-2.5);
		
		lblOccupation = new Label();
		lblOccupation.styleProperty().bind(Effects.fontSmall);
		lblOccupation.setTextFill(Effects.fontColorDark);
		lblOccupation.setAlignment(Effects.fontPos);
		lblOccupation.translateXProperty().bind(picture.widthProperty().divide(1.6));
		lblOccupation.translateYProperty().bind(lblName.heightProperty().multiply(1.8));
		lblOccupation.setRotate(-2.5);
		
		
		lblAction = new Label();
		lblAction.styleProperty().bind(Effects.fontVerySmall);
		lblAction.setTextFill(Effects.fontColorDark);
		lblAction.setAlignment(Effects.fontPos);
		lblAction.translateXProperty().bind(picture.widthProperty().divide(1.9));
		lblAction.translateYProperty().bind(lblName.heightProperty().multiply(2.6));
		lblAction.maxWidthProperty().bind(picture.widthProperty().divide(2.5));
		lblAction.setWrapText(true);
		lblAction.setRotate(-2.5);
		
		lblAbility = new Label();
		lblAbility.styleProperty().bind(Effects.fontVerySmall);
		lblAbility.setTextFill(Effects.fontColorDark);
		lblAbility.setAlignment(Effects.fontPos);
		lblAbility.translateXProperty().bind(picture.widthProperty().divide(1.9));
		lblAbility.translateYProperty().bind(lblAction.heightProperty().multiply(1.1).add(lblAction.translateYProperty()));
		lblAbility.maxWidthProperty().bind(picture.widthProperty().divide(2.5));
		lblAbility.setWrapText(true);
		lblAbility.setRotate(-2.5);
		
		lblQuote = new Label();
		lblQuote.styleProperty().bind(Effects.fontVerySmallCursive);
		lblQuote.setTextFill(Effects.fontColorDark);
		lblQuote.setAlignment(Effects.fontPos);
		lblQuote.translateXProperty().bind(picture.widthProperty().divide(1.9));
		lblQuote.translateYProperty().bind(lblAbility.heightProperty().multiply(1.1).add(lblAbility.translateYProperty()));
		lblQuote.maxWidthProperty().bind(picture.widthProperty().divide(2.5));
		lblQuote.setWrapText(true);
		lblQuote.setRotate(-2.5);
	
		
		
		
		lblLore = new Label();
		lblLore.styleProperty().bind(Effects.fontMedium);
		lblLore.setTextFill(Effects.fontColor);
		lblLore.setAlignment(Effects.fontPos);
		
		lblInfluence = new Label();
		lblInfluence.styleProperty().bind(Effects.fontMedium);
		lblInfluence.setTextFill(Effects.fontColor);
		lblInfluence.setAlignment(Effects.fontPos);
		lblObservation = new Label();
		lblObservation.styleProperty().bind(Effects.fontMedium);
		lblObservation.setTextFill(Effects.fontColor);
		lblObservation.setAlignment(Effects.fontPos);
		lblStrength = new Label();
		lblStrength.styleProperty().bind(Effects.fontMedium);
		lblStrength.setTextFill(Effects.fontColor);
		lblStrength.setAlignment(Effects.fontPos);
		lblWill = new Label();
		lblWill.styleProperty().bind(Effects.fontMedium);
		lblWill.setTextFill(Effects.fontColor);
		lblWill.setAlignment(Effects.fontPos);
		HBox skills = new HBox(lblLore,lblInfluence,lblObservation,lblStrength,lblWill);
		skills.translateXProperty().bind(picture.widthProperty().divide(6.8));
		skills.translateYProperty().bind(picture.heightProperty().subtract(skills.heightProperty().multiply(1.8)));
		skills.spacingProperty().bind(picture.widthProperty().divide(7.5));
		skills.setRotate(1);
		this.getChildren().addAll(investigatorPicture,picture,lblName, lblHealth,lblSanity,
				lblOccupation,lblAction,lblAbility,lblQuote,skills,btnClose);
	}
	
	private void showText() {
		TextScreen textScreen = new TextScreen(true);
		textScreen.setText(investigator.getSTORY());
		((Group)StageControll.getPrimaryStage().getScene().getRoot()).getChildren().add(textScreen);
		this.getParent().setDisable(true);
		this.getParent().setEffect(Effects.blure);

	}

	public void setInvestigator(Investigator investigator2) {
		investigator=investigator2;
		investigatorPicture.setFill(new ImagePattern(investigator.getPicture()));
		
		lblName.setText(investigator.getName());
		lblHealth.setText(investigator.getMAX_HEALTH()+"");
		if(investigator.getMAX_HEALTH()== 6|| investigator.getMAX_HEALTH()==8 ){
			lblHealth.translateYProperty().bind(picture.heightProperty().divide(2.0));
		}else{
			lblHealth.translateYProperty().bind(picture.heightProperty().divide(2.15));
		}
		lblSanity.setText(investigator.getMAX_SANITY()+"");
		
		lblSanity.translateYProperty().bind(picture.heightProperty().divide(1.98));
		if(investigator.getMAX_SANITY()== 6|| investigator.getMAX_SANITY()==8 ){
			lblSanity.translateYProperty().bind(picture.heightProperty().divide(1.85));
		}else{
			lblSanity.translateYProperty().bind(picture.heightProperty().divide(1.98));
		}
		lblOccupation.setText(investigator.getOCCUPATION());
		lblAction.setText("ACTION: "+investigator.getActionText());
		lblAbility.setText(investigator.getAblitiyText());
		lblQuote.setText(investigator.getQUOTE());
		
		
		
		lblLore.setText(investigator.getLore().getValue()+"");
		lblInfluence.setText(investigator.getInfluence().getValue()+"");
		lblObservation.setText(investigator.getObservation().getValue()+"");
		lblStrength.setText(investigator.getStrength().getValue()+"");
		lblWill.setText(investigator.getWill().getValue()+"");

		
		
	}
	public OkCancelBtn getCloseButton(){
		if(btnClose.isVisible())return null;
		return btnClose;
	}

	public DoubleExpression widthProperty() {
		return picture.widthProperty();
	}
}
