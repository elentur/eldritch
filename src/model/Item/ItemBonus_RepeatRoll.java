package model.Item;

import container.Result;
import enums.BonusType;
import enums.EventTimeType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.Encounter;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

@Getter
@Setter
public class ItemBonus_RepeatRoll extends ItemBonus {
    private int value;
    private BonusType bonusType = BonusType.REPEAT_ROLL;


    public ItemBonus_RepeatRoll(int value, TestType test, SituationType situation,Item parentItem) {
        super(parentItem);
        this.value = value;
        this.test = test;
        this.situation = situation;
        this.eventTime= EventTimeType.AFTER;
    }


    @Override
    public void execute(Encounter encounter) {
    Result result = encounter.getResult();
        if(!result.getFails().isEmpty()){
            result.setReroll(value);
        }
    }
    @Override
    public String getText() {
        return ResourceUtil.get("${repeatRoll}",Bonus.class,value+"", test.getText(), situation.getText() );
    }
}
