package container;

import enums.ConditionType;
import lombok.Getter;
import lombok.Setter;
import model.Item.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Setter
@Getter
public class Result extends ArrayList<Die> {
    private ConditionType typ;

    private int minNumberOfSuccesses;

    private int reroll;
    private int shift;
    private int shiftValue;
    @Override
    public boolean add(Die value) {
        return value != null && super.add(value);
    }

    @Override
    public void add(int index,Die value){
        if(value==null){
            return;
        }
        super.add(index,value);
    }
    public Result(ConditionType typ, int minNumberOfSuccesses){
        this.typ = typ;
        this.minNumberOfSuccesses=minNumberOfSuccesses;
    }

    public int getNumberOfSuccess(){
        return (int)this.stream().filter(die->die.isSuccess(typ)).count();
    }

    public boolean isSuccess(){
        return getNumberOfSuccess()>=minNumberOfSuccesses;
    }

    public List<Die> getFails() {
        return this.stream().filter(die -> !die.isSuccess(typ)).collect(Collectors.toList());
    }


    public void rerollDie(Die die){
        if(reroll<1)return;
        reroll--;
        die.reroll();

    }
    public void shiftDie(Die die){
        if(shift<1)return;
        die.shift(shiftValue);
        shift--;
        if(shift<=0){
            for(Die d: this){
                d.setShiftable(false);
            }
        }
    }

}
