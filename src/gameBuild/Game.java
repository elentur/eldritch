package gameBuild;

import java.io.Serializable;

import java.util.List;

import elements.Artifact;
import elements.Asset;
import elements.ClueToken;
import elements.Condition;
import elements.Gate;
import elements.Investigator;
import elements.Spell;
import gameItems.AncientOne;
import gameItems.DoomTracker;
import gameItems.GameBoard;
import gameItems.OmenTracker;
import gameItems.Stack;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	private GameBoard gameBoard;
	private Stack<Spell> spellDeck;
	private Stack<Asset> assetDeck;
	private Stack<Artifact> artifactDeck;
	private Stack<Condition> conditionDeck;
	private Stack<Gate> gateStack;
	
	private DoomTracker doomTracker;
	private OmenTracker omenTracker;
	private AncientOne ancientOne;
	
	private Stack<ClueToken> cluePool;
	public Game( AncientOne ancientOne){
		  this.ancientOne=ancientOne;
		 this.spellDeck=Build.buildSpellDeck();
		 this.assetDeck=Build.buildAssetDeck();
		 this.artifactDeck=Build.buildArtifactDeck();
		 this.conditionDeck=Build.buildConditionDeck();
		 
		 this.cluePool=Build.buildCluePool();
		 
		 this.gateStack=Build.buildGateStack();
		 
		 this.doomTracker= new DoomTracker(ancientOne.getDoom());
		 this.omenTracker = new OmenTracker();
		 
	}
	public AncientOne getAncientOne() {
		return ancientOne;
	}
	public void setInvestigators(List<Investigator> investigators){
		this.gameBoard = Build.buildGameBoard(investigators);
		System.out.println(gameBoard.getFields());
	}
	public DoomTracker getDoomTracker() {
		return doomTracker;
	}
	public OmenTracker getOmenTracker() {
		return omenTracker;
	}
	public Stack<Gate> getGateStack() {
		return gateStack;
	}
	public Stack<Spell> getSpellDeck() {
		return spellDeck;
	}
	public Stack<Asset> getAssetDeck() {
		return assetDeck;
	}
	public Stack<Artifact> getArtifactDeck() {
		return artifactDeck;
	}
	public Stack<Condition> getConditionDeck() {
		return conditionDeck;
	}
	public Stack<ClueToken> getCluePool() {
		return cluePool;
	}
	public GameBoard getGameBoard() {
		return gameBoard;
	}
	
	
}
