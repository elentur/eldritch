package model.Item.boni;

import enums.BonusType;
import enums.EffectSelector;
import enums.SituationType;
import enums.TestType;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Bonus;
import model.Item.Item;
import model.Item.ItemBonus;
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
        if(!isExecutable()){
            return;
        }
        if(isPerRound()){
            setUsable(false);
        }
        super.execute(encounter);
    }
    @Override
    public String getText() {
        return ResourceUtil.get("${gainDice}",Bonus.class,value+"", test.getSymbol(), situation.getText() );
    }

}
