package model.Item;

import enums.ConditionTyp;
import enums.TestTyp;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ItemBonus_DiceResult implements ItemBonus {

    private int num;
    private int value;
    private TestTyp test;
    private ConditionTyp condition;
    @Override
    public void execute() {

    }
}
