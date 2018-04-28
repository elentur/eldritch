package model.Item;

import container.Die;
import container.Result;
import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import expetions.NoFailsAvailableException;
import gamemechanics.Encounter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

@Getter
@Setter

@EqualsAndHashCode
public class ItemBonus_DiceResult extends ItemBonus {

    private int num;
    private int value;
    private TestTyp test;
    private SituationTyp situation;
    private EventTimeType eventTime =EventTimeType.AFTER;

    public ItemBonus_DiceResult(int num, int value, TestTyp test, SituationTyp situation) {
        this.num = num;
        this.value = value;
        this.test = test;
        this.situation = situation;

    }

    @Override
    public void execute(Encounter encounter) {
        Result result = encounter.getResult();
        if(!result.getFails().isEmpty()){
            result.setShift(num);
            result.setShiftValue(value);
            for(Die die: result){
                if(die.getValue()<6){
                    die.setShiftable(true);
                }
            }
        }else{
            throw new NoFailsAvailableException();
        }
    }
    @Override
    public String getText() {
        return ResourceUtil.get("${diceResult}",Bonus.class,num+"",value+"", test.getText(), situation.getText() );
    }
}
