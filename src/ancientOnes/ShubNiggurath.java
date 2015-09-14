package ancientOnes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import elements.Monster;
import elements.Mystery;
import elements.Mythos;
import gameBuild.Global;
import gameItems.Stack;
import gameMechanics.Event;
import gameMechanics.IO;
import gui.MenueTextures;
import javafx.scene.image.Image;

import static enums.Events.*;

public class ShubNiggurath {
	private final static String NAME ="ShubNiggurath";
	public static int buildDoom(){
		return 13;
	}
	
	public static Monster buildCultist1(){
		Map<String,String> names=IO.readText(Global.language+"/Monster.txt");
			Monster cultist1 = new Monster(names.get("cultistName"), 0, 1, 1, -1, 0,new Event("Bevor resolving the Strengthtest",loose,2,SANITY),"ShubNiggurath");
		return cultist1;
		
	}
	public static String buildName(){
		return "Shub-Niggurath";
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
		return MenueTextures.shubNiggurath;
	}
	public static Image buildBigPicture(){
		return MenueTextures.shubNiggurathBig;
	}
	public static String buildHeadline1(){
		Map<String,String> names=IO.readText(Global.language+"/" + NAME + ".txt");
			return names.get("headline1");
	}
}
