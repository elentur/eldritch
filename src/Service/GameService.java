package Service;

import container.ItemContainer;
import enums.SituationType;
import gamemechanics.choice.Choice;
import gamemechanics.encounter.Encounter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import model.Item.Item;

public class GameService {
    private static GameService ourInstance = new GameService();

    private final ObservableList<Effect> insertions;
    private Field field;

    public static GameService getInstance() {
        return ourInstance;
    }

    private  SimpleObjectProperty<Encounter> encounter = new SimpleObjectProperty<>();
    private  SimpleObjectProperty<Choice> choice = new SimpleObjectProperty<>();

    private GameService() {
        insertions= FXCollections.observableArrayList();
    }

    public Field getFieldOfInvestigator(Investigator inv) {

        field.getInvestigators().add(inv);
        return field;
    }


    public  ItemContainer<Item> getBonusItemsforInvestigator(Investigator investigator) {
        ItemContainer<Item> items = new ItemContainer<>();
        for(Investigator inv : getFieldOfInvestigator(investigator).getInvestigators()){
            items.addAll(inv.getInventory().getItemsWidthSituationTyp(SituationType.ALL));
        }
        items.add(investigator);
        return items ;
    }



    public  void addChoice(Choice choice) {
        this.choice.set(choice);
    }

    public SimpleObjectProperty<Choice> getChoiceProperty() {
        return choice;
    }

    public ObservableList<Effect> getInsertions() {
        return insertions;
    }

    public void addEffect(Effect effect){
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
}
