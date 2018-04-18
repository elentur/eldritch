package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class ItemBonus_GainDice extends ItemBonus {

    private int value;
    private TestTyp test;
    private SituationTyp situation;
    private EventTimeType eventTime = EventTimeType.BEFORE;

    public ItemBonus_GainDice(int value, TestTyp test, SituationTyp situation) {
        this.value = value;
        this.test = test;
        this.situation = situation;
    }

    @Override
    public void execute(Object object) {

    }
    @Override
    public String getText() {
        return "";
    }

}
