package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_DiceResult;

import java.util.ArrayList;
import java.util.List;


public class PocketWatch extends Asset {

    public PocketWatch() {
        super(ItemType.TRINKET, 1);
    }

    @Override
    public String getId() {
        return "&pocketWatch";
    }

    @Override
    public String getNameId() {
        return "${pocket_watch}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        //TODO
        return boni;
    }


}
