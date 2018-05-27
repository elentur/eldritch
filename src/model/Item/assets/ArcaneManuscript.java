package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.ItemBonus_RepeatRoll;

import java.util.ArrayList;
import java.util.List;


public class ArcaneManuscript extends Asset {

    public ArcaneManuscript() {
        super(ItemType.ITEM_TOME, 1);
    }

    @Override
    public String getId() {
        return "&arcaneManuscripts";
    }

    @Override
    public String getNameId() {
        return "${arcane_manuscripts}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_RepeatRoll bonus1 = new ItemBonus_RepeatRoll(1,TestType.LORE,SituationType.SPELL_EFFECT,this);
        boni.add(bonus1);
        return boni;
    }


}
