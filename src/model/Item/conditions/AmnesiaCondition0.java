package model.Item.conditions;

import enums.ItemType;
import model.Item.Condition;
import model.Item.ItemBonus;

import java.util.ArrayList;
import java.util.List;


public class AmnesiaCondition0 extends Condition {

    public AmnesiaCondition0() {
        super(ItemType.AMNESIA_CONDITION);
    }

    @Override
    public String getId() {
        return "&amnesiaCondition";
    }

    @Override
    public String getNameId() {
        return "${amnesia_condition}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }


}
