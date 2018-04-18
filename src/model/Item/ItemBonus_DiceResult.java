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

@EqualsAndHashCode
public class ItemBonus_DiceResult extends ItemBonus {

    private int num;
    private int value;
    private TestTyp test;
    private SituationTyp situation;
    private EventTimeType eventTime =EventTimeType.WHILE;

    public ItemBonus_DiceResult(int num, int value, TestTyp test, SituationTyp situation) {
        this.num = num;
        this.value = value;
        this.test = test;
        this.situation = situation;
    }

    @Override
    public void execute() {

    }
    @Override
    public String getText() {
        return "";
    }
}
