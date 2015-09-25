package gameBuild;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import elements.AmericaEncounter;
import elements.Artifact;
import elements.AsiaEncounter;
import elements.Asset;
import elements.ClueToken;
import elements.Condition;
import elements.DefaultEncounter;
import elements.EuropeEncounter;
import elements.Expedition;
import elements.Gate;
import elements.Investigator;
import elements.Monster;
import elements.Mystery;
import elements.Mythos;
import elements.OtherWorld;
import elements.Research;
import elements.Special;
import elements.Spell;
import exceptions.CardNotFoundException;
import gameItems.AncientOne;
import gameItems.Dice;
import gameItems.DoomTracker;
import gameItems.Field;
import gameItems.GameBoard;
import gameItems.OmenTracker;
import gameItems.Reserve;
import gameItems.Round;
import gameItems.Stack;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	private GameBoard gameBoard;
	private Dice dice;
	private Reserve reserve;
	private Round round;
	private List<Investigator> investigators;

	private Stack<Spell> spellDeck;
	private Stack<Asset> assetDeck;
	private Stack<Artifact> artifactDeck;
	private Stack<Condition> conditionDeck;
	private Stack<Gate> gateStack;
	private Stack<Mythos> mythosDeck;
	private Stack<Mystery> mysterieDeck;
	private Stack<Research> researchDeck;
	private Stack<Expedition> expeditionDeck;
	private Stack<Special> specialDeck;
	private Stack<OtherWorld> otherWorldDeck;
	private Stack<AmericaEncounter> americaEncounterDeck;
	private Stack<EuropeEncounter> europeEncounterDeck;
	private Stack<AsiaEncounter> asiaEncounterDeck;
	private Stack<DefaultEncounter> defaultEncounterDeck;

	private DoomTracker doomTracker;
	private OmenTracker omenTracker;
	private AncientOne ancientOne;

	private Stack<ClueToken> cluePool;
	private Stack<Monster> monsterPool;

	public Game(AncientOne ancientOne) {
		this.ancientOne = ancientOne;

		this.spellDeck = Build.buildSpellDeck();
		this.assetDeck = Build.buildAssetDeck();
		this.artifactDeck = Build.buildArtifactDeck();
		this.conditionDeck = Build.buildConditionDeck();

		this.mysterieDeck = Build.buildMysteryDeck(ancientOne);
		this.researchDeck = Build.buildResearchDeck(ancientOne);
		this.specialDeck = Build.buildSpecialDeck(ancientOne);
		this.mythosDeck = ancientOne.getMythosDeck();
		this.expeditionDeck = Build.buildExpeditionDeck();
		this.otherWorldDeck = Build.buildOtherWorldDeck();
		this.americaEncounterDeck = Build.buildAmeriacEncounterDeck();
		this.europeEncounterDeck = Build.buildEuropeEncounterDeck();
		this.asiaEncounterDeck = Build.buildAsiaEncounterDeck();
		this.defaultEncounterDeck = Build.buildDefaultEncounterDeck();

		this.gameBoard = Build.buildGameBoard();
		this.gateStack = Build.buildGateStack(this.gameBoard);
		this.cluePool = Build.buildCluePool(this.gameBoard);

		this.monsterPool = Build.buildMonsterPool(ancientOne);

		this.doomTracker = new DoomTracker(ancientOne.getDoom());
		this.omenTracker = new OmenTracker();
		this.dice = new Dice();
		this.reserve=new Reserve(assetDeck);
		
		
		

	}

	public Dice getDice() {
		return dice;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public Round getRound() {
		return round;
	}

	public Stack<Mythos> getMythosDeck() {
		return mythosDeck;
	}

	public Stack<Mystery> getMysterieDeck() {
		return mysterieDeck;
	}

	public Stack<Research> getResearchDeck() {
		return researchDeck;
	}

	public Stack<Expedition> getExpeditionDeck() {
		return expeditionDeck;
	}

	public Stack<Special> getSpecialDeck() {
		return specialDeck;
	}

	public Stack<OtherWorld> getOtherWorldDeck() {
		return otherWorldDeck;
	}

	public Stack<AmericaEncounter> getAmericaEncounterDeck() {
		return americaEncounterDeck;
	}

	public Stack<EuropeEncounter> getEuropeEncounterDeck() {
		return europeEncounterDeck;
	}

	public Stack<AsiaEncounter> getAsiaEncounterDeck() {
		return asiaEncounterDeck;
	}

	public Stack<DefaultEncounter> getDefaultEncounterDeck() {
		return defaultEncounterDeck;
	}

	public Stack<Monster> getMonsterPool() {
		return monsterPool;
	}

	public AncientOne getAncientOne() {
		return ancientOne;
	}

	public void setInvestigators(List<Investigator> investigators) {
		this.investigators=investigators;
		for (Investigator investigator : investigators) {
			try {
				if (investigator.getName() == "Charlie Kane")
					gameBoard.getField("sanFrancisco").addInvestigator(investigator);
				if (investigator.getName() == "Akachi Onyele")
					gameBoard.getField("15").addInvestigator(investigator);
				if (investigator.getName() == "Diana Stanley")
					gameBoard.getField("7").addInvestigator(investigator);
				if (investigator.getName() == "Jacqueline Fine")
					gameBoard.getField("5").addInvestigator(investigator);
				if (investigator.getName() == "Jim Culver")
					gameBoard.getField("6").addInvestigator(investigator);
				if (investigator.getName() == "Leo Anderson")
					gameBoard.getField("buenosAires").addInvestigator(investigator);
				if (investigator.getName() == "Lily Chen")
					gameBoard.getField("shanghai").addInvestigator(investigator);
				if (investigator.getName() == "Lola Hayes")
					gameBoard.getField("tokyo").addInvestigator(investigator);
				if (investigator.getName() == "Mark Harrigan")
					gameBoard.getField("14").addInvestigator(investigator);
				if (investigator.getName() == "Norman Withers")
					gameBoard.getField("arkham").addInvestigator(investigator);
				if (investigator.getName() == "Silas Marsh")
					gameBoard.getField("sydney").addInvestigator(investigator);
				if (investigator.getName() == "Trish Scarborough")
					gameBoard.getField("16").addInvestigator(investigator);
			} catch (Exception e) {
				System.out.println("Game.setInvestigator Feldname falsch");
			}
		}
		this.round = new Round(investigators.size());
		spawnGates(round.getGateNumber());
		spawnClues(round.getClueNumber());

	}

	public List<Investigator> getInvestigators() {
		return investigators;
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

	public List<Field> spawnGates(int number) {
		Gate gate = null;
		List<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < number; i++) {
			try {
				gate = getGateStack().drawNextCard();
				gate.getField().setGate(gate);
				gate.getField().addMonster(monsterPool.drawNextCard());
			} catch (CardNotFoundException e) {
				System.out.println("Game.spawnGates");
			}
			fields.add(gate.getField());
		}
		return fields;
	}

	public List<Field> spawnClues(int number) {
		ClueToken clue = null;
		List<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < number; i++) {
			try {
				clue = getCluePool().drawNextCard();
			} catch (CardNotFoundException e) {
				System.out.println("Game.spawnClues");
			}
			clue.getField().setClue(clue);
			fields.add(clue.getField());
		}
		return fields;
	}
	public Investigator getActiveInvestigator(){
		return investigators.get(getRound().getActiveInvestigator());
	}

}
