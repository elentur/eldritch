package container;

import enums.EventTimeType;
import enums.SituationType;
import enums.TestType;
import model.Item.Bonus;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.Item.boni.ItemBonus_GainDice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public class BonusContainer<T extends Bonus> extends ArrayList<T>{

    private BonusContainer(List<T> list){
       super(list);
    }
    public BonusContainer(){
        super();
    }



    public BonusContainer<T> getAllByEventTime(EventTimeType eventTime) {
        if(eventTime == null){
            return new BonusContainer<>();
        }
       return new BonusContainer<>(this.stream().filter(bonus -> eventTime.equals(bonus.getEventTime())&& bonus.isUsable()).filter(bonus ->!(bonus instanceof ItemBonus_GainDice)).collect(Collectors.toList()));
}
    public List<T> getAllBySituation(SituationType situation) {
        if(situation == null){
            return  new ArrayList<>();
        }
        return this.stream().filter(item -> situation.equals(item.getSituation())).filter(item ->!(item instanceof ItemBonus_GainDice)).collect(Collectors.toList());
    }

    public List<T> getAllBySituation(SituationType situation, TestType test) {
        if(situation == null){
            return  new ArrayList<>();
        }
        return this.stream().filter(item -> situation.equals(item.getSituation())&&test.equals(item.getTest())).filter(item ->!(item instanceof ItemBonus_GainDice)).collect(Collectors.toList());
    }

    public ItemBonus_GainDice getStrongestGainDiceBonus(Function<Bonus,Boolean> filter) {
        Optional<ItemBonus_GainDice> optional= this.stream().filter(item -> item instanceof ItemBonus_GainDice)
                .map(item-> (ItemBonus_GainDice)item).filter(filter::apply)
                .max(Comparator.comparingInt(ItemBonus_GainDice::getValue));
        return optional.orElse(ItemBonus_GainDice.EMPTY);
    }

    public BonusContainer<ItemBonus_AdditionalDice> getAdditionalDiceBoni(Function<Bonus,Boolean> filter) {
        return  this.stream().filter(bonus -> bonus instanceof ItemBonus_AdditionalDice)
                .filter(filter::apply).filter(bonus->!bonus.isActivated()).map(bonus -> (ItemBonus_AdditionalDice)bonus).collect(Collectors.toCollection(BonusContainer::new));
    }
    public BonusContainer<Bonus> getBoniWithFilter(Function<Bonus,Boolean> filter) {
        return this.stream().filter(filter::apply).collect(Collectors.toCollection(BonusContainer::new));

    }

}
