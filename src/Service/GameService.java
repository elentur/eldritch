package Service;

import container.InvestigatorContainer;
import container.ItemContainer;
import container.ItemStack;
import enums.FieldID;
import enums.OmenStates;
import enums.SituationType;
import factory.ItemFactory;
import factory.MonsterFactory;
import gamemechanics.Mystery;
import gamemechanics.Phases;
import gamemechanics.choice.Choice;
import gamemechanics.choice.InformationChoice;
import gamemechanics.encounter.*;
import gamemechanics.mystery.azathoth.Mystery0;
import gamemechanics.mystery.azathoth.Mystery2;
import gamemechanics.mystery.azathoth.Mystery3;
import gui.InterfaceLinking;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import model.*;
import model.Item.*;
import model.Item.token.*;
import model.effects.SwitchPhase;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class GameService {
    private static GameService ourInstance = new GameService();

    private  ObservableList<Effect> insertions;
    private SimpleObjectProperty<Encounter> encounter = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Choice> choice = new SimpleObjectProperty<>();

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
    private ItemStack<Mystery> mysteries;


    @Getter
    private List<Spell> usedSpells;



    @Getter
    @Setter
    private Monster lastChosenMonster;

    @Getter
    private Mystery activeMystery;

    @Getter
    private DoomTrack doomTrack;
    @Getter
    private OmenTrack omenTrack;
    private Reserve reserve;

    @Getter
    private  Phases phases;
    private List<Function<Encounter,Void> > encounterListener;

    public static GameService getInstance() {
        return ourInstance;
    }

    public void setGameBoardAndAncientOne(AncientOne ancientOne, GameBoard gameBoard) {
        this.ancientOne = ancientOne;
        setGameBoard(gameBoard);
        init();


    }
    public void addInvestigators(InvestigatorContainer investigators) {
        this.investigators = investigators;
        for (Investigator inv : investigators) {
            gameBoard.addInvestigator(inv);
        }
        count = 1;
        activeInvestigator.setValue(investigators.get(0));
        encounteringInvestigator = activeInvestigator.getValue();
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


    private void init(){
        encounterListener = new ArrayList<>();
        insertions = FXCollections.observableArrayList();
        activeInvestigator = new SimpleObjectProperty<>(null);
        assets = ItemFactory.getAssets();
        artifacts = ItemFactory.getArtifacts();
        spells = ItemFactory.getSpells();
        conditions = ItemFactory.getConditions();
        reserve = new Reserve(assets);
        standardEncounters = ItemFactory.getStandardEncounters();
        specialEncounter = ItemFactory.getSpecialEncounters(ancientOne.getOldOne());
        reseaarchEncounter = ItemFactory.getResearchEncounters(ancientOne.getOldOne());
        otherWorldEncounter = ItemFactory.getOtherWorldEncounter();
        expeditionEncounter = ItemFactory.getExpeditionEncounter();
        europeEncounter = ItemFactory.getEuropeEncounter();
        asiaEncounter = ItemFactory.getAsiaEncounter();
        americaEncounter = ItemFactory.getAmericaEncounter();
        mysteries = ItemFactory.getMysteries(ancientOne.getOldOne());
        clueTokens = ItemFactory.getClueTokens();
        gateTokens = ItemFactory.getGateTokens();
        doomTrack = new DoomTrack(15);
        omenTrack = new OmenTrack(OmenStates.GREEN_COMET);
        phases = new Phases();
        monsterPool = MonsterFactory.getMonster();

        usedSpells = new ArrayList<>();



    }
    public void startGame() {
        addActiveMystery();
        reserve.init();
    }
    private void addActiveMystery(){
        activeMystery =new Mystery3();//mysteries.draw();
       activeMystery.getUpdate().addListener(InterfaceLinking.interfaceGui.getMysteryGUI().getListener());
        InformationChoice choice = new InformationChoice(ResourceUtil.get("${mystery}", "ui"),
                activeMystery.getName() +"\n" +activeMystery.getText(),null);
        addChoice(choice);
        activeMystery.init();
        InterfaceLinking.interfaceGui.getMysteryGUI().update();
    }
    public void handleMystery() {
        if(activeMystery.isFinished()){
            activeMystery.getUpdate().removeListener(InterfaceLinking.interfaceGui.getMysteryGUI().getListener());
            activeMystery.discard();
            if(mysteries.getTraystack().size()>=ancientOne.getminNumberOfSolvedMysteries()){
                //TODO
                InformationChoice choice = new InformationChoice("Gewonnen",
                        "Du hast gewonnen",null);
                addChoice(choice);
                System.exit(1);
            }else{
                addActiveMystery();
            }
        }
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
        if(encounter!=null) {
            for (Function<Encounter, Void> listner : encounterListener) {
                listner.apply(encounter);
            }
        }
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


    public void addEncounterListener(Function<Encounter,Void>  listener) {
        encounterListener.add(listener);

    }
    public void removeEncounterListener(Function<Encounter,Void> listener) {
        encounterListener.remove(listener);

    }

    public Field getRandomField(){
        int i = FieldID.values().length;
        Random r = new Random();
        return gameBoard.getField(FieldID.values()[r.nextInt(i)]);
    }
}
