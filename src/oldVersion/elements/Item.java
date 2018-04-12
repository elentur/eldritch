package oldVersion.elements;

import oldVersion.enums.AssetNames;

import javafx.scene.image.Image;

public abstract class Item extends Card {


	public abstract Image getPicture();


	public abstract Image getPictureBack();


	public abstract Image getPictureSymbol();
	
	public abstract int getItemNumber();


	public abstract String getText() ;
	public abstract String getTypText() ;
}
