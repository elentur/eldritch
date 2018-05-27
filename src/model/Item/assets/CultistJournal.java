package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.ItemBonus_GainDice;

import java.util.ArrayList;
import java.util.List;


public class CultistJournal extends Asset {

    public CultistJournal() {
        super(ItemType.ITEM_WEAPON_MAGICAL, 3);
    }

    @Override
    public String getId() {
        return "&cultistJournal";
    }

    @Override
    public String getNameId() {
        return "${cultists_journal}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(2,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        ItemBonus_GainDice bonus2 = new ItemBonus_GainDice(2,TestType.WILL,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus2);
        return boni;
    }


}
