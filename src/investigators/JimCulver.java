package investigators;

import java.io.IOException;
import java.util.Map;
import elements.Card;
import elements.ClueToken;
import elements.Item;
import enums.Skills;
import enums.SpellNames;
import exceptions.CardNotFoundException;
import gameBuild.Global;
import gameItems.Skill;
import gameItems.Stack;
import gameMechanics.IO;
import gui.MenueTextures;
import javafx.scene.image.Image;


public class JimCulver {
	private final static String NAME ="JimCulver";
	
	public static Stack<Item> buildInventory(){
		Stack<Item> inventory = new Stack<Item>(false);
		try {
			inventory.add(Global.game.getSpellDeck().drawCard(SpellNames._Shriveling));
		} catch (CardNotFoundException e) {	}
		
		return inventory;
	}

	
	public static int buildHealth(){
		return 7;
	}
	public static int buildSanity(){
		return 5;
	}
	public static String buildName(){
		return "Jim Culver";
	}
	
	
	public static Skill buildLore(){
		return new Skill(Skills.lore, 3);
	}
	public static Skill buildInfluence(){
		return new Skill(Skills.influence, 3);
	}
	public static Skill buildObservation(){
		return new Skill(Skills.observation, 2);
	}
	public static Skill buildStrength(){
		return new Skill(Skills.strength, 2);
	}
	public static Skill buildWill(){
		return new Skill(Skills.will, 3);
	}
	
	
	public static int buildTrainTicket(){
		return 0;
	}
	public static int buildShipTicket(){
		return 0;
	}
	public static Stack<ClueToken> buildClues(){
		Stack<ClueToken> clues = new Stack<ClueToken>(false);
		try {
			clues.add(Global.game.getCluePool().drawNextCard());
		} catch (CardNotFoundException e) {}
		return clues;
	}
	
	public static Image buildPicture(){
		return MenueTextures.jimCulver;
	}
	
	

}
