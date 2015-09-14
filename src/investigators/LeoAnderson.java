package investigators;

import java.io.IOException;
import java.util.Map;
import elements.Card;
import elements.ClueToken;
import elements.Item;
import enums.AssetNames;
import enums.Skills;
import exceptions.CardNotFoundException;
import gameBuild.Global;
import gameItems.Skill;
import gameItems.Stack;
import gameMechanics.IO;
import gui.MenueTextures;
import javafx.scene.image.Image;


public class LeoAnderson {
	private final static String NAME ="LeoAnderson";
	
	public static Stack<Item> buildInventory(){
		Stack<Item> inventory = new Stack<Item>(false);
		try {
			inventory.add(Global.game.getAssetDeck().drawCard(AssetNames._HiredMuscle));
		} catch (CardNotFoundException e) {	}
		
		return inventory;
	}

	
	public static int buildHealth(){
		return 6;
	}
	public static int buildSanity(){
		return 6;
	}
	public static String buildName(){
		return "Leo Anderson";
	}
	
	
	public static Skill buildLore(){
		return new Skill(Skills.lore, 2);
	}
	public static Skill buildInfluence(){
		return new Skill(Skills.influence, 2);
	}
	public static Skill buildObservation(){
		return new Skill(Skills.observation, 3);
	}
	public static Skill buildStrength(){
		return new Skill(Skills.strength, 3);
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
		return clues;
	}
	
	public static Image buildPicture(){
		return MenueTextures.leoAnderson;
	}
	
	
	
}
