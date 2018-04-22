package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import gamemechanics.Encounter;
import lombok.Getter;

@Getter
public class ItemBonus_Null extends ItemBonus{
    private final static ItemBonus_Null instance = new ItemBonus_Null();
    private final SituationTyp situation = SituationTyp.NONE;
    private final EventTimeType eventTime= EventTimeType.NONE;
    private ItemBonus_Null(){}

    @Override
    public void execute(Encounter encounter) {

    }

    @Override
    public TestTyp getTest() {
        return TestTyp.NONE;
    }

    @Override
    public String getText() {
        return "";
    }

    public static ItemBonus_Null value(){
        return instance;
    }




}
