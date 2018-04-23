package container;

import enums.ConditionTyp;
import enums.TestTyp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.stream.Collectors;



public class Result extends ArrayList<Die> {
    private ConditionTyp typ;
    @Setter
    @Getter
    private int minNumberOfSuccesses;

    private int reroll;

    public Result(ConditionTyp typ, int minNumberOfSuccesses){
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

    public void setReroll(int reroll) {
        this.reroll = reroll;
    }
    public void rerollDie(Die die){
        if(reroll<=1)return;
        die.reroll();
        reroll--;
    }
}
