package gui;



import java.util.List;
import java.util.Set;

import elements.Investigator;
import elements.Monster;
import enums.FieldTyps;
import enums.Space;
import gameBuild.Global;
import gameItems.Field;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;

public class FieldGui extends Group {
	private Field field;
	private Rectangle nameField;
	private Rectangle nameFieldShape;
	private Label lblName;
	private Shape space;
	private FlowPane investigators;
	private FlowPane monsters;
	private HBox gate;
	private HBox clue;

	public FieldGui(Field field){
		this.field=field;
		gate = new HBox();
		clue = new HBox();
		nameField=new Rectangle();
		nameFieldShape= new Rectangle();
		lblName=new Label();
		investigators=new FlowPane(5,5);
		monsters = new FlowPane(5,5);
		field.getObservalbleInvestigators().addListener((ListChangeListener<? super Investigator>) a -> investigatorUpdate());
		field.gateProperty().addListener(a->gateChanges());
		field.getObservalbleMonsters().addListener((ListChangeListener<? super Monster>) a -> MonsterUpdate());
		field.clueProperty().addListener(a->clueChanges());
		
		investigators.setPrefWidth(230);
		monsters.setPrefWidth(230);
		monsters.translateXProperty().bind(investigators.translateXProperty());
		monsters.translateYProperty().bind(investigators.translateYProperty().add(investigators.heightProperty()));
		//investigators.setPadding(new Insets(20));
		
			
		if(field.getFieldTyp()==FieldTyps.Other){
			buildOther();
		}else if(field.getFieldTyp()==FieldTyps.City){
			buildCity();
		}else{
			buildExpedition();
		}
		
		space.setOnMouseClicked(a->{
			Set<Field> fields=Global.game.getGameBoard().nextInvestigatorField(field);
			
			for(Field f:fields){
				for(Investigator i:f.getInvestigators() ){
					field.addInvestigator(i);
				}
				f.getInvestigators().clear();
			}
			Global.game.spawnGates(1);
		});
		
		gate.setTranslateX(space.getTranslateX()+85);
		gate.setTranslateY(space.getTranslateY()-100);
		investigatorUpdate();
		gateChanges();
		clueChanges();
		MonsterUpdate();
		this.getChildren().addAll(gate,investigators,monsters,clue);
		
		
	}

	private void clueChanges() {
		if(field.getClue()==null){
			clue.getChildren().clear();
		}else{
			
			clue.getChildren().add(field.getClue().getToken());
		}

	}

	private void MonsterUpdate() {
		monsters.getChildren().clear();
		for(Monster monster: field.getMonsters()){
			monster.getFlatToken().setWidth(76.8);
			monster.getFlatToken().setHeight(52.6);
			if (!monsters.getChildren().contains(monster.getFlatToken())){
				monsters.getChildren().add(monster.getFlatToken());
			}
		}
	}

	private void gateChanges() {
		if(field.getGate()==null){
			gate.getChildren().clear();
		}else{
			
			gate.getChildren().add(field.getGate().getToken());
		}
	}

	private void investigatorUpdate() {
		investigators.getChildren().clear();
		for(Investigator inv: field.getInvestigators()){
			inv.getFlatToken().widthProperty().unbind();
			inv.getFlatToken().heightProperty().unbind();
			inv.getFlatToken().setWidth(52);
			inv.getFlatToken().setHeight(54.5);
			if (!investigators.getChildren().contains(inv.getFlatToken())){
				investigators.getChildren().add(inv.getFlatToken());
			}
			
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
		lblName.styleProperty().bind(Effects.fontGraphicButton);
		lblName.setTextFill(Effects.fontColorDark);
		lblName.setMinWidth(nameField.getWidth());
		lblName.setMinHeight(nameField.getHeight());
		//lblName.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		lblName.setAlignment(Effects.fontPos);
		lblName.setClip(nameFieldShape);
		lblName.setTextAlignment(TextAlignment.CENTER);
		lblName.setTranslateX(space.getTranslateX()+23);
		lblName.setTranslateY(space.getTranslateY()+18);
		investigators.setTranslateX(space.getTranslateX()-50);
		investigators.setTranslateY(space.getTranslateY()+26);
		clue.setTranslateX(space.getTranslateX());
		clue.setTranslateY(space.getTranslateY());
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
		lblName.styleProperty().bind(Effects.fontMedium);
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
		lblNameNote.styleProperty().bind(Effects.fontSmall);
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
		
		investigators.setTranslateX(space.getTranslateX()+50);
		investigators.setTranslateY(space.getTranslateY()-50);
		clue.setTranslateX(space.getTranslateX());
		clue.setTranslateY(space.getTranslateY()-100);
		
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
		lblName.styleProperty().bind(Effects.fontMedium);
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
			
		investigators.setTranslateX(space.getTranslateX()+50);
		investigators.setTranslateY(space.getTranslateY()-50);
		clue.setTranslateX(space.getTranslateX());
		clue.setTranslateY(space.getTranslateY()-100);
		this.getChildren().addAll(space, nameField,lblName);
	}
}
