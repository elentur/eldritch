package gui;

import elements.Asset;
import elements.Card;
import elements.Item;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class ItemGraphic extends Group {

	private Rectangle picture;
	private Rectangle pricePicture;
	private Rectangle namePicture;
	private Rectangle backPicture;
	private Rectangle symbolPicture;
	private Rectangle textClipNode;
	private Rectangle nameClipNode;
	private Label lblName;
	private Label lblText;
	private Label lblPrice;
	private boolean front;
	private Group frontSide;
	private Group backSide;
	public ItemGraphic(Item  card){
		this(card,false);
	}
	public ItemGraphic(Item  card,boolean showPrice){
		Scene scene= StageControll.getPrimaryStage().getScene();
		front = true;
		namePicture = new Rectangle();
		namePicture.setFill(new ImagePattern(MenueTextures.mainMenueBtn));
		namePicture.widthProperty().bind(scene.widthProperty().divide(4.15).divide(1.8));
		namePicture.heightProperty().bind(scene.widthProperty().divide(14.77).divide(1.8));
		//namePicture.setEffect(Effects.shadowBtn);
		
		nameClipNode = new Rectangle();
		nameClipNode.setFill(new ImagePattern(MenueTextures.mainMenueBtn));
		nameClipNode.widthProperty().bind(scene.widthProperty().divide(4.15).divide(1.8));
		nameClipNode.heightProperty().bind(scene.widthProperty().divide(14.77).divide(1.8));
		nameClipNode.translateYProperty().bind(namePicture.heightProperty().divide(8));
		
		picture = new Rectangle();
		picture.setFill(new ImagePattern(card.getPicture()));
		picture.widthProperty().bind(scene.widthProperty().divide(4.77).divide(2));
		picture.heightProperty().bind(scene.widthProperty().divide(9.32).divide(2));
		picture.translateXProperty().bind(namePicture.widthProperty().subtract(picture.widthProperty()).divide(2));
		picture.translateYProperty().bind(namePicture.heightProperty().add(namePicture.heightProperty().divide(10)));

		
		//pricePicture.setEffect(Effects.shadowBtn);
	

		backPicture = new Rectangle();
		backPicture.setFill(new ImagePattern(card.getPictureBack()));
		backPicture.widthProperty().bind(scene.widthProperty().divide(5.14).divide(1.45));
		backPicture.heightProperty().bind(scene.widthProperty().divide(6.81).divide(1.45));		
		
		textClipNode = new Rectangle();
		textClipNode.setFill(new ImagePattern(card.getPictureBack()));
		textClipNode.widthProperty().bind(scene.widthProperty().divide(5.14).divide(1.45));
		textClipNode.heightProperty().bind(scene.widthProperty().divide(6.81).divide(1.45));		
		
		//flipPicture.setEffect(Effects.shadowBtn);
			//backPicture.setEffect(Effects.shadowBtn);
	
		symbolPicture = new Rectangle();
		symbolPicture.setFill(new ImagePattern(card.getPictureSymbol()));
		symbolPicture.widthProperty().bind(scene.widthProperty().divide(35.55));
		symbolPicture.heightProperty().bind(scene.widthProperty().divide(38.4));
		symbolPicture.translateXProperty().bind(namePicture.widthProperty().subtract(symbolPicture.widthProperty()));
		symbolPicture.translateYProperty().bind(symbolPicture.widthProperty().divide(5));
		
		//symbolPicture.setEffect(Effects.shadowBtn);
	
	
		
		lblName=new Label(card.getName());
		lblName.setAlignment(Pos.CENTER);
		lblName.setTextFill(Effects.fontColor);
		lblName.styleProperty().bind(Effects.fontMedium2);
		lblName.setTextAlignment(TextAlignment.CENTER);
		lblName.maxWidthProperty().bind(namePicture.widthProperty().divide(2));
		lblName.minWidthProperty().bind(namePicture.widthProperty().divide(2));
		lblName.setClip(nameClipNode);
		lblName.setLineSpacing(-10);
		lblName.maxHeightProperty().bind(namePicture.heightProperty().multiply(1.1));
		lblName.minHeightProperty().bind(namePicture.heightProperty().multiply(1.1));
		//lblName.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		
		lblName.setWrapText(true);
		lblName.translateXProperty().bind(namePicture.widthProperty().divide(4));
		lblName.translateYProperty().bind(namePicture.heightProperty().divide(-8));
		
		//lblName.setOnMouseEntered(a-> Effects.highlight(a));
		//lblName.setOnMouseExited(a-> Effects.highlightOff(a));
		
		pricePicture = new Rectangle();
		lblPrice=new Label();
		if(card instanceof Asset && showPrice){
			
			pricePicture.setFill(new ImagePattern(MenueTextures.roundButton));
			pricePicture.widthProperty().bind(scene.widthProperty().divide(16.84).divide(2));
			pricePicture.heightProperty().bind(scene.widthProperty().divide(16.84).divide(2));
			pricePicture.translateYProperty().bind(pricePicture.heightProperty().divide(10));
			
			lblPrice.setText(((Asset)card).getPrice()+"");
			lblPrice.setAlignment(Effects.fontPos);
			lblPrice.setTextFill(Effects.fontColorDark);
			lblPrice.styleProperty().bind(Effects.fontMedium);
			lblPrice.maxWidthProperty().bind(pricePicture.widthProperty());
			lblPrice.minWidthProperty().bind(pricePicture.widthProperty());
			
			lblPrice.maxHeightProperty().bind(pricePicture.heightProperty());	
		}
		lblText=new Label(card.getTypText()+ "\n\n"+card.getText());
		lblText.setAlignment(Effects.fontPos);
		lblText.setTextFill(Effects.fontColorDark);
		lblText.styleProperty().bind(Effects.fontSmall);
		//lblText.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		lblText.setLineSpacing(-10);
		lblText.setPadding(new Insets(10));
		lblText.setTextAlignment(TextAlignment.CENTER);
		lblText.maxWidthProperty().bind(backPicture.widthProperty());
		lblText.minHeightProperty().bind(backPicture.heightProperty().multiply(2));
		
		lblText.maxHeightProperty().bind(backPicture.heightProperty().multiply(2));
		lblText.setWrapText(true);
		lblText.setClip(textClipNode);
		frontSide= new Group(picture,namePicture,pricePicture,symbolPicture,lblName,lblPrice);
		backSide = new Group(lblText);
		this.getChildren().addAll(backPicture,frontSide);
		this.setEffect(Effects.shadowBtn);
		this.setOnMouseClicked(a->flip(a));
	}

	private void flip(MouseEvent a) {
		if(a.getButton()==MouseButton.PRIMARY){
			front=!front;
			if (!front){
				Animations.startRotateFromTo( frontSide,backSide, this);
			}else{
				Animations.startRotateFromTo(backSide, frontSide, this);
			}

		}
	}


	public DoubleProperty widthProperty() {
		return picture.widthProperty();
	}

	public DoubleProperty heightProperty() {

		return picture.heightProperty();
	}

	
	
}
