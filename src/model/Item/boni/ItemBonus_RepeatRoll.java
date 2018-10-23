package model.Item.boni;

import container.Result;
import enums.*;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Bonus;
import model.Item.Item;
import model.Item.ItemBonus;
import utils.ResourceUtil;

@Getter
@Setter
public class ItemBonus_RepeatRoll extends ItemBonus {
    private  EffectSelector selector;
    private int value;
    private BonusType bonusType = BonusType.REPEAT_ROLL;


    public ItemBonus_RepeatRoll(int value, TestType test, SituationType situation, Item parentItem) {
        super(parentItem);
        this.value = value;
        this.test = test;
        this.situation = situation;
        this.eventTime = EventTimeType.AFTER;
    }
    public ItemBonus_RepeatRoll(EffectSelector selector, TestType test, SituationType situation, Item parentItem) {
        super(parentItem);
        this.value = 0;
        this.test = test;
        this.situation = situation;
        this.eventTime = EventTimeType.AFTER;
        this.selector =selector;
    }


    @Override
    public void execute(Encounter encounter) {
        if (!isExecutable()) {
            return;
        }
        if(selector!=null && selector.equals(EffectSelector.ALL)){
          value=  encounter.getResult().size();
        }
        Result result = encounter.getResult();
        if (!result.getFails().isEmpty()) {
            result.setReroll(value);
            super.execute(encounter);
        }
    }

    @Override
    public String getText() {
        return ResourceUtil.get("${repeat_roll}", Bonus.class, value + "", test.getSymbol(), situation.getText());
    }
}
