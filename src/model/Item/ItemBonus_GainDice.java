package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import gamemechanics.Encounter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;


@Getter
@Setter
public class ItemBonus_GainDice extends ItemBonus {

    public static final ItemBonus_GainDice EMPTY = new ItemBonus_GainDice(0, TestTyp.NONE, SituationTyp.NONE);
    private int value;

    public ItemBonus_GainDice(int value, TestTyp test, SituationTyp situation) {
        this.value = value;
        this.test = test;
        this.situation = situation;
    }

    @Override
    public void execute(Encounter encounter) {

    }
    @Override
    public String getText() {
        return ResourceUtil.get("${gainDice}",Bonus.class,value+"", test.getText(), situation.getText() );
    }

}
