package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;
import model.Item.boni.ItemBonus_SuccessMultiplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FineClothes extends Asset {

    public FineClothes() {
        super(ItemType.ITEM, 2);
    }

    @Override
    public String getId() {
        return "&fineClothes";
    }

    @Override
    public String getNameId() {
        return "${fine_clothes}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_SuccessMultiplier bonus1 = new ItemBonus_SuccessMultiplier(Collections.singletonList(6),2,TestType.INFLUENCE,SituationType.ACQUIRE_ASSETS,this);
        boni.add(bonus1);
        return boni;
    }


}
