package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.SpendType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;
import model.Item.boni.ItemBonus_RepeatRoll;
import model.Item.boni.ItemBonus_Rest;
import model.effects.LooseOrGainHealthSanity;

import java.util.ArrayList;
import java.util.List;


public class KingJamesBible extends Asset {

    public KingJamesBible() {
        super(ItemType.ITEM_WEAPON, 1);
    }

    @Override
    public String getId() {
        return "&kingJamesBible";
    }

    @Override
    public String getNameId() {
        return "${king_james_bible}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_Rest bonus1 = new ItemBonus_Rest(new LooseOrGainHealthSanity(SpendType.SANITY,1,LooseOrGainHealthSanity.ENCOUNTERING_INVESTIGATOR),this);
        boni.add(bonus1);
        ItemBonus_RepeatRoll bonus2 = new ItemBonus_RepeatRoll(1,TestType.WILL,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus2);
        return boni;
    }


}
