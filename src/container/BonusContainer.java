package container;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import model.Item.Bonus;

import java.util.ArrayList;
import java.util.List;
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
       return new BonusContainer<>(this.stream().filter(item -> eventTime.equals(item.getEventTime())).collect(Collectors.toList()));
    }
    public List<T> getAllBySituation(SituationTyp situation) {
        if(situation == null){
            return  new ArrayList<>();
        }
        return this.stream().filter(item -> situation.equals(item.getSituation())).collect(Collectors.toList());
    }

    public List<T> getAllBySituation(SituationTyp situation, TestTyp test) {
        if(situation == null){
            return  new ArrayList<>();
        }
        return this.stream().filter(item -> situation.equals(item.getSituation())&&test.equals(item.getTest())).collect(Collectors.toList());
    }

}