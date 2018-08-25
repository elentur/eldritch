package model;

import enums.FieldID;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;

import java.util.List;

@Getter
@Setter
public class GameBoard {

    private final List<Field> fields;

    public GameBoard(List<Field> fields) {
        this.fields = fields;
    }

    public Field getField(FieldID id){
        return fields.stream().filter(f -> f.getFieldID().equals(id)).findFirst().orElse(null);
    }

    public void addInvestigator(Investigator inv) {
       Field field = getField(inv.getStartingSpace());
       field.addInvestigator(inv);
    }

    public void moveTo(Investigator inv, Field newField){
        Field oldField= fieldOfInvestigator(inv);
        oldField.removeInvestigator(inv);
        newField.addInvestigator(inv);
    }

    public Field fieldOfInvestigator(Investigator inv){
        return fields.stream().filter(f -> f.getInvestigators().contains(inv)).findFirst().orElse(null);
    }

}
