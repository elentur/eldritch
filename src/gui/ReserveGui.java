package gui;

import java.awt.ScrollPaneAdjustable;
import java.io.IOException;
import java.util.Map;

import elements.Investigator;
import enums.Actions;
import enums.Path;
import enums.Skills;
import enums.Space;
import gameBuild.Global;
import gameItems.Field;
import gameMechanics.IO;
import gameMechanics.TextAppearsTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ReserveGui extends Group {
	private Label lblHeadline;
	private Label lblSuccsess;
	private Rectangle picture;
	private Scene scene;
	private OkCancelBtn btnClose;
	private OkCancelBtn btnAccept;
	private FlowPane inventory;
	private DiceGui diceGui;
	private Map<String,String> names;
	
	private Button btnRollDice;
	
	public ReserveGui( boolean CloseButton){
		 names = IO.readText(Global.language+"/GameScreen.txt");
		scene = StageControll.getPrimaryStage().getScene();
		
		btnClose= new OkCancelBtn(false);
		btnClose.setNode(this);
		btnClose.setVisible(CloseButton);
		btnAccept= new OkCancelBtn(true);
		btnAccept.setNode(this);
		btnAccept.setVisible(CloseButton);
		btnAccept.addEventHandler(MouseEvent.MOUSE_CLICKED, a->{
			Global.game.getReserve().refill();
			Global.game.getActiveInvestigator().accquireAssets();
		});
		Animations.startFadeIn(this);
		picture = new Rectangle();
		picture.setFill(new ImagePattern(MenueTextures.infoBox));
		picture.widthProperty().bind(scene.widthProperty().divide(2));
		picture.heightProperty().bind(scene.widthProperty().divide(2*1.3));
		picture.setMouseTransparent(true);
		this.setEffect(Effects.shadowBtn);
		
		lblHeadline = new Label(names.get("reserve"));
		lblHeadline.styleProperty().bind(Effects.fontMedium);
		lblHeadline.setTextFill(Effects.fontColorDark);
		lblHeadline.setAlignment(Effects.fontPos);
		lblHeadline.translateXProperty().bind(picture.widthProperty().divide(2.3));
		lblHeadline.translateYProperty().bind(lblHeadline.heightProperty().divide(1.2));
		
			Node[] nodes = Global.game.getReserve().getNodes();
		Scene scene = StageControll.getPrimaryStage().getScene();
		inventory= new FlowPane( Orientation.VERTICAL,20,20,nodes);
		inventory.prefHeightProperty().bind(picture.heightProperty().divide(1.7));
		inventory.maxWidthProperty().bind(picture.widthProperty().divide(2.5));
		//inventory.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		inventory.translateXProperty().bind(picture.widthProperty().divide(5));
		inventory.translateYProperty().bind(picture.heightProperty().divide(6));
		//inventory.setEffect(Effects.innerShadow);
		int i=0;
		for (Node node : nodes){
			final int b=i;
			node.addEventFilter(MouseEvent.MOUSE_CLICKED, a->buy(a,b));
			i++;
		}
	
		diceGui= new DiceGui(Skills.influence, Global.game.getActiveInvestigator());
		
		lblSuccsess = new Label();
		lblSuccsess.textProperty().bind(Bindings.concat(names.get("succsess"),diceGui.succsessProperty()));
		lblSuccsess.styleProperty().bind(Effects.fontMedium);
		lblSuccsess.setTextFill(Effects.fontColorDark);
		lblSuccsess.setAlignment(Effects.fontPos);
		lblSuccsess.translateXProperty().bind(picture.widthProperty().divide(2.3));
		lblSuccsess.translateYProperty().bind(picture.heightProperty().subtract(lblHeadline.heightProperty().multiply(2)));
		
	
//		VBox invDice =new VBox(,diceGui);
//		invDice.setAlignment(Effects.fontPos);
//		invDice.prefWidthProperty().bind(picture.widthProperty());
//		invDice.translateYProperty().bind(picture.heightProperty().divide(6));
		VBox elements = new VBox(new Group(picture,lblHeadline,inventory,lblSuccsess),btnClose,diceGui);
		elements.setAlignment(Effects.fontPos);
		diceGui.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, a->{
			System.out.println(!Global.game.getActiveInvestigator().getActions().contains(Actions.acquire_Assets)&&
					Global.game.getActiveInvestigator().getActions().size()<2);
			if(!Global.game.getActiveInvestigator().getActions().contains(Actions.acquire_Assets)&&
					Global.game.getActiveInvestigator().getActions().size()<2){
				elements.getChildren().remove(btnClose);
				elements.getChildren().remove(diceGui);
				elements.getChildren().addAll(btnAccept,diceGui);
			}else{
				Global.textAppearsTransitionList.add(new TextAppearsTransition(names.get("noReserve"), Effects.fontColor));
				diceGui.setClose();
			}
			
		});

		
		this.getChildren().addAll(elements);
		this.translateXProperty().bind(scene.widthProperty().subtract(this.widthProperty()).divide(2));
		this.translateYProperty().bind(scene.heightProperty().divide(10));
		
		Global.overlay.getChildren().add(this);
		if(CloseButton){
			Global.screen.getChildren().get(0).setDisable(true);
			Global.screen.getChildren().get(0).setEffect(Effects.blure);
		}
	}
	
	
	
	
	

	private void buy(MouseEvent a,int index) {
		if(a.getButton() == MouseButton.SECONDARY && diceGui.isRolled()){
			diceGui.setClose();
			Node node = (Node)a.getSource();
			if(Global.game.getReserve().showAssets()[index].getPrice()<= diceGui.succsessProperty().get()){
				diceGui.succsessProperty().set(diceGui.succsessProperty().get()-Global.game.getReserve().showAssets()[index].getPrice());
				Global.game.getActiveInvestigator().addToInventory(Global.game.getReserve().getAsset(index));
				inventory.getChildren().remove(node);
			}else{
				Global.textAppearsTransitionList.add(new TextAppearsTransition(names.get("noSuccsess"), Effects.fontColor));
				
			}
		}
		
	}






	public OkCancelBtn getCloseButton(){
		if(btnClose.isVisible())return null;
		return btnClose;
	}

	public DoubleExpression widthProperty() {
		return picture.widthProperty();
	}
}
