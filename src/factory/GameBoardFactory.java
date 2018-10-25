package factory;

import enums.FieldConnections;
import enums.FieldID;
import enums.FieldType;
import lombok.extern.java.Log;
import model.Field;
import model.GameBoard;
import model.Neighbour;

import java.util.ArrayList;
import java.util.List;

@Log
public class GameBoardFactory {

    public static GameBoard getGameBoard() {

        List<Field> fields = createFields();
        connectNeighbours(fields);

        return new GameBoard(fields);
    }

    private static List<Field> createFields() {
        List<Field> fields = new ArrayList<>();

        for (FieldID fieldID : FieldID.values()) {
            if(fieldID.getType().equals(FieldType.NONE)){
                continue;
            }
            Field field = new Field(fieldID);
            fields.add(field);
        }
        return fields;
    }

    private static void connectNeighbours(List<Field> fields) {
        for (Field field : fields) {
            for (FieldID neighbourID : field.getFieldID().getNeigbours()) {
                FieldConnections connections = FieldConnections.getConnection(field.getFieldID(), neighbourID);
                if (connections == null) {
                    log.warning("FieldID: " + field.getFieldID() + "   Neighbour: " + neighbourID + " are not neighbours.");
                } else {
                    Field neighbourField = fields.stream().filter(f -> f.getFieldID().equals(neighbourID)).findFirst().orElse(null);
                    if (neighbourField != null) {
                        Neighbour neighbour1 = new Neighbour(neighbourField, connections.getPathType());
                        Neighbour neighbour2 = new Neighbour(field, connections.getPathType());
                        field.getNeighbours().add(neighbour1);
                        neighbourField.getNeighbours().add(neighbour2);
                    }
                }
            }
        }
    }

}
