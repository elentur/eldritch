package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ItemBonus_RepeatRoll implements ItemBonus {
    public ItemBonus_RepeatRoll(int value, TestTyp test, SituationTyp situation) {
        this.value = value;
        this.test = test;
        this.situation = situation;
    }

    private EventTimeType eventTime= EventTimeType.WHILE;
    private int value;
    private TestTyp test;
    private SituationTyp situation;
    @Override
    public void execute() {

    }

}
