package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;
import model.Item.boni.ItemBonus_RepeatRoll;

import java.util.ArrayList;
import java.util.List;


public class HolyCross extends Asset {

    public HolyCross() {
        super(ItemType.ITEM, 2);
    }

    @Override
    public String getId() {
        return "&holyCross";
    }

    @Override
    public String getNameId() {
        return "${holy_cross}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(2,TestType.WILL,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        return boni;
    }


}
