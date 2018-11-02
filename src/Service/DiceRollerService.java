package Service;

import container.Result;
import container.Die;
import enums.ConditionType;
import utils.RNG;

import java.util.Random;

public class DiceRollerService {

   public static boolean debug= false;

    public Result rollDice(int number, ConditionType typ){
        Result result = new Result(typ,1);
        for(int i = 0; i < number;i++){
            if(debug){
                result.add(new Die(6));
            }else {
                Die die = new Die(RNG.getInt(6) + 1);
                result.add(die);
            }
        }
        return result;
    }

    public void rerollDie(Die die) {
        die.setValue(RNG.getInt(6)+1);
    }
}
