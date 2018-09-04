package model.Item.conditions;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.Condition;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_DiceResult;

import java.util.ArrayList;
import java.util.List;


public class BlessedCondition1 extends Condition {

    public BlessedCondition1() {
        super(ItemType.BLESSED_CONDITION);
    }

    @Override
    public String getId() {
        return "&blessedCondition";
    }

    @Override
    public String getNameId() {
        return "${blessed_condition}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }


}
