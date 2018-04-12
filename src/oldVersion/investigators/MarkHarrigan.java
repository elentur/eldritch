package oldVersion.investigators;

import java.io.IOException;
import java.util.Map;
import oldVersion.elements.Card;
import oldVersion.elements.ClueToken;
import oldVersion.elements.Item;
import oldVersion.enums.AssetNames;
import oldVersion.enums.Skills;
import oldVersion.exceptions.CardNotFoundException;
import oldVersion.gameBuild.Global;
import oldVersion.gameItems.Skill;
import oldVersion.gameItems.Stack;
import oldVersion.gameMechanics.IO;
import oldVersion.gui.MenueTextures;
import javafx.scene.image.Image;


public class MarkHarrigan {
	private final static String NAME ="MarkHarrigan";
	
	public static Stack<Item> buildInventory(){
		Stack<Item> inventory = new Stack<Item>(false);
		try {
			inventory.add(Global.game.getAssetDeck().drawCard(AssetNames._38Revolver));
			inventory.add(Global.game.getAssetDeck().drawCard(AssetNames._Kerosene));
		} catch (CardNotFoundException e) {	}
		
		return inventory;
	}

	
	public static int buildHealth(){
		return 8;
	}
	public static int buildSanity(){
		return 4;
	}
	public static String buildName(){
		return "Mark Harrigan";
	}
	
	
	public static Skill buildLore(){
		return new Skill(Skills.lore, 1);
	}
	public static Skill buildInfluence(){
		return new Skill(Skills.influence, 2);
	}
	public static Skill buildObservation(){
		return new Skill(Skills.observation, 2);
	}
	public static Skill buildStrength(){
		return new Skill(Skills.strength, 4);
	}
	public static Skill buildWill(){
		return new Skill(Skills.will, 4);
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
		return MenueTextures.markHarrigan;
	}

}
