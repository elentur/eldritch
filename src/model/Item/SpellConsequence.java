package model.Item;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class SpellConsequence {

    private final String id;
    private final int number;
    private final List<Effect> effects;
    private final int[] values;

    public SpellConsequence(String id, int number, int[] values, List<Effect> effects) {
        this.id = id;
        this.number = number;
        this.effects = effects;
        this.values = values;
    }



    public Effect getEffect(int result){
        return effects.get(getInt(result));
    }

    public String getText(int result){
        return ResourceUtil.get(id.replace("}","_con_"+number+"_"+ getInt(result)+"}"),"spell")+
                "\n" +getEffect(result).getText();
    }

    private int getInt ( int result){
        for (int i = values.length-1; i >=0;i--){
            if(values[i] <=  result){
                return i;
            }
        }
        return 0;
    }
}
