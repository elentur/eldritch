package Service;

import container.InvestigatorContainer;
import container.ItemContainer;
import container.ItemStack;
import enums.FieldID;
import enums.OldOnes;
import enums.OmenStates;
import enums.SituationType;
import factory.ItemFactory;
import factory.MonsterFactory;
import gamemechanics.Phases;
import gamemechanics.choice.Choice;
import gamemechanics.encounter.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import model.*;
import model.Item.*;
import model.Item.ancientOnes.Azathoth;
import model.Item.token.*;
import model.effects.SwitchPhase;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private static GameService ourInstance = new GameService();

    private final ObservableList<Effect> insertions;

    @Getter
    private ObjectProperty<Investigator> activeInvestigator;
    private int count;

    @Getter
    private Investigator encounteringInvestigator;
    private GameBoard gameBoard;
    @Getter
    private InvestigatorContainer investigators;
    @Getter
    private ItemStack<Monster> monsterPool;
    @Getter
    private AncientOne ancientOne;

    @Getter
    private ItemStack<Asset> assets;
    @Getter
    private ItemStack<Artifact> artifacts;
    @Getter
    private ItemStack<Spell> spells;

    @Getter
    private ItemStack<Condition> conditions;

    @Getter
    private ItemStack<ClueToken> clueTokens;
    @Getter
    private ItemStack<GateToken> gateTokens;

    @Getter
    private ItemStack<StandardEncounter> standardEncounters;
    @Getter
    private ItemStack<SpecialEncounter> specialEncounter;
    @Getter
    private ItemStack<ResearchEncounter> reseaarchEncounter;
    @Getter
    private ItemStack<OtherWorldEncounter> otherWorldEncounter;
    @Getter
    private ItemStack<ExpeditionEncounter> expeditionEncounter;
    @Getter
    private ItemStack<EuropeEncounter> europeEncounter;
    @Getter
    private ItemStack<AsiaEncounter> asiaEncounter;
    @Getter
    private ItemStack<AmericaEncounter> americaEncounter;

    @Getter
    private List<Spell> usedSpells;



    @Getter
    @Setter
    private Monster lastChosenMonster;

    @Getter
    private DoomTrack doomTrack;
    @Getter
    private OmenTrack omenTrack;
    private Reserve reserve;

    @Getter
    private final Phases phases;

    public static GameService getInstance() {
        return ourInstance;
    }

    public void startGame(InvestigatorContainer investigators, GameBoard gameBoard) {

        setGameBoard(gameBoard);
        this.investigators = investigators;
        for (Investigator inv : investigators) {
            gameBoard.addInvestigator(inv);
        }
        count = 1;
        activeInvestigator.setValue(investigators.get(0));
        encounteringInvestigator = activeInvestigator.getValue();
        reserve.init();

    }

    public void setActiveInvestigator() {
        if (count >= investigators.size()) {
            count = 0;
            addEffect(new SwitchPhase());
        }
        int i = (investigators.indexOf(activeInvestigator.getValue()) + 1) % investigators.size();
        activeInvestigator.setValue(investigators.get(i));
        encounteringInvestigator = activeInvestigator.getValue();
        count++;


    }

    public Investigator getEncounteringInvestigator() {
        return encounteringInvestigator;
    }

    public Investigator getActiveInvestigator() {
        return activeInvestigator.getValue();
    }

    public Investigator getStartInvestigator() {
        return investigators.get(0);
    }

    public void setStartInvestigator() {
        investigators.add(investigators.remove(0));
        activeInvestigator.setValue(getStartInvestigator());
        encounteringInvestigator = getStartInvestigator();
        count = 1;
    }

    public Investigator[] getInactiveInvestigators() {
        Investigator[] invs = new Investigator[investigators.size() - 1];
        int index = (investigators.indexOf(activeInvestigator.getValue()) + 1) % investigators.size();
        for (int i = 0; i < invs.length; i++) {
            invs[i] = investigators.get((index + i) % investigators.size());
        }
        return invs;
    }

    private SimpleObjectProperty<Encounter> encounter = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Choice> choice = new SimpleObjectProperty<>();

    private GameService() {
        insertions = FXCollections.observableArrayList();
        activeInvestigator = new SimpleObjectProperty<>(null);
        assets = ItemFactory.getAssets();
        artifacts = ItemFactory.getArtifacts();
        spells = ItemFactory.getSpells();
        conditions = ItemFactory.getConditions();
        reserve = new Reserve(assets);
        standardEncounters = ItemFactory.getStandardEncounters();
        specialEncounter = ItemFactory.getSpecialEncounters(OldOnes.SHUB_NIGGURATH);
        reseaarchEncounter = ItemFactory.getResearchEncounters(OldOnes.AZATHOTH);
        otherWorldEncounter = ItemFactory.getOtherWorldEncounter();
        expeditionEncounter = ItemFactory.getExpeditionEncounter();
        europeEncounter = ItemFactory.getEuropeEncounter();
        asiaEncounter = ItemFactory.getAsiaEncounter();
        americaEncounter = ItemFactory.getAmericaEncounter();
        clueTokens = ItemFactory.getClueTokens();
        gateTokens = ItemFactory.getGateTokens();
        doomTrack = new DoomTrack(15);
        omenTrack = new OmenTrack(OmenStates.GREEN_COMET);
        phases = new Phases();
        monsterPool = MonsterFactory.getMonster();
        ancientOne = new Azathoth();
        usedSpells = new ArrayList<>();


    }

    public Field getFieldOfInvestigator(Investigator inv) {
        return gameBoard.fieldOfInvestigator(inv);
    }

    public Field getFieldOfMonster(Monster monster) {
        return gameBoard.fieldOfMonster(monster);
    }

    public void moveTo(Investigator inv, Field newField) {
        gameBoard.moveTo(inv, newField);


    }

    public void moveTo(Monster monster, Field newField) {
        gameBoard.moveTo(monster, newField);
    }


    public ItemContainer<Item> getBonusItemsforInvestigator(Investigator investigator) {
        ItemContainer<Item> items = new ItemContainer<>();
        Field f = getFieldOfInvestigator(investigator);
        for (Investigator inv : f.getInvestigators()) {
            items.addAll(inv.getInventory().getItemsWidthSituationTyp(SituationType.ALL));
        }
        items.add(investigator);
        return items;
    }


    public void addChoice(Choice choice) {
        this.choice.set(choice);
    }

    public SimpleObjectProperty<Choice> getChoiceProperty() {
        return choice;
    }

    public ObservableList<Effect> getInsertions() {
        return insertions;
    }

    public void addEffect(Effect effect) {
        insertions.add(effect);

    }

    public void addAllEffect(List<Effect> list) {
        insertions.addAll(list);

    }
    public void addEffectAfter(Effect effect) {
        insertions.add(0, effect);

    }

    public SimpleObjectProperty<Encounter> getEncounterProperty() {
        return encounter;
    }

    public void addEncounter(Encounter encounter) {
        this.encounter.set(encounter);
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }


    public ExpeditionEncounter activeExpedition() {
        return expeditionEncounter.showFirst();
    }

    private ClueToken drawClue() {
        return new ClueToken(FieldID.PYRAMIDS);
    }


    public void removeGate(FieldID fieldID) {
        Field field = gameBoard.getField(fieldID);
        field.removeGate();
    }

    public void addExpedition() {
        Field field = gameBoard.getField(activeExpedition().getFieldID());
        field.addExpedition(new ExpeditionToken());
    }

    public void removeExpedition() {
        Field field = gameBoard.getField(activeExpedition().getFieldID());
        field.removeExpedition();
    }


    public void addClue() {
        ClueToken clue = drawClue();
        Field field = gameBoard.getField(clue.getFieldID());
        field.addClue(clue);
    }

    public void addRandomClueOnField(FieldID fieldID) {
        ClueToken clue = drawClue();
        Field field = gameBoard.getField(fieldID);
        field.addClue(clue);
    }


    public void addRumor(RumorToken token) {

        Field field = gameBoard.getField(token.getFieldID());
        field.addRumor(token);
    }

    public void addMystery(FieldID fieldID) {
        MysteryToken mystery = new MysteryToken();
        Field field = gameBoard.getField(fieldID);
        field.addMystery(mystery);
    }

    public void addEldritchToken(FieldID fieldID, EldritchToken eldritchToken) {
        Field field = gameBoard.getField(fieldID);
        field.addEldritchToken(eldritchToken);
    }

    public void addUsedSpell(Spell spell) {
        if(!usedSpells.contains(spell)){
            usedSpells.add(spell);
        }
    }


    public ObjectProperty<Investigator> getActiveInvestigatorProperty() {
        return activeInvestigator;
    }



    public Reserve getReserve() {
        return reserve;
    }


    public void clearPath() {

    }

    public void showPath(Field field) {
    }

    public void removeInvestigator(Investigator investigator) {
        gameBoard.removeInvestigator(investigator);
    }
}
