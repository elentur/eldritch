package elements;

import java.io.Serializable;

import javafx.scene.image.Image;

public abstract class Card implements Serializable,Element {
	private static final long serialVersionUID = 1L;
	protected String name="";
	protected String typ="";
	public Card(String name){
		this.name = name;
	}
	public Card(){
		
	}
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
}
