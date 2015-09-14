package gui;

import elements.Monster;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MonsterToken extends Group {
	private Label lblStrengthTest;
	private Label lblDamage;
	private Label lblWillTest;
	private Label lblHorror;
	private Label lblToughness;
	private Label lblText;
	private Rectangle picture;
	private Scene scene;
	
	public MonsterToken(){
		scene = StageControll.getPrimaryStage().getScene();
		
		picture = new Rectangle();
		picture.setFill(new ImagePattern(MenueTextures.monsterBack));
		picture.widthProperty().bind(scene.widthProperty().divide(9.5));
		picture.heightProperty().bind(scene.widthProperty().divide(9.5));
		
		lblDamage=new Label();
		lblDamage.setFont(Effects.fontMedium);
		lblDamage.setTextFill(Effects.fontColorDark);
		lblDamage.setAlignment(Effects.fontPosLeft);
		lblDamage.translateXProperty().bind(picture.heightProperty().divide(2.5));
		
		lblStrengthTest=new Label();
		lblStrengthTest.setFont(Effects.fontMedium);
		lblStrengthTest.setTextFill(Effects.fontColorDark);
		lblStrengthTest.setAlignment(Effects.fontPosLeft);

		
		lblHorror=new Label();
		lblHorror.setFont(Effects.fontMedium);
		lblHorror.setTextFill(Effects.fontColorDark);
		lblHorror.setAlignment(Effects.fontPosLeft);
		lblHorror.translateXProperty().bind(picture.heightProperty().divide(2.5));
		lblHorror.translateYProperty().bind(picture.heightProperty().divide(5));
		
		
		lblWillTest=new Label();
		lblWillTest.setFont(Effects.fontMedium);
		lblWillTest.setTextFill(Effects.fontColorDark);
		lblWillTest.setAlignment(Effects.fontPosLeft);
		lblWillTest.translateYProperty().bind(picture.heightProperty().divide(5));
		
		lblToughness=new Label();
		lblToughness.setFont(Effects.font);
		lblToughness.setTextFill(Effects.fontColor);
		lblToughness.setAlignment(Effects.fontPosRight);
		lblToughness.translateXProperty().bind(picture.heightProperty().divide(1.2));
		lblToughness.translateYProperty().bind(picture.heightProperty().divide(-8));
		
		lblText=new Label();
		lblText.setFont(Effects.fontVerySmall);
		lblText.setTextFill(Effects.fontColorDark);
		lblText.setLineSpacing(-3);
		lblText.setAlignment(Effects.fontPosLeft);
		lblText.maxWidthProperty().bind(picture.widthProperty().multiply(0.8));
		lblText.minHeightProperty().bind(picture.widthProperty().multiply(1));
		//lblText.setLineSpacing(-2);
		lblText.setWrapText(true);
		lblText.translateYProperty().bind(picture.heightProperty().divide(2.2));
		lblText.translateXProperty().bind(picture.heightProperty().multiply(0.1));
		
		
		

		getChildren().addAll(picture,lblStrengthTest,lblDamage,lblWillTest,lblHorror,lblToughness,lblText);
	}

	public Label getStrengthTest() {
		return lblStrengthTest;
	}

	public void setStrengthTest(String lblStrengthTest) {
		if(lblStrengthTest.equals("0")){
			this.lblStrengthTest.setTextFill(Color.TRANSPARENT);
		}else{
			this.lblStrengthTest.setTextFill(Effects.fontColorDark);
		}
		this.lblStrengthTest.setText(lblStrengthTest);
		this.lblStrengthTest.setGraphic(new Rectangle(scene.getWidth()/70,scene.getWidth()/70,new ImagePattern(MenueTextures.strength)));
		
	}

	public Label getDamage() {
		return lblDamage;
	}

	public void setDamage(String damage) {
		if(damage.equals("0"))damage="-";
		this.lblDamage.setText(damage);
		this.lblDamage.setGraphic(new Rectangle(scene.getWidth()/70,scene.getWidth()/70,new ImagePattern(MenueTextures.health)));

	}

	public Label getWillTest() {
		return lblWillTest;
	}

	public void setWillTest(String lblWillTest) {
		if(lblWillTest.equals("0")){
			this.lblWillTest.setTextFill(Color.TRANSPARENT);
		}else{
			this.lblWillTest.setTextFill(Effects.fontColorDark);
		}
		
		this.lblWillTest.setText(lblWillTest);
		this.lblWillTest.setGraphic(new Rectangle(scene.getWidth()/70,scene.getWidth()/70,new ImagePattern(MenueTextures.will)));

	}

	public Label getHorror() {
		return lblHorror;
	}

	public void setHorror(String lblWill) {
		if(lblWill.equals("0"))lblWill="-";
		this.lblHorror.setText(lblWill);
		this.lblHorror.setGraphic(new Rectangle(scene.getWidth()/70,scene.getWidth()/70,new ImagePattern(MenueTextures.sanity)));

	}
	
	public void setText(String text) {
		this.lblText.setText(text);
	}

	public Label getToughness() {
		return lblToughness;
	}

	public void setToughness(String lblToughness) {
		this.lblToughness.setText(lblToughness);
	}

	public Rectangle getPicture() {
		return picture;
	}

	public void setPicture(Rectangle picture) {
		this.picture = picture;
	}
	
	public void setMonster(Monster monster){
		this.setHorror(monster.getHORROR()+"");
		this.setStrengthTest(monster.getSTRENGTH_TEST()+"");
		this.setDamage(monster.getDAMAGE()+"");
		this.setWillTest(monster.getWILL_TEST()+"");
		this.setToughness(monster.getTOUGHNESS()+"");
		this.setText(monster.getTEXT());
	}
}
