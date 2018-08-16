package model.Item.boni;

import enums.BonusType;
import enums.EventTimeType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import model.Item.ItemBonus;

@Getter
public class ItemBonus_Null extends ItemBonus {
    private final static ItemBonus_Null instance = new ItemBonus_Null();
    private final SituationType situation = SituationType.NONE;
    private final EventTimeType eventTime= EventTimeType.NONE;
    private BonusType bonusType = BonusType.NONE;
    private ItemBonus_Null(){
        super(null);
    }

    @Override
    public void execute(Encounter encounter) {

    }

    @Override
    public TestType getTest() {
        return TestType.NONE;
    }

    @Override
    public String getText() {
        return "";
    }

    public static ItemBonus_Null value(){
        return instance;
    }




}
