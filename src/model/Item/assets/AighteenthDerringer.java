package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.ItemBonus_DiceResult;

import java.util.ArrayList;
import java.util.List;


public class AighteenthDerringer extends Asset {

    public AighteenthDerringer() {
        super(ItemType.TRINKET_WEAPON, 1);
    }

    @Override
    public String getId() {
        return "&18Derringer";
    }

    @Override
    public String getNameId() {
        return "${18_derringer}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_DiceResult bonus1 = new ItemBonus_DiceResult(1,1,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        return boni;
    }


}