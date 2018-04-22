package model.Item;

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
@EqualsAndHashCode
public class ItemBonus_RepeatRoll extends ItemBonus {
    private EventTimeType eventTime= EventTimeType.AFTER;
    private int value;
    private TestTyp test;
    private SituationTyp situation;


    public ItemBonus_RepeatRoll(int value, TestTyp test, SituationTyp situation) {
        this.value = value;
        this.test = test;
        this.situation = situation;
    }


    @Override
    public void execute(Encounter encounter) {


    }
    @Override
    public String getText() {
        return ResourceUtil.get("${repeatRoll}",Bonus.class,value+"", test.getText(), situation.getText() );
    }
}
