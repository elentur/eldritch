package gui;

import elements.ClueToken;
import elements.Investigator;
import gameBuild.Global;
import gameItems.AncientOne;
import gameItems.DoomTracker;
import gameItems.Field;
import javafx.beans.Observable;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class InvestigatorInterface extends Group {
	
	private Rectangle frame;
	private Rectangle inventory;
	private Rectangle ability;
	private Circle investigatorPicture;
	private Circle sanityIndicator;
	private Circle healthIndicator;
	private Circle clueIndicator;
	private Label lblClues;
	private Label lblHealth;
	private Label lblSanity;
	private Scene scene;
	private Investigator inv;
	private Label lblName;
	private InventoryGui inventoryGui;
	private VBox tickets;
	

	
	public InvestigatorInterface(){
		scene= StageControll.getPrimaryStage().getScene();
		Global.game.getRound().activInvestigatorProperty().addListener(a->updateInvestigator(a));
		investigatorPicture=new Circle();
		frame=new Rectangle(0,0,new ImagePattern(GameTextures.investigatorFrame));
		frame.widthProperty().bind(scene.widthProperty().divide(6.4));
		frame.heightProperty().bind(scene.widthProperty().divide(6.63));
		frame.setMouseTransparent(true);
		investigatorPicture.radiusProperty().bind(scene.widthProperty().divide(16));
		investigatorPicture.setOnMouseClicked(a->pictureButton(a));	
		
		
		
		inventory=new Rectangle(0,0, new ImagePattern(GameTextures.suitCase));
		inventory.widthProperty().bind(frame.widthProperty().divide(3).multiply(1.6));
		inventory.heightProperty().bind(frame.widthProperty().divide(3));
		inventory.translateXProperty().bind(frame.widthProperty().multiply(0.8));
		inventory.translateYProperty().bind(frame.heightProperty().subtract(inventory.heightProperty()));
		inventory.setOnMouseEntered(a-> Effects.highlight(a));
		inventory.setOnMouseExited(a-> Effects.highlightOff(a));
		inventoryGui= new InventoryGui();
		inventoryGui.translateXProperty().bind(inventory.translateXProperty().add(inventory.widthProperty()));
		inventoryGui.translateYProperty().bind(frame.translateYProperty().subtract(inventory.widthProperty()));
		
		inventory.setOnMouseClicked(a->{
			if(this.getChildren().contains(inventoryGui)){
				//this.getChildren().remove(inventoryGui);
				Animations.inventoryOut(inventoryGui.getChildren().get(0));

			}else{
				inventoryGui.setInvestigator(inv);
				this.getChildren().add(inventoryGui);
				Animations.inventoryIn(inventoryGui.getChildren().get(0));
			}
		});
		
		ability=new Rectangle(0,0, new ImagePattern(GameTextures.passport));
		ability.widthProperty().bind(frame.widthProperty().divide(3));
		ability.heightProperty().bind(frame.widthProperty().divide(3));
		ability.translateXProperty().bind(frame.widthProperty().divide(1.1));
		ability.translateYProperty().bind(inventory.translateYProperty().subtract(ability.heightProperty().multiply(0.8)));
		ability.setRotate(5);
		ability.setOnMouseEntered(a-> Effects.highlight(a));
		ability.setOnMouseExited(a-> Effects.highlightOff(a));
		ability.setOnMouseClicked(a->{
			Global.game.getRound().next();
		});	
		
		lblName = new Label();
		lblName.setAlignment(Effects.fontPos);
		lblName.setTextFill(Effects.fontColorDark);
		lblName.styleProperty().bind(Effects.fontGraphicButton);
		lblName.prefWidthProperty().bind(frame.widthProperty());
		lblName.setTextAlignment(TextAlignment.CENTER);
		lblName.translateYProperty().bind(this.heightProperty().subtract(lblName.heightProperty().multiply(1.5)));
		
		investigatorPicture.translateXProperty().bind(frame.widthProperty().divide(2));
		investigatorPicture.translateYProperty().bind(frame.heightProperty().divide(2));
	
		healthIndicator= new Circle();
		healthIndicator.setFill(new ImagePattern(GameTextures.healthIndicator));
		healthIndicator.radiusProperty().bind(frame.widthProperty().divide(8));
		healthIndicator.translateXProperty().bind(healthIndicator.radiusProperty().multiply(2.1));
		healthIndicator.translateYProperty().bind(healthIndicator.radiusProperty().multiply(0.4));
		lblHealth = new Label();
		
		lblHealth.translateXProperty().bind(healthIndicator.translateXProperty().subtract(healthIndicator.radiusProperty()));
		lblHealth.translateYProperty().bind(healthIndicator.translateYProperty().subtract(healthIndicator.radiusProperty()));
		lblHealth.prefWidthProperty().bind(healthIndicator.radiusProperty().multiply(2));
		lblHealth.styleProperty().bind(Effects.fontMedium);
		lblHealth.setTextAlignment(TextAlignment.CENTER);
		lblHealth.setAlignment(Effects.fontPos);
		lblHealth.setTextFill(Effects.fontColor);
	
		sanityIndicator= new Circle();
		sanityIndicator.setFill(new ImagePattern(GameTextures.sanityIndicator));
		sanityIndicator.radiusProperty().bind(frame.widthProperty().divide(8));
		sanityIndicator.translateXProperty().bind(sanityIndicator.radiusProperty());
		sanityIndicator.translateYProperty().bind(sanityIndicator.radiusProperty().multiply(2));
		lblSanity = new Label();
		
		lblSanity.translateXProperty().bind(sanityIndicator.translateXProperty().subtract(healthIndicator.radiusProperty()));
		lblSanity.translateYProperty().bind(sanityIndicator.translateYProperty().subtract(healthIndicator.radiusProperty()));
		lblSanity.prefWidthProperty().bind(healthIndicator.radiusProperty().multiply(2));
		lblSanity.styleProperty().bind(Effects.fontMedium);
		lblSanity.setTextAlignment(TextAlignment.CENTER);
		lblSanity.setAlignment(Effects.fontPos);
		lblSanity.setTextFill(Effects.fontColor);
		
		
		clueIndicator= new Circle();
		clueIndicator.setFill(new ImagePattern(GameTextures.clueIndicator));
		clueIndicator.radiusProperty().bind(frame.widthProperty().divide(8));
		clueIndicator.translateXProperty().bind(sanityIndicator.radiusProperty());
		clueIndicator.translateYProperty().bind(sanityIndicator.radiusProperty().multiply(5));
		lblClues = new Label();
		lblClues = new Label();
		lblClues.translateXProperty().bind(clueIndicator.translateXProperty().subtract(healthIndicator.radiusProperty()));
		lblClues.translateYProperty().bind(clueIndicator.translateYProperty().subtract(healthIndicator.radiusProperty()));
		lblClues.prefWidthProperty().bind(healthIndicator.radiusProperty().multiply(2));
		lblClues.styleProperty().bind(Effects.fontMedium);
		lblClues.setTextAlignment(TextAlignment.CENTER);
		lblClues.setAlignment(Effects.fontPos);
		lblClues.setTextFill(Effects.fontColor);
		lblClues.setEffect(Effects.textShadow);
	
		
		//frame.translateXProperty().bind(InvestigatorPicture.widthProperty().divide(-8));
		//frame.translateYProperty().bind(InvestigatorPicture.widthProperty().divide(-6));
	
		
		tickets = new VBox();
		tickets.translateXProperty().bind(frame.heightProperty().divide(1.5));
		//tickets.translateYProperty().bind(frame.heightProperty());
		
		updateInvestigator(Global.game.getRound().activInvestigatorProperty());
		lblSanity.textProperty().bind(inv.sanityProperty().asString());
		lblHealth.textProperty().bind(inv.healthProperty().asString());
		Group frameElements = new Group(frame,lblName,clueIndicator,healthIndicator,sanityIndicator,
				lblClues,lblHealth,lblSanity,tickets);
		frameElements.setEffect(Effects.shadowBtn);
		this.getChildren().addAll(ability,inventory, investigatorPicture,frameElements);
		this.setEffect(Effects.shadowBtn);
	}


	private void pictureButton(MouseEvent a) {
		if(a.getButton()==MouseButton.PRIMARY){
			Field field = Global.game.getGameBoard().getInvestigatorField(
					Global.game.getActiveInvestigator());
			Animations.moveToMap(field.getPosition().getX(), field.getPosition().getY());

		}else if(a.getButton()==MouseButton.SECONDARY){
			InvestigatorSheet invSheet = new InvestigatorSheet(true);
			invSheet.setInvestigator(inv);
			invSheet.translateXProperty().bind(scene.widthProperty().divide(2).subtract(invSheet.widthProperty().divide(2)));
			invSheet.translateYProperty().bind(invSheet.widthProperty().divide(8));

		}
	}


	private void updateInvestigator(Observable a) {
		this.getChildren().remove(inventoryGui);
		inv = Global.game.getInvestigators().get(((IntegerProperty)a).get());
		
		investigatorPicture.setFill(new ImagePattern( inv.getPicture()));
		lblName.setText(inv.getName());
		lblClues.setText(inv.getClues().getSize()+"");
		//lblHealth.setText(inv.getHealth()+"");
		//lblSanity.setText(inv.getSanity()+"");
		inv.shipTicketProperty().addListener(b->updateTickets());
		inv.trainTicketProperty().addListener(b->updateTickets());
		updateTickets();
		
	}


	private void updateTickets() {
		tickets.getChildren().clear();
		for(int i = 0 ; i< inv.getTrainTickets();i++){
			Rectangle ticket=new Rectangle(0,0,new ImagePattern(GameTextures.trainTicket));
			ticket.widthProperty().bind(scene.widthProperty().divide(19.51));
			ticket.heightProperty().bind(scene.widthProperty().divide(39.18));
			ticket.setRotate(-45);
			tickets.getChildren().add(ticket);
		}
		for(int i = 0 ; i< inv.getShipTickets();i++){
			Rectangle ticket=new Rectangle(0,0,new ImagePattern(GameTextures.shipTicket));
			ticket.widthProperty().bind(scene.widthProperty().divide(19.51));
			ticket.heightProperty().bind(scene.widthProperty().divide(39.18));
			ticket.setRotate(-45);
			tickets.getChildren().add(ticket);
		}
	}


	public DoubleProperty heightProperty() {
		return frame.heightProperty();
	}
}
