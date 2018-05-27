package Service;

import container.BonusContainer;
import container.ItemContainer;
import enums.FieldType;
import enums.SituationType;
import model.Field;
import model.Investigator;
import model.Item.Bonus;
import model.Item.Item;

import java.util.function.Function;
import java.util.stream.Collectors;

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
            items.addAll(inv.getInventory().getItemsWidthSituationTyp(SituationType.ALL));
        }
        return items ;
    }

    public BonusContainer<Bonus> getInvestigatorBoni(Investigator investigator,Function<Bonus,Boolean> filter) {
        BonusContainer<Bonus> boni = new BonusContainer<>();
        for(Investigator inv : getFieldOfInvestigator(investigator).getInvestigators()){
            boni.addAll(inv.getBonus().stream().filter(filter::apply).collect(Collectors.toCollection(BonusContainer::new)));
        }
        return boni;
    }
}
