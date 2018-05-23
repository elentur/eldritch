package model.Item;

import enums.*;
import gamemechanics.Encounter;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;


@Getter
@Setter
public class ItemBonus_AdditionalDice extends ItemBonus {

    public static final ItemBonus_AdditionalDice EMPTY = new ItemBonus_AdditionalDice(0, TestTyp.NONE, SituationTyp.NONE,RangeType.NONE,FieldType.NONE);
    private int value;

    public ItemBonus_AdditionalDice(int value, TestTyp test, SituationTyp situation,RangeType range,FieldType field) {
        this.value = value;
        this.test = test;
        this.situation = situation;
        this.range=range;
        this.field=field;
    }

    @Override
    public void execute(Encounter encounter) {

    }
    @Override
    public String getText() {
        return ResourceUtil.get("${additionalDice}",Bonus.class,value+"", test.getText(), situation.getText() );
    }

}
