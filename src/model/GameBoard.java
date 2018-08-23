package model;

import enums.FieldID;
import lombok.Getter;
import lombok.Setter;

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

}
