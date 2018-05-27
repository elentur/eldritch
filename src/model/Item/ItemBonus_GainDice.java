package model.Item;

import enums.BonusType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.Encounter;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;


@Getter
@Setter
public class ItemBonus_GainDice extends ItemBonus {

    public static final ItemBonus_GainDice EMPTY = new ItemBonus_GainDice(0, TestType.NONE, SituationType.NONE,null);
    private int value;
    private BonusType bonusType = BonusType.GAIN_DICE;

    public ItemBonus_GainDice(int value, TestType test, SituationType situation,Item parentItem) {
        super(parentItem);
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
