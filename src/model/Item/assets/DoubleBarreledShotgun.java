package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;
import model.Item.boni.ItemBonus_SuccessMultiplier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class DoubleBarreledShotgun extends Asset {

    public DoubleBarreledShotgun() {
        super(ItemType.ITEM_WEAPON, 4);
    }

    @Override
    public String getId() {
        return "&doubleBarreledShotgun";
    }

    @Override
    public String getNameId() {
        return "${double_barreled_shotgun}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(4,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        ItemBonus_SuccessMultiplier bonus2 = new ItemBonus_SuccessMultiplier(Collections.singletonList(6),2,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus2);
        return boni;
    }


}
