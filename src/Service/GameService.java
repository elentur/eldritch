package Service;

import container.ItemContainer;
import enums.FieldID;
import enums.SituationType;
import gamemechanics.choice.Choice;
import gamemechanics.choice.EncounterChoice;
import gamemechanics.encounter.CombatEncounter;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.ExpeditionEncounter;
import gamemechanics.encounter.americaencounter.AmericaEncounter0;
import gamemechanics.encounter.asiaencounter.AsiaEncounter0;
import gamemechanics.encounter.europeencounter.EuropeEncounter0;
import gamemechanics.encounter.expeditionencounter.ExpeditionEncounter0;
import gamemechanics.encounter.otherworldencounter.OtherWorldEncounter0;
import gamemechanics.encounter.researchencounter.azathoth.ResearchEncounter0;
import gamemechanics.encounter.rumorencounter.RumorEncounter0;
import gamemechanics.encounter.specialencounter.shubniggurath.SpecialEncounter0;
import gamemechanics.encounter.standardencounter.StandardEncounter0;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Field;
import model.GameBoard;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.Monster;
import model.Item.token.*;

public class GameService {
    private static GameService ourInstance = new GameService();

    private final ObservableList<Effect> insertions;
    private Field field;
    @Getter
    @Setter
    private Investigator activeInvestigator;
    private GameBoard gameBoard;

    public static GameService getInstance() {
        return ourInstance;
    }

    private SimpleObjectProperty<Encounter> encounter = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Choice> choice = new SimpleObjectProperty<>();

    private GameService() {
        insertions = FXCollections.observableArrayList();
    }

    public Field getFieldOfInvestigator(Investigator inv) {
        return gameBoard.fieldOfInvestigator(inv);
    }

    public void moveTo(Investigator inv, Field newField) {
        gameBoard.moveTo(inv, newField);
        if(!newField.getMonster().isEmpty()){
            GameService.getInstance().addEncounter(new CombatEncounter(newField.getMonster(),activeInvestigator));
        }else {
            GameService.getInstance().addChoice(new EncounterChoice(newField));
        }

    }

    public void moveTo(Monster monster, Field newField) {
        gameBoard.moveTo(monster, newField);
    }


    public ItemContainer<Item> getBonusItemsforInvestigator(Investigator investigator) {
        ItemContainer<Item> items = new ItemContainer<>();
        for (Investigator inv : getFieldOfInvestigator(investigator).getInvestigators()) {
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

    public SimpleObjectProperty<Encounter> getEncounterProperty() {
        return encounter;
    }

    public void addEncounter(Encounter encounter) {
        this.encounter.set(encounter);
    }

    public void setField(Field f) {
        field = f;
    }


    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Encounter drawOtherWorldEncounter() {
        return new OtherWorldEncounter0(activeInvestigator);
    }

    public Encounter drawResearchEncounter() {

        return new ResearchEncounter0(activeInvestigator);
    }

    public Encounter drawSpecialEncounter() {
        return new SpecialEncounter0(activeInvestigator);
    }

    public Encounter drawStandardEncounter() {
        return new StandardEncounter0(activeInvestigator);
    }
    public Encounter drawEuropeEncounter() {
        return new EuropeEncounter0(activeInvestigator);
    }
    public Encounter drawAsiaEncounter() {
        return new AsiaEncounter0(activeInvestigator);
    }
    public Encounter drawAmericaEncounter() {
        return new AmericaEncounter0(activeInvestigator);
    }
    public ExpeditionEncounter activeExpedition() {
        return new ExpeditionEncounter0(activeInvestigator);
    }

    private ClueToken drawClue() {
        return new ClueToken(FieldID.PYRAMIDS);
    }

    public void addGate(FieldID fieldID) {
        Field field = gameBoard.getField(fieldID);
        field.addGate();
    }

    public void removeGate(FieldID fieldID) {
        Field field = gameBoard.getField(fieldID);
        field.removeGate();
    }

    public void addExpedition() {
        Field field = gameBoard.getField(activeExpedition().getFieldID());
        field.addExpedition(new ExpeditionToken(activeExpedition()));
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


}
