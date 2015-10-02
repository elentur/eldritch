package elements;

import gameItems.Field;
import gui.GameTextures;
import gui.StageControll;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ClueToken extends Token {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Field field;
	
	public ClueToken(Field field){
		this.field = field;
	}
	
	public Field getField(){
		return field;
	}
	
	public String toString(){
		return getField().toString();
	}
	@Override
	public Image getPicture() {
		return GameTextures.clue;
	}

	@Override
	public Circle getToken() {

		return new Circle(25,new ImagePattern(getPicture()));
	}
}
