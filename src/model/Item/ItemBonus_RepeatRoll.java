package model.Item;

import container.Result;
import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import gamemechanics.Encounter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

@Getter
@Setter
@AllArgsConstructor
public class ItemBonus_RepeatRoll extends ItemBonus {
    private int value;


    public ItemBonus_RepeatRoll(int value, TestTyp test, SituationTyp situation) {
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
