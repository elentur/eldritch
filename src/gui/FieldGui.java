package gui;



import enums.FieldTyps;
import enums.Space;
import gameItems.Field;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;

public class FieldGui extends Group {
Field field;
Rectangle nameField;
Rectangle nameFieldShape;
Label lblName;
Shape space;
	public FieldGui(Field field){
		this.field=field;
		nameField=new Rectangle();
		nameFieldShape= new Rectangle();
		lblName=new Label();
		if(field.getFieldTyp()==FieldTyps.Other){
			buildOther();
		}else if(field.getFieldTyp()==FieldTyps.City){
			buildCity();
		}else{
			buildExpedition();
		}
	}

	private void buildOther() {
		space = new Circle(30);
		if(field.getSpace()==Space.city){
			space.setFill(new ImagePattern(GameTextures.citySpace));
		}else if(field.getSpace()==Space.wilderness){
			space.setFill(new ImagePattern(GameTextures.wildernessSpace));
		}else{
			space.setFill(new ImagePattern(GameTextures.seaSpace));
		}
		
		space.setTranslateX(field.getPosition().getX());
		space.setTranslateY(field.getPosition().getY());
		space.setOnMouseEntered(a-> Effects.highlightSpaces(a));
		space.setOnMouseExited(a-> Effects.highlightOff(a));
	
		nameField.setFill(new ImagePattern(GameTextures.mark));
		nameField.setWidth(33);
		nameField.setHeight(29);
		nameField.setTranslateX(space.getTranslateX()+23);
		nameField.setTranslateY(space.getTranslateY()+18);
		
		nameFieldShape.setFill(new ImagePattern(GameTextures.mark));
		nameFieldShape.setWidth(nameField.getWidth());
		nameFieldShape.setHeight(nameField.getHeight());
		
	
		lblName.setText(field.getName());
		lblName.setFont(Effects.fontGraphicButton);
		lblName.setTextFill(Effects.fontColorDark);
		lblName.setMinWidth(nameField.getWidth());
		lblName.setMinHeight(nameField.getHeight());
		//lblName.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		lblName.setAlignment(Effects.fontPos);
		lblName.setClip(nameFieldShape);
		lblName.setTextAlignment(TextAlignment.CENTER);
		lblName.setTranslateX(space.getTranslateX()+23);
		lblName.setTranslateY(space.getTranslateY()+18);
		
		this.getChildren().addAll(space, nameField,lblName);
	}
	
	private void buildCity() {
		space = new Rectangle(259,77);
		space.setFill(new ImagePattern(GameTextures.cityMark));
		
		space.setTranslateX(field.getPosition().getX());
		space.setTranslateY(field.getPosition().getY());
		space.setPickOnBounds(true);
		space.setOnMouseEntered(a-> Effects.highlightSpaces(a));
		space.setOnMouseExited(a-> Effects.highlightOff(a));
	
		
		nameFieldShape.setFill(new ImagePattern(GameTextures.cityMark));
		nameFieldShape.setWidth(((Rectangle)space).getWidth());
		nameFieldShape.setHeight(((Rectangle)space).getHeight());
		
	
		lblName.setText(field.getName());
		lblName.setFont(Effects.fontMedium);
		lblName.setTextFill(Effects.fontColorDark);
		lblName.setMinWidth(((Rectangle)space).getWidth());
		lblName.setMinHeight(((Rectangle)space).getHeight()/1.5);
		//lblName.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		lblName.setAlignment(Pos.CENTER);
		lblName.setClip(nameFieldShape);
		lblName.setTextAlignment(TextAlignment.CENTER);
		lblName.setTranslateX(space.getTranslateX()+20);
		lblName.setTranslateY(space.getTranslateY());
		lblName.setMouseTransparent(true);
		
		Label lblNameNote = new Label();
		lblNameNote.setText(field.getNameNote());
		lblNameNote.setFont(Effects.fontSmall);
		lblNameNote.setTextFill(Effects.fontColorDark);
		lblNameNote.setMinWidth(((Rectangle)space).getWidth());
		lblNameNote.setMinHeight(((Rectangle)space).getHeight());
		//lblName.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		lblNameNote.setAlignment(Pos.CENTER);
		//lblNameNote.setClip(nameFieldShape);
		lblNameNote.setTextAlignment(TextAlignment.CENTER);
		lblNameNote.setTranslateX(lblName.getTranslateX()-10);
		lblNameNote.setTranslateY(space.getTranslateY()+20);
		lblNameNote.setMouseTransparent(true);
		
		
		this.getChildren().addAll(space, nameField,lblName,lblNameNote);
	}

	private void buildExpedition() {
		
		if(field.getSpace()==Space.sea){
			space = new Rectangle(279,76);
			space.setFill(new ImagePattern(GameTextures.expeditionMarkSea));
		}else{
			space = new Rectangle(279,85);
			space.setFill(new ImagePattern(GameTextures.expeditionMarkWilderness));
		}
		
		
		space.setTranslateX(field.getPosition().getX());
		space.setTranslateY(field.getPosition().getY());
		space.setPickOnBounds(true);
		space.setOnMouseEntered(a-> Effects.highlightSpaces(a));
		space.setOnMouseExited(a-> Effects.highlightOff(a));
	
		
		nameFieldShape.setFill(new ImagePattern(GameTextures.expeditionMarkSea));
		nameFieldShape.setWidth(((Rectangle)space).getWidth());
		nameFieldShape.setHeight(((Rectangle)space).getHeight());
		
	
		lblName.setText(field.getName());
		lblName.setFont(Effects.fontMedium);
		lblName.setTextFill(Effects.fontColorDark);
		lblName.setMinWidth(((Rectangle)space).getWidth());
		lblName.setMinHeight(((Rectangle)space).getHeight()/1.5);
		//lblName.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		lblName.setAlignment(Pos.CENTER);
		lblName.setClip(nameFieldShape);
		lblName.setTextAlignment(TextAlignment.CENTER);
		lblName.setTranslateX(space.getTranslateX()+20);
		lblName.setTranslateY(space.getTranslateY()+10);
		lblName.setMouseTransparent(true);
		lblName.setRotate(-1);
			
		
		this.getChildren().addAll(space, nameField,lblName);
	}
}
