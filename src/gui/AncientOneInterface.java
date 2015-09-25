package gui;

import gameItems.AncientOne;
import gameItems.DoomTracker;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class AncientOneInterface extends Group {
	
	private Rectangle graphic;
	private Rectangle ancientOnePicture;
	private Scene scene;
	private AncientOne ancientOne;
	private Rectangle graphicBack;
	private Label lblDoom;
	private DoomTracker doomTracker;
	public AncientOneInterface(AncientOne ancientOne){
		this.ancientOne=ancientOne;
		doomTracker= new DoomTracker(ancientOne.getDoom());
		scene= StageControll.getPrimaryStage().getScene();
		ancientOnePicture=new Rectangle(0,0,new ImagePattern(ancientOne.getSmallPicture()));
		ancientOnePicture.widthProperty().bind(scene.widthProperty().divide(10));
		ancientOnePicture.heightProperty().bind(scene.widthProperty().divide(10));
		ancientOnePicture.translateXProperty().bind(ancientOnePicture.widthProperty().divide(-8));
		ancientOnePicture.translateYProperty().bind(ancientOnePicture.widthProperty().divide(-6));
		ancientOnePicture.setOnMouseClicked(a->{
			AncientOneCard aoC = new AncientOneCard(true);
			aoC.setAncientOne(ancientOne);
			aoC.translateXProperty().bind(scene.widthProperty().divide(2).subtract(aoC.widthProperty().divide(2)));
			aoC.translateYProperty().bind(aoC.widthProperty().divide(8));

			
		});
		graphicBack = new Rectangle();
		graphicBack.setFill(new ImagePattern(MenueTextures.aOInterfaceBack));
		graphicBack.widthProperty().bind(scene.widthProperty().divide(6));
		graphicBack.heightProperty().bind(scene.widthProperty().divide(6));
		graphicBack.setMouseTransparent(true);
		
		
		graphic = new Rectangle();
		graphic.setFill(new ImagePattern(MenueTextures.aOInterface));
		graphic.widthProperty().bind(scene.widthProperty().divide(6));
		graphic.heightProperty().bind(scene.widthProperty().divide(6));
		graphic.setMouseTransparent(true);
		lblDoom= new Label();
		lblDoom.setAlignment(Effects.fontPos);
		lblDoom.styleProperty().bind(Effects.fontMedium);
		lblDoom.setTextFill(Effects.fontColorGreen);
		lblDoom.setMouseTransparent(true);
		lblDoom.textProperty().bind(doomTracker.getDoomProperty().asString());
		lblDoom.textProperty().addListener(a->{
			if (doomTracker.getMaxDoom() > doomTracker.actualDoom()){
				lblDoom.setTextFill(Effects.fontColorRed);
			}else{
				lblDoom.setTextFill(Effects.fontColorGreen);
			}
		});
		lblDoom.translateXProperty().bind(graphic.widthProperty().divide(1.6));
		lblDoom.translateYProperty().bind(graphic.widthProperty().divide(2.3));
		this.getChildren().addAll(graphicBack,ancientOnePicture,graphic,lblDoom);
		this.setEffect(Effects.shadowBtn);
	}
}
