package container;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import model.Item.Bonus;
import model.Item.Item;
import model.Item.ItemBonus;
import model.Item.ItemBonus_AdditionalDice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ItemContainer<T extends Item> extends ArrayList<T>{



    public ItemContainer(List<T> list){
       super(list);
    }
    public ItemContainer(){
        super();
    }



    public T get(String s) {
        if(s == null){
            return null;
        }
        Optional<T> opt= this.stream().filter(item -> s.equals(item.getId())).findFirst();
        return opt.orElse(null);
    }

    public ItemContainer<Item> getItemsWidthFilter(Function<ItemBonus,Boolean> filter) {
        return this.stream().filter(item -> item.getBonus().stream().anyMatch(filter::apply))
                .collect(Collectors.toCollection(ItemContainer::new));
    }

    public BonusContainer<Bonus> getBoniWithFilter(Function<Bonus,Boolean> filter) {
        return this.stream().collect(BonusContainer<ItemBonus>::new, ItemContainer::addAll, BonusContainer::addAll)
                .stream().filter(filter::apply).collect(Collectors.toCollection(BonusContainer::new));

    }


    private static <T extends Item> void addAll(BonusContainer<ItemBonus> boni, T t) {
        boni.addAll(t.getBonus());
    }



    public ItemContainer<Item> getItemsWidthSituationTyp(SituationTyp situationTyp) {
        return this.stream().filter(item -> item.getBonus().stream().anyMatch(bonus->bonus.getSituation().equalsWithAll(situationTyp)))
                .collect(Collectors.toCollection(ItemContainer::new));
    }
}
