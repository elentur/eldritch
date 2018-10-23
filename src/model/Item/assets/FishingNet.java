package model.Item.assets;

import enums.*;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.Item.boni.ItemBonus_ChangeMonsterDamage;
import model.Item.boni.ItemBonus_RepeatRoll;

import java.util.ArrayList;
import java.util.List;


public class FishingNet extends Asset {

    public FishingNet() {
        super(ItemType.ITEM, 2);
    }

    @Override
    public String getId() {
        return "&fishingNet";
    }

    @Override
    public String getNameId() {
        return "${fishing_net}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_RepeatRoll bonus1 = new ItemBonus_RepeatRoll(1, TestType.STRENGTH, SituationType.COMBAT_ENCOUNTER, this);
        boni.add(bonus1);
        ItemBonus_ChangeMonsterDamage bonus2 = new ItemBonus_ChangeMonsterDamage(1, SituationType.COMBAT_ENCOUNTER, this);
        boni.add(bonus2);
        return boni;
    }


}
