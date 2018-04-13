package model.Item;

import enums.ConditionTyp;
import enums.TestTyp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemBonus_Gain implements ItemBonus {

    private int value;
    private TestTyp test;
    private ConditionTyp condition;

    @Override
    public void execute() {

    }
}
