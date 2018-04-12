package oldVersion.elements;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Token implements Element,Serializable {
	private static final long serialVersionUID = 1L;
	protected String name="";
	protected String typ="";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String toString(){
		return name;
	}
	@Override
	public abstract Image getPicture();
	public abstract Shape getToken();
}
