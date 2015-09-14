package elements;


import enums.Omen;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Gate extends Token {

	private static final long serialVersionUID = 1L;
	Omen omen;
	
	public Gate(Omen omen){
		this.omen =omen;
	}

	@Override
	public Image getPicture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getToken() {
		// TODO Auto-generated method stub
		return null;
	}
}
