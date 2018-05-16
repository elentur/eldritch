package container;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import model.Item.Item;
import model.Item.ItemBonus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public ItemContainer<Item> getItemsWidthSituationTyp(SituationTyp situation) {
        return this.stream().filter(item -> item.getBonus().stream().anyMatch(bonus -> bonus.getSituation().equalsWithAll(situation)))
                .collect(Collectors.toCollection(ItemContainer::new));
    }

    public ItemContainer<Item> getItemsWidthEventTimeType(EventTimeType eventTime) {
        return this.stream().filter(item -> item.getBonus().stream().anyMatch(bonus -> bonus.getEventTime().equals(eventTime))).collect(Collectors.toCollection(ItemContainer::new));
    }

    public BonusContainer<ItemBonus> getBoniWithSituationTyp(SituationTyp situation) {
        return this.stream().collect(BonusContainer<ItemBonus>::new, ItemContainer::addAll, BonusContainer::addAll)
                .stream().filter(bonus -> situation.equals(bonus.getSituation()) && bonus.isActive()).collect(Collectors.toCollection(BonusContainer::new));

    }



    public BonusContainer<ItemBonus> getBoniWithSituationTyp(SituationTyp situation, TestTyp test) {
        return this.stream().collect(BonusContainer<ItemBonus>::new, ItemContainer::addAll, BonusContainer::addAll)
                .stream().filter(bonus -> situation.equals(bonus.getSituation())
                        && test.equalsWithAll(bonus.getTest())
                        && bonus.isActive()).collect(Collectors.toCollection(BonusContainer::new));

    }

    private static <T extends Item> void addAll(BonusContainer<ItemBonus> boni, T t) {
        boni.addAll(t.getBonus());
    }

}
