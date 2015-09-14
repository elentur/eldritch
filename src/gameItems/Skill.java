package gameItems;

import java.io.Serializable;

import enums.Skills;

public class Skill implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Skills typ;
	private int value;
	private int improvement;
	
	public Skill(Skills typ, int value){
		this.typ =typ;
		this.value=value;
		this.improvement=0;
	}
	
	public Skills getTyp(){
		return typ;
	}
	
	public int getValue(){
		return value;
	}
	
	public int getImprovment(){
		return improvement;
	}
	
	public void improve(){
		if(improvement<2) improvement++;
	}
}
