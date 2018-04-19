package container;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import model.Item.Bonus;
import model.Item.Item;

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
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }

    public ItemContainer<Item> getItemsWidthSituationTyp(SituationTyp situation) {
        return new ItemContainer<>(this.stream().filter(item-> item.getBonus().stream().
                        filter(bonus->bonus.getSituation().equals(situation)).
                        count()>0).collect(Collectors.toList()));
    }

    public ItemContainer<Item> getItemsWidthEventTimeType(EventTimeType eventTime) {
        return new ItemContainer<>(this.stream().filter(item-> item.getBonus().stream().
                filter(bonus->bonus.getEventTime().equals(eventTime)).
                count()>0).collect(Collectors.toList()));
    }

    public BonusContainer<Bonus> getBoniWithSituationTyp(SituationTyp situation) {
      return new BonusContainer<>(this.stream().collect(BonusContainer::new, ItemContainer::addAll, BonusContainer::addAll)
              .stream().filter(bonus->situation.equals(bonus.getSituation())).collect(Collectors.toList()));
    }
    public BonusContainer<Bonus> getBoniWithSituationTyp(SituationTyp situation, TestTyp test) {
        return new BonusContainer<>(this.stream().collect(BonusContainer::new, ItemContainer::addAll, BonusContainer::addAll)
                .stream().filter(bonus->situation.equals(bonus.getSituation())&&test.equals(bonus.getTest())).collect(Collectors.toList()));

    }

    private static <T extends Item> void addAll(BonusContainer<Bonus> boni, T t) {
        boni.addAll(t.getBonus());
    }

}
