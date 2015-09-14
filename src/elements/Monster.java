package elements;

import java.io.IOException;
import java.util.Map;

import gameBuild.Global;
import gameMechanics.Event;
import gameMechanics.IO;
import javafx.scene.image.Image;

public class Monster extends Card {

	private static final long serialVersionUID = 1L;
	
	private final int HORROR;
	private final int TOUGHNESS;
	private final int DAMAGE;
	private final int STRENGTH_TEST;
	private final int WILL_TEST;
	private final String TEXT;
	
	private int actualDamage;

	
	//Aktion und F�higkeit
	//Aktion f�r tot
	
	
	
	
	public Monster(String name, int horror, int toughness, int damage, int strengthTest,int willTest, Event event,String ancient){
		this.HORROR=horror;
		this.TOUGHNESS=toughness;
		this.name=name;
		this.DAMAGE=damage;
		this.STRENGTH_TEST=strengthTest;
		this.WILL_TEST=willTest;
		this.actualDamage=0;
		Map<String, String> names=IO.readText(Global.language+"/"+ancient+".txt");

		this.TEXT = names.get("cultist1");
	}
	public Monster(String name){
		Map<String, String> names= IO.readText(Global.language+"/"+name+".txt");

		this.HORROR=Integer.valueOf(names.get("horror"));
		this.TOUGHNESS=Integer.valueOf(names.get("toughness"));
		this.name=name;
		this.DAMAGE=Integer.valueOf(names.get("damage"));
		this.STRENGTH_TEST=Integer.valueOf(names.get("strengthTest"));
		this.WILL_TEST=Integer.valueOf(names.get("willTest"));
		this.TEXT=names.get("text");
		this.actualDamage=0;
	}


	public int getActualDamage() {
		return actualDamage;
	}


	public void addActualDamage(int actualDamage) {
		this.actualDamage += actualDamage;
	}


	public int getHORROR() {
		return HORROR;
	}


	public int getTOUGHNESS() {
		return TOUGHNESS;
	}


	public int getDAMAGE() {
		return DAMAGE;
	}


	public int getSTRENGTH_TEST() {
		return STRENGTH_TEST;
	}


	public int getWILL_TEST() {
		return WILL_TEST;
	}
	
	public String getTEXT() {
		return TEXT;
	}
	@Override
	public Image getPicture() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
