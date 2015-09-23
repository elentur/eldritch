package elements;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import enums.MonsterNames;
import enums.SpellNames;
import gameBuild.BuildMonsters;
import gameBuild.BuildSpells;
import gameBuild.Global;
import gameMechanics.Event;
import gameMechanics.IO;
import gui.MenueTextures;
import gui.StageControll;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Monster extends Card {

	private static final long serialVersionUID = 1L;
	
	private final int HORROR;
	private final int TOUGHNESS;
	private final int DAMAGE;
	private final int STRENGTH_TEST;
	private final int WILL_TEST;
	private MonsterNames name;
	private int actualDamage;
	Map<String, String> names;
	private Event event;
	private String ancient;

	private Rectangle flatToken;


	
	//Aktion und F�higkeit
	//Aktion f�r tot
	
	
	
	
	public Monster(MonsterNames name, int horror, int toughness, int damage, int strengthTest,int willTest, Event event,String ancient){
		names=IO.readText(Global.language+"/Monster.txt");
		this.HORROR=horror;
		this.TOUGHNESS=toughness;
		this.name=name;
		this.DAMAGE=damage;
		this.STRENGTH_TEST=strengthTest;
		this.WILL_TEST=willTest;
		this.actualDamage=0;
		this.event=event;
		this.ancient=ancient;
		setflatToken();
			}

	private void setflatToken() {
		this.flatToken= new Rectangle(0,0,new ImagePattern(getPicture()));
		this.flatToken.setStrokeWidth(1);
		this.flatToken.setStrokeType(StrokeType.INSIDE);
		this.flatToken.setStroke(Color.RED);
		
	
	}

	public Monster(MonsterNames name) {
		this.name=name;
		names=IO.readText(Global.language+"/Monster.txt");
		
		this.HORROR=BuildMonsters.getHorror(name);
		this.TOUGHNESS=BuildMonsters.getToughness(name);
		this.DAMAGE=BuildMonsters.getDamage(name);
		this.STRENGTH_TEST=BuildMonsters.getStrengthTest(name);
		this.WILL_TEST=BuildMonsters.getWillTest(name);
		this.actualDamage=0;
		this.event=BuildMonsters.getEvent(name);
		setflatToken();	}

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
		if (name== MonsterNames.cultist){
			Map<String, String> names=IO.readText(Global.language+"/"+ancient+".txt");

			return names.get("cultist1");
		
		}else{
			return this.names.get(name.toString()+"Text");	
		}
			
		
	}
	@Override
	public Image getPicture() {
		try {
			Class<?> textures=Class.forName("gui.GameTextures");
				Field f=textures.getField(name.toString());
			
			return (Image)f.get(null);
			} catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException | NoSuchFieldException | SecurityException e) {
				System.out.println("Fehler in Monster.getPicture()");
			}
			return MenueTextures.akachiOnyele;
	}
	
	public Rectangle getFlatToken(){
		return flatToken;
	}

	public String getName(){
		return this.names.get(name.toString()+"Name");
	}
	
	public String toString(){
		String s = "";
		s=s+"Name: " + getName() +"\n";
		s=s+"Will-test: " + getWILL_TEST() +"  ";
		s=s+"Horror: " + getHORROR() +"\n";
		s=s+"Strength-test: " + getSTRENGTH_TEST() +"  ";
		s=s+"damage: " + getDAMAGE() +"\n";
		s=s+"Toghness: " + getTOUGHNESS() +"\n";
		s=s+"Text: " + getTEXT() +"\n\n";
		return s;
	}


	
}
