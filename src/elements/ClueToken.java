package elements;

import gui.GameTextures;
import gui.StageControll;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ClueToken extends Token {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClueToken(String name){
		this.name =name;
	}

	@Override
	public Image getPicture() {
		return GameTextures.clue;
	}

	@Override
	public Rectangle getToken() {
		Rectangle clueToken = new Rectangle();
		clueToken.setFill(new ImagePattern(getPicture()));
		Scene scene= StageControll.getPrimaryStage().getScene();
		clueToken.widthProperty().bind(scene.widthProperty().divide(25.6));
		clueToken.heightProperty().bind(scene.widthProperty().divide(25.6));
		
		return clueToken;
	}
}
