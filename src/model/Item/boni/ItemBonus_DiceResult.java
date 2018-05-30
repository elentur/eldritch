package model.Item.boni;

import container.Die;
import container.Result;
import enums.BonusType;
import enums.EventTimeType;
import enums.SituationType;
import enums.TestType;
import expetions.NoFailsAvailableException;
import gamemechanics.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Item;
import utils.ResourceUtil;

@Getter
@Setter

public class ItemBonus_DiceResult extends ItemBonus {

    private int num;
    private int value;
    private BonusType bonusType = BonusType.DICE_RESULT;

    public ItemBonus_DiceResult(int num, int value, TestType test, SituationType situation,Item parentItem) {
        super(parentItem);
        this.num = num;
        this.value = value;
        this.test = test;
        this.situation = situation;
        this.eventTime = EventTimeType.AFTER;

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
