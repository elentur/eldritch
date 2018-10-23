package model;

import Service.GameService;
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
        if(id.equals(FieldID.CHOSEN_FIELD)){
            return GameService.getInstance().getChosenField();
        }
        return fields.stream().filter(f -> f.getFieldID().equals(id)).findFirst().orElse(null);
    }

    public void addInvestigator(Investigator inv) {
       Field field = getField(inv.getStartingSpace());
       field.addInvestigator(inv);
    }

    public void removeInvestigator(Investigator inv){
        Field oldField= fieldOfInvestigator(inv);
        oldField.removeInvestigator(inv);
    }
    public void removeMonster(Monster monster){
        Field oldField= fieldOfMonster(monster);
        oldField.removeMonster(monster);
    }
    public void moveTo(Investigator inv, Field newField){

        newField.addInvestigator(inv);
    }

    public void moveTo(Monster monster, Field newField){
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

    public List<Field> getPath(Field source, Field destination, Investigator inv){
        dijkstra.execute(source.getFieldID(),inv);
        return dijkstra.getPath(destination.getFieldID()).stream().map(this::getField).collect(Collectors.toList());
    }
    public Field getNearestField(Field field){
        dijkstra.execute(field.getFieldID(),null);
        FieldID id =  dijkstra.getNearestField();
        if(id!=null){
            return getField(id);
        }
        return null;
    }

    public Field getFieldWithDistance(Field field, int value) {
        dijkstra.execute(field.getFieldID(),null);
        FieldID id =  dijkstra.getFieldWithDistance(value);
        if(id!=null){
            return getField(id);
        }
        return null;
    }

    public List<Field> getFieldsWithDistance( Field field,int distance) {
        dijkstra.execute(field.getFieldID(),null);
        List<FieldID> fieldIds =  dijkstra.getFieldsWithDistance(distance);
        return fieldIds.stream().map(this::getField).collect(Collectors.toList());
    }
}
