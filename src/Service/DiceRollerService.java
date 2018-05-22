package Service;

import container.Result;
import container.Die;
import enums.ConditionTyp;

import java.util.Random;

public class DiceRollerService {
   private static Random rnd = new Random();

    public Result rollDice(int number, ConditionTyp typ){
        Result result = new Result(typ,1);
        for(int i = 0; i < number;i++){
            Die die = new Die(rnd.nextInt(6)+1);
            result.add(die);
        }
        return result;
    }

    public void rerollDie(Die die) {
        die.setValue(rnd.nextInt(6)+1);
    }
}
