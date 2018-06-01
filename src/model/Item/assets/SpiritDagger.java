package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;

import java.util.ArrayList;
import java.util.List;


public class SpiritDagger extends Asset {

    public SpiritDagger() {
        super(ItemType.ITEM_WEAPON_MAGICAL, 2);
    }

    @Override
    public String getId() {
        return "&spiritDagger";
    }

    @Override
    public String getNameId() {
        return "${spirit_dagger}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(2,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        ItemBonus_GainDice bonus2 = new ItemBonus_GainDice(1,TestType.WILL,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus2);
        return boni;
    }


}
