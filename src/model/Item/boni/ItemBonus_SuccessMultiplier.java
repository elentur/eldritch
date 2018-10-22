package model.Item.boni;

import container.Die;
import container.Result;
import enums.*;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Bonus;
import model.Item.Item;
import model.Item.ItemBonus;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Setter
public class ItemBonus_SuccessMultiplier extends ItemBonus {
    private List<Integer> values;
    private BonusType bonusType = BonusType.SUCCESS_MULTIPLIER;
    private final int multiplier;


    public ItemBonus_SuccessMultiplier(List<Integer> values, int multiplier, TestType test, SituationType situation, Item parentItem) {
        super(parentItem);
        this.values = values;
        this.test = test;
        this.situation = situation;
        this.eventTime = EventTimeType.AFTER;
        this.multiplier=multiplier;
        this.passive = true;
    }



    @Override
    public void execute(Encounter encounter) {
        if (!isExecutable()) {
            return;
        }
        Result result = encounter.getResult();
        for(Die die : result){
            if(values.contains(die.getValue())){
                die.setMultiplier(multiplier);
            }
        }
    }

    @Override
    public String getText() {
        return ResourceUtil.get("${success_multiplier}", Bonus.class, values.toString() ,multiplier+"", test.getSymbol(), situation.getText());
    }
}
