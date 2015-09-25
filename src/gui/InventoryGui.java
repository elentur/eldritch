package gui;

import elements.Investigator;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class InventoryGui extends Group {
	private FlowPane inventory;
	private Rectangle clip;

	public InventoryGui(Investigator investigator){
		Scene scene = StageControll.getPrimaryStage().getScene();
		inventory= new FlowPane( Orientation.VERTICAL,20,20,investigator.getInventory().getNodes());
		inventory.prefHeightProperty().bind(scene.widthProperty().divide(4));
		inventory.setBackground(new Background(new BackgroundImage(GameTextures.leatherBackground, null, null, null, null)));
		inventory.setEffect(Effects.innerShadow);
		clip = new Rectangle();
		clip.setFill(new ImagePattern(GameTextures.itemBackPicture));
		clip.widthProperty().bind(inventory.widthProperty());
		clip.heightProperty().bind(inventory.heightProperty());
		inventory.setClip(clip);
		this.getChildren().add(inventory);
	}
	public InventoryGui(){
		Scene scene = StageControll.getPrimaryStage().getScene();
		inventory= new FlowPane();
		inventory.setPadding(new Insets(5));
		inventory.prefHeightProperty().bind(scene.widthProperty().divide(4));
		inventory.setBackground(new Background(new BackgroundImage(GameTextures.leatherBackground, null, null, null, null)));
		inventory.setEffect(Effects.innerShadow);
		clip = new Rectangle();
		clip.setFill(new ImagePattern(GameTextures.itemBackPicture));
		clip.widthProperty().bind(inventory.widthProperty());
		clip.heightProperty().bind(inventory.heightProperty());
		inventory.setClip(clip);
		this.getChildren().add(inventory);
	}


	public void setInvestigator(Investigator inv) {
		inventory.setOrientation(Orientation.VERTICAL);
		inventory.setHgap(20);
		inventory.setVgap(20);
		inventory.getChildren().clear();
		inventory.getChildren().addAll(inv.getInventory().getNodes());
		
				
	}
}
