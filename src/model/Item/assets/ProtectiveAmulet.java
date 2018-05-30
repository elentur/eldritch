package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.boni.ItemBonus;
import model.Item.boni.ItemBonus_RepeatRoll;

import java.util.ArrayList;
import java.util.List;


public class ProtectiveAmulet extends Asset {

    public ProtectiveAmulet() {
        super(ItemType.ITEM, 1);
    }

    @Override
    public String getId() {
        return "&protectiveAmulet";
    }

    @Override
    public String getNameId() {
        return "${protective_amulet}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_RepeatRoll bonus1 = new ItemBonus_RepeatRoll(1,TestType.WILL,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        return boni;
    }


}
