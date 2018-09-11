package model;

import enums.FieldID;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;
import model.Item.Monster;
import utils.DijkstraUtil;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GameBoard {

    private final DijkstraUtil dijkstra;
    private final List<Field> fields;

    public GameBoard(List<Field> fields) {
        this.fields = fields;
        dijkstra= new DijkstraUtil(this);
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

    public void moveTo(Monster monster, Field newField){
        Field oldField= fieldOfMonster(monster);
        oldField.removeMonster(monster);
        newField.addMonster(monster);
    }

    public Field fieldOfMonster(Monster monster) {
        return fields.stream().filter(f -> f.getMonster().contains(monster)).findFirst().orElse(null);
    }

    public Field fieldOfInvestigator(Investigator inv){
        return fields.stream().filter(f -> f.getInvestigators().contains(inv)).findFirst().orElse(null);
    }

    public void addMonster(Monster monster,FieldID fieldID) {
        Field field =  getField(fieldID);
        field.addMonster(monster);
    }

    public List<Field> getPath(Field source, Field destination){
        dijkstra.execute(source.getFieldID());
        return dijkstra.getPath(destination.getFieldID()).stream().map(this::getField).collect(Collectors.toList());
    }
}
