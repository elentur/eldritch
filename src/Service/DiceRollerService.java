package Service;

import container.Result;
import container.Die;
import enums.ConditionTyp;

import java.util.Random;

public class DiceRollerService {
   private static Random rnd = new Random(532835472);

    public Result rollDice(int number, ConditionTyp typ){
        Result result = new Result(typ,1);
        for(int i = 0; i < number;i++){
            Die die = new Die(rnd.nextInt(6)+1);
            result.add(die);
        }
        return result;
    }
}
