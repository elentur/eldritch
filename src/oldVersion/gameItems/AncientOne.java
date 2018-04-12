package oldVersion.gameItems;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import oldVersion.elements.Monster;
import oldVersion.elements.Mystery;
import oldVersion.elements.Mythos;
import oldVersion.gameBuild.Global;
import oldVersion.gameMechanics.Event;
import oldVersion.gameMechanics.IO;
import javafx.scene.image.Image;

public class AncientOne implements Serializable {

	private static final long serialVersionUID = 1L;
	private int doom;
	private String name;
	private Monster cultist1;
	private Monster cultist2;
	private Event reckoning1;
	private String reckoningText1;
	private Event reckoning2;
	private String reckoningText2;
	private String specialText;
	private String amtosphereText;
	private List<Monster> setAside;
	private Mystery finalMystery;
	private Stack<Mythos> mythosDeck;
	private Image smallPicture;
	private Image bigPicture;
	private String headline1;
	
	public AncientOne(String name){
		Class<?> clazz;
		

			
			try {
			
				clazz = Class.forName("oldVersion.ancientOnes." + name);
				
				this.doom = (int) clazz.getMethod("buildDoom").invoke(null);
				this.name = (String) clazz.getMethod("buildName").invoke(null);
				this.headline1 = (String) clazz.getMethod("buildHeadline1").invoke(null);
				this.cultist1 = (Monster) clazz.getMethod("buildCultist1").invoke(null);
				this.cultist2 = (Monster) clazz.getMethod("buildCultist2").invoke(null);
				this.reckoning1 = (Event) clazz.getMethod("buildReckoning1").invoke(null);
				this.reckoning2 = (Event) clazz.getMethod("buildReckoning2").invoke(null);
				this.reckoningText1 = (String) clazz.getMethod("buildReckoningText1").invoke(null);
				this.reckoningText2 = (String) clazz.getMethod("buildReckoningText2").invoke(null);
				this.specialText = (String) clazz.getMethod("buildSpecialText").invoke(null);
				this.amtosphereText = (String) clazz.getMethod("builAtmosphereText").invoke(null);
				this.setAside = (List<Monster>) clazz.getMethod("buildSetAside").invoke(null);
				this.finalMystery = (Mystery) clazz.getMethod("buildMystery").invoke(null);
				this.mythosDeck = (Stack<Mythos>)  clazz.getMethod("buildMythosDeck").invoke(null);
				this.smallPicture = (Image) clazz.getMethod("buildSmallPicture").invoke(null);
				this.bigPicture = (Image)  clazz.getMethod("buildBigPicture").invoke(null);
			
				
			} catch (SecurityException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		//doom = Class.forName(name).buildDoom();
	}

	public Image getSmallPicture() {
		return smallPicture;
	}

	public Image getBigPicture() {
		return bigPicture;
	}

	public void setMythosDeck(Stack<Mythos> mythosDeck) {
		this.mythosDeck = mythosDeck;
	}

	public int getDoom() {
		return doom;
	}

	
	public String getReckoningText1(){
		return reckoningText1;
	}

	public String getName() {
		return name;
	}

	public Monster getCultist1() {
		return cultist1;
	}

	public Monster getCultist2() {
		return cultist2;
	}

	public Event getReckoning1() {
		return reckoning1;
	}

	public Event getReckoning2() {
		return reckoning2;
	}

	public String getReckoningText2() {
		return reckoningText2;
	}

	public String getSpecialText() {
		return specialText;
	}

	public String getAmtosphereText() {
		return amtosphereText;
	}

	public List<Monster> getSetAside() {
		return setAside;
	}

	public Mystery getFinalMystery() {
		return finalMystery;
	}

	public Stack<Mythos> getMythosDeck() {
		return mythosDeck;
	}

	public String getHeadline1() {
		return headline1;
	}
}
