package container;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import model.Item.Bonus;
import model.Item.ItemBonus_GainDice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class BonusContainer<T extends Bonus> extends ArrayList<T>{

    public BonusContainer(List<T> list){
       super(list);
    }
    public BonusContainer(){
        super();
    }



    public BonusContainer<T> getAllByEventTime(EventTimeType eventTime) {
        if(eventTime == null){
            return new BonusContainer<>();
        }
       return new BonusContainer<>(this.stream().filter(item -> eventTime.equals(item.getEventTime())).filter(item ->!(item instanceof ItemBonus_GainDice)).collect(Collectors.toList()));
    }
    public List<T> getAllBySituation(SituationTyp situation) {
        if(situation == null){
            return  new ArrayList<>();
        }
        return this.stream().filter(item -> situation.equals(item.getSituation())).filter(item ->!(item instanceof ItemBonus_GainDice)).collect(Collectors.toList());
    }

    public List<T> getAllBySituation(SituationTyp situation, TestTyp test) {
        if(situation == null){
            return  new ArrayList<>();
        }
        return this.stream().filter(item -> situation.equals(item.getSituation())&&test.equals(item.getTest())).filter(item ->!(item instanceof ItemBonus_GainDice)).collect(Collectors.toList());
    }

    public ItemBonus_GainDice getStrongestGainDiceBonus(TestTyp testTyp) {
        Optional<ItemBonus_GainDice> optional= this.stream().filter(item -> item instanceof ItemBonus_GainDice).map(item-> (ItemBonus_GainDice)item).filter(item->
             item.getTest().equals(testTyp)||item.getTest().equals(TestTyp.NONE)).max(Comparator.comparingInt(ItemBonus_GainDice::getValue));
        if(optional.isPresent()){
            return optional.get();
        }
        return ItemBonus_GainDice.EMPTY;
    }
}
