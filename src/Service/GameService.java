package Service;

import container.BonusContainer;
import container.ItemContainer;
import enums.FieldType;
import enums.SituationTyp;
import model.Field;
import model.Investigator;
import model.Item.Bonus;
import model.Item.Item;

public class GameService {
    private static GameService ourInstance = new GameService();

    public static GameService getInstance() {
        return ourInstance;
    }

    private GameService() {

    }

    public Field getFieldOfInvestigator(Investigator inv) {
        Field f = new Field(FieldType.CITY);
        f.getInvestigators().add(inv);
        return f;
    }


    public  ItemContainer<Item> getBonusItemsforInvestigator(Investigator investigator) {
        ItemContainer<Item> items = new ItemContainer<>();
        for(Investigator inv : getFieldOfInvestigator(investigator).getInvestigators()){
            items.addAll(inv.getInventory().getItemsWidthSituationTyp(SituationTyp.ALL));
        }
        return items ;
    }

    public BonusContainer<Bonus> getInvestigatorBoni(Investigator investigator) {
        BonusContainer<Bonus> boni = new BonusContainer<>();
        for(Investigator inv : getFieldOfInvestigator(investigator).getInvestigators()){
            boni.addAll(inv.getBonus());
        }
        return boni;
    }
}
