package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;

import java.util.ArrayList;
import java.util.List;


public class CarbineRifle extends Asset {

    public CarbineRifle() {
        super(ItemType.ITEM_WEAPON, 3);
    }

    @Override
    public String getId() {
        return "&carbineRifle";
    }

    @Override
    public String getNameId() {
        return "${carbine_rifle}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(5,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        bonus1.setPerRound(true);
        boni.add(bonus1);
        return boni;
    }


}
