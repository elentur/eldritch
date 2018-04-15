package container;

import enums.ConditionTyp;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class Result extends ArrayList<Die> {
    private ConditionTyp typ;

    public int getNumberOfSuccess(){
        return (int)this.stream().filter(die->die.isSuccess(typ)).count();
    }

    public boolean isSuccess(){
        return getNumberOfSuccess()>0;
    }
}
