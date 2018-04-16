package model.Item;

import enums.SituationTyp;

public class ItemBonus_Null implements ItemBonus{
    private final static ItemBonus_Null instance = new ItemBonus_Null();

    private ItemBonus_Null(){}
    @Override
    public void execute() {

    }

    public static ItemBonus_Null value(){
        return instance;
    }

    @Override
    public SituationTyp getSituation() {
        return SituationTyp.NONE;
    }
}
