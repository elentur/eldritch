package elements;


import enums.Omen;
import gameItems.Field;
import gui.GameTextures;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Gate extends Token {

	private static final long serialVersionUID = 1L;
	private Omen omen;
	private Field field;
	
	public Gate(Field field){
		this.field = field;
		if(field.toString()=="istanbul"||
				field.toString()=="tunguska" ||
				field.toString()=="sanFrancisco" ){
			this.omen=Omen.sun;
		}else if(field.toString()=="tokyo"||
				field.toString()=="buenosAires" ||
				field.toString()=="pyramids" ||
				field.toString()=="london"){
			this.omen=Omen.starsign;
		}else{
			this.omen=Omen.sun;
		}
			
	}

	@Override
	public Image getPicture() {
		return GameTextures.gate;
	}

	
	public Circle getToken() {
		
		return new Circle(50, new ImagePattern(getPicture()));
	}
	
	public Field getField(){
		return field;
	}
	
	public String toString(){
		return getField().toString();
	}
}
