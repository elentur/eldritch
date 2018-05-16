package Service;

import enums.FieldType;
import model.Field;
import model.Investigator;

public class GameService {
    private static GameService ourInstance = new GameService();

    public static GameService getInstance() {
        return ourInstance;
    }

    private GameService() {
    }

    public Field getFieldOfInvestigator(Investigator inv){
        return new Field(FieldType.CITY);
    }
}
