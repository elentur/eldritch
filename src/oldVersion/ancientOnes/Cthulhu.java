package oldVersion.ancientOnes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import oldVersion.elements.Monster;
import oldVersion.elements.Mystery;
import oldVersion.elements.Mythos;
import oldVersion.enums.MonsterNames;
import oldVersion.gameBuild.Global;
import oldVersion.gameItems.Stack;
import oldVersion.gameMechanics.Event;
import oldVersion.gameMechanics.IO;
import oldVersion.gui.MenueTextures;
import javafx.scene.image.Image;

import static oldVersion.enums.Events.*;

public class Cthulhu {
	private static final String NAME ="Cthulhu";
	public static int buildDoom(){
		return 12;
	}
	
	public static Monster buildCultist1(){
		Map<String,String> names=IO.readText(Global.language+"/Monster.txt");
		Monster cultist1 = new Monster(MonsterNames.cultist, 0, 2, 1, 0, 0,new Event("Bevor resolving the Strengthtest",loose,2,SANITY),"Cthulhu");
		return cultist1;
		
	}
	public static String buildName(){
		return "Cthulhu";
	}
	public static Monster buildCultist2(){
		return null;
		
	}
	public static Event buildReckoning1(){
		return null;
		
	}
	public static Event buildReckoning2(){
		return null;
		
	}
	public static String buildReckoningText1(){
		Map<String,String> names=IO.readText(Global.language+"/" + NAME + ".txt");
			return names.get("reckoningText1");
		
	}
	public static String buildReckoningText2(){
		return null;
		
	}
	public static String buildSpecialText(){
		Map<String,String> names=IO.readText(Global.language+"/" + NAME + ".txt");
			return names.get("specialText1");
		
	}
	public static String builAtmosphereText(){
		Map<String,String> names=IO.readText(Global.language+"/" + NAME + ".txt");
			return names.get("atmosphereText1");
		
	}
	public static List<Monster> buildSetAside(){
		return null;
		
	}
	public static Mystery buildMystery(){
		return null;
		
	}
	
	public static Stack<Mythos> buildMythosDeck(){
		return null;
		
	}
	public static Image buildSmallPicture(){
		return MenueTextures.cthulhu;
	}
	public static Image buildBigPicture(){
		return MenueTextures.cthulhuBig;
	}
	public static String buildHeadline1(){
		Map<String,String> names=IO.readText(Global.language+"/" + NAME + ".txt");
			return names.get("headline1");
	}

}
