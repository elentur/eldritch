package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.boni.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;

import java.util.ArrayList;
import java.util.List;


public class ThirtyAightRevolver extends Asset {

    public ThirtyAightRevolver() {
        super(ItemType.ITEM_WEAPON, 1);
    }

    @Override
    public String getId() {
        return "&38Revolver";
    }

    @Override
    public String getNameId() {
        return "${38_revolver}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(2,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        return boni;
    }


}
