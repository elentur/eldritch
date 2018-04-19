package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
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
    public ItemBonus_RepeatRoll(int value, TestTyp test, SituationTyp situation) {
        this.value = value;
        this.test = test;
        this.situation = situation;
    }

    private EventTimeType eventTime= EventTimeType.AFTER;
    private int value;
    private TestTyp test;
    private SituationTyp situation;
    @Override
    public void execute(Object object) {

    }
    @Override
    public String getText() {
        return ResourceUtil.get("${repeatRoll}",Bonus.class,value+"", test.getText(), situation.getText() );
    }
}
