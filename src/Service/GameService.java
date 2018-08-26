package Service;

import container.ItemContainer;
import enums.FieldID;
import enums.SituationType;
import gamemechanics.choice.Choice;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.ExpeditionEncounter;
import gamemechanics.encounter.expeditionencounter.ExpeditionEncounter0;
import gamemechanics.encounter.otherworldencounter.OtherWorldEncounter0;
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
import model.Item.token.ExpeditionToken;

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

    public ExpeditionEncounter activeExpedition() {
        return new ExpeditionEncounter0(activeInvestigator);
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
}
