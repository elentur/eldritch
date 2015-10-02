package gui;

import java.awt.ScrollPaneAdjustable;
import java.io.IOException;
import java.util.Map;

import elements.Investigator;
import enums.Path;
import enums.Space;
import gameBuild.Global;
import gameItems.Field;
import gameMechanics.IO;
import javafx.application.Platform;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ActionScreen extends Group {
	private Label lblHeadline;
	private Rectangle picture;
	private Scene scene;
	private OkCancelBtn btnClose;
	private FlowPane flowPane;
	
	private Button btnTrain;
	private Button btnShip;
	private Button btnAsset;
	private Button btnRest;
	
	public ActionScreen( boolean CloseButton,Field field){
		Map<String,String> names = IO.readText(Global.language+"/GameScreen.txt");
		scene = StageControll.getPrimaryStage().getScene();
		
		btnClose= new OkCancelBtn(false);
		btnClose.setNode(this);
		btnClose.setVisible(CloseButton);
		Animations.startFadeIn(this);
		picture = new Rectangle();
		picture.setFill(new ImagePattern(MenueTextures.infoBox));
		picture.widthProperty().bind(scene.widthProperty().divide(2));
		picture.heightProperty().bind(scene.widthProperty().divide(2*1.3));
		picture.setMouseTransparent(true);
		this.setEffect(Effects.shadowBtn);
		
		lblHeadline = new Label(names.get("actions"));
		lblHeadline.styleProperty().bind(Effects.fontMedium);
		lblHeadline.setTextFill(Effects.fontColorDark);
		lblHeadline.setAlignment(Effects.fontPos);
		lblHeadline.translateXProperty().bind(picture.widthProperty().divide(2.3));
		lblHeadline.translateYProperty().bind(lblHeadline.heightProperty().divide(1.2));
		
		
		btnShip = new Button(names.get("shipTicket"));
		btnShip.setOnMouseClicked(a->{
			if(Global.game.getActiveInvestigator().addShipTickets()){
				Dialog.infoDialog("shipTicketBuyDiag");
			}else{
				Dialog.infoDialog("noTicketBuyDiag");
			}
			btnClose.close(this);
		});

		btnShip.setDisable(!field.getNeighbours().containsValue(Path.ship)||field.getSpace()!=Space.city);
		
		btnTrain = new Button(names.get("trainTicket"));
		btnTrain.setOnMouseClicked(a->{
			if(Global.game.getActiveInvestigator().addTrainTickets()){
				Dialog.infoDialog("trainTicketBuyDiag");
			}else{
				Dialog.infoDialog("noTicketBuyDiag");
			}
			btnClose.close(this);
		});
		btnTrain.setDisable(!field.getNeighbours().containsValue(Path.train)||field.getSpace()!=Space.city);
		
		btnAsset = new Button(names.get("assetAcquire"));
		btnAsset.setOnMouseClicked(a->{
			new ReserveGui(true);
			btnClose.close(this);
			
		});
		btnAsset.setDisable(!(field.getSpace()==Space.city&& field.getMonsters().isEmpty()));
		btnRest = new Button(names.get("rest"));
		btnRest.setOnMouseClicked(a->{
			if(Global.game.getActiveInvestigator().rest()){
				Dialog.infoDialog("restDiag");
			}else{
				Dialog.infoDialog("noRestDiag");
			}
			btnClose.close(this);
		});
		btnRest.setDisable(!field.getMonsters().isEmpty());
		
		
		flowPane=new FlowPane(btnAsset,btnRest,btnShip,btnTrain);
		flowPane.prefWidthProperty().bind(picture.widthProperty());
		flowPane.prefHeightProperty().bind(btnRest.heightProperty().multiply(2.3));
		flowPane.translateXProperty().bind(picture.widthProperty().divide(11));
		flowPane.translateYProperty().bind(picture.heightProperty().divide(3));
		
		VBox elements = new VBox(new Group(picture,lblHeadline,flowPane),btnClose);
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
	
	
	
	
	

	public OkCancelBtn getCloseButton(){
		if(btnClose.isVisible())return null;
		return btnClose;
	}

	public DoubleExpression widthProperty() {
		return picture.widthProperty();
	}
}
