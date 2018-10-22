package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;

import java.util.ArrayList;
import java.util.List;


public class FourtyFiveAutomatic extends Asset {

    public FourtyFiveAutomatic() {
        super(ItemType.ITEM_WEAPON, 2);
    }

    @Override
    public String getId() {
        return "&45Automatic";
    }

    @Override
    public String getNameId() {
        return "${45_automatic}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(3,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        return boni;
    }


}
