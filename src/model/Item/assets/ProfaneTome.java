package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.boni.ItemBonus;
import model.Item.boni.ItemBonus_RepeatRoll;

import java.util.ArrayList;
import java.util.List;


public class ProfaneTome extends Asset {

    public ProfaneTome() {
        super(ItemType.TRINKET_WEAPON, 1);
    }

    @Override
    public String getId() {
        return "&profaneTome";
    }

    @Override
    public String getNameId() {
        return "${profane_tome}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_RepeatRoll bonus1 = new ItemBonus_RepeatRoll(1,TestType.WILL,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        return boni;
    }


}
