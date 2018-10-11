package model.Item.boni;

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
public class ItemBonus_AdditionalDice extends ItemBonus {

    public static final ItemBonus_AdditionalDice EMPTY = new ItemBonus_AdditionalDice(0, TestType.NONE, SituationType.NONE,RangeType.NONE,FieldType.NONE,null);
    private int value;
    private BonusType bonusType = BonusType.ADDITIONAL_DICE;
    public ItemBonus_AdditionalDice(int value, TestType test, SituationType situation, RangeType range, FieldType field,Item parentItem) {
        super(parentItem);
        this.value = value;
        this.test = test;
        this.situation = situation;
        this.range=range;
        this.field=field;
        this.eventTime = EventTimeType.BEFORE;
    }

    @Override
    public void execute(Encounter encounter) {
        if(!isExecutable()){
            return;
        }
        encounter.getPreparation().getAdditionalDiceBoni().add(this);
        if(isPerRound()){
            setUsable(false);
        }
        super.execute(encounter);

    }
    @Override
    public String getText() {
        return ResourceUtil.get("${additionalDice}",Bonus.class,value+"",  situation.getText() );
    }

}
