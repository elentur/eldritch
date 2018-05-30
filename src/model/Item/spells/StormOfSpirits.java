package model.Item.spells;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.boni.ItemBonus;
import model.Item.boni.ItemBonus_SwitchSkill;

import java.util.ArrayList;
import java.util.List;

public class StormOfSpirits extends Spell {
    public StormOfSpirits() {
        super(ItemType.INCANTATION);
    }



    @Override
    public String getId() {
        return  "&stormOfSpirits";
    }

    @Override
    public String getNameId() {
        return "${storm_of_spirits}";
    }
    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_SwitchSkill bonus1 = new ItemBonus_SwitchSkill(SituationType.COMBAT_ENCOUNTER,TestType.STRENGTH,TestType.LORE,new ArrayList<>(),this);
        boni.add(bonus1);
        return boni;
    }
}
