package model.Item.assets;

import enums.*;
import model.Item.boni.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;

import java.util.ArrayList;
import java.util.List;


public class UrbanGuide extends Asset {

    public UrbanGuide() {
        super(ItemType.ALLEY, 4);
    }

    @Override
    public String getId() {
        return "&urbanGuide";
    }

    @Override
    public String getNameId() {
        return "${urban_guide}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_AdditionalDice bonus1 = new ItemBonus_AdditionalDice(1,TestType.ALL,SituationType.ALL,RangeType.LOCAL,FieldType.CITY,this);
        boni.add(bonus1);
        return boni;
    }


}
