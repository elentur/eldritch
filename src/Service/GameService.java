package Service;

import container.ItemContainer;
import enums.FieldType;
import enums.SituationTyp;
import model.Field;
import model.Investigator;
import model.Item.Item;

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


    public static ItemContainer<Item> getBonusItemsforInvestigatorAndSituation(Investigator investigator) {
        return investigator.getInventory().getItemsWidthSituationTyp(SituationTyp.ALL);
    }
}
