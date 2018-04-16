package oldVersion.investigators;

import java.io.IOException;
import java.util.Map;
import oldVersion.elements.Card;
import oldVersion.elements.ClueToken;
import oldVersion.elements.Item;
import oldVersion.enums.AssetNames;
import oldVersion.enums.Skills;
import oldVersion.enums.SpellNames;
import oldVersion.exceptions.CardNotFoundException;
import oldVersion.gameBuild.Global;
import oldVersion.gameItems.Skill;
import oldVersion.gameItems.Stack;
import oldVersion.gameMechanics.IO;
import oldVersion.gui.MenueTextures;
import javafx.scene.image.Image;


public class DianaStanley {
	private final static String NAME ="DianaStanley";
	
	public static Stack<Item> buildInventory(){
		Stack<Item> inventory = new Stack<Item>(false);
		try {
			inventory.add(Global.game.getAssetDeck().drawCard(AssetNames._ArcaneManuscript));
			inventory.add(Global.game.getSpellDeck().drawCard(SpellNames._Wither));
			
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
		return "Diana Stanley";
	}
	
	
	public static Skill buildLore(){
		return new Skill(Skills.lore, 4);
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
		return new Skill(Skills.will, 1);
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
		return MenueTextures.dianaStanley;
	}
	
}