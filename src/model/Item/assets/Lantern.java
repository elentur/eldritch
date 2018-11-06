package model.Item.assets;

import enums.*;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.Item.boni.ItemBonus_ChangeMonsterHorror;

import java.util.ArrayList;
import java.util.List;


public class Lantern extends Asset {

    public Lantern() {
        super(ItemType.ITEM, 1);
    }

    @Override
    public String getId() {
        return "&lantern";
    }

    @Override
    public String getNameId() {
        return "${lantern}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_AdditionalDice bonus1 = new ItemBonus_AdditionalDice(1, TestType.ALL, SituationType.ALL, RangeType.LOCAL, FieldType.ALL, this);
        bonus1.setPerRound(true);
        boni.add(bonus1);
        return boni;
    }


}
