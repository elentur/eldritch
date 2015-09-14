package gameItems;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DoomTracker implements Serializable  {
	private IntegerProperty doomCount;
	private int maxDoom;
	private static final long serialVersionUID = 1L;
	
	public DoomTracker(int startDoom){
		doomCount = new SimpleIntegerProperty(startDoom);
		maxDoom = startDoom;
	}
	public int advanceDoom(){
		if(doomCount.get()<20)doomCount.add(1);
		return doomCount.get();
		
	}
	public int retrieveDoom(){
		if(doomCount.get()>0)doomCount.subtract(1);
		return doomCount.get();
	}
	
	public int actualDoom(){
		return doomCount.get();
	}
	
	public IntegerProperty getDoomProperty(){
		return doomCount;
	}
	
	public int getMaxDoom(){
		return maxDoom;
	}
}
