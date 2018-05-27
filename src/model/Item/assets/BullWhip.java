package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.ItemBonus_GainDice;
import model.Item.ItemBonus_RepeatRoll;

import java.util.ArrayList;
import java.util.List;


public class BullWhip extends Asset {

    public BullWhip() {
        super(ItemType.ITEM_WEAPON, 1);
    }

    @Override
    public String getId() {
        return "&bullWhip";
    }

    @Override
    public String getNameId() {
        return "${bull_whip}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(1,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        ItemBonus_RepeatRoll bonus2 = new ItemBonus_RepeatRoll(1,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus2);
        return boni;
    }


}
