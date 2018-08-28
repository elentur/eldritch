package model.Item.spells;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.ItemBonus;
import model.Item.Spell;
import model.Item.SpellConsequence;
import model.Item.boni.ItemBonus_SwitchSkill;

import java.util.ArrayList;
import java.util.List;

public class Wither extends Spell {
    public Wither() {
        super(ItemType.INCANTATION);

    }



    @Override
    public String getId() {
        return  "&wither";
    }

    @Override
    public String getNameId() {
        return "${wither}";
    }
    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_SwitchSkill bonus1 = new ItemBonus_SwitchSkill(SituationType.COMBAT_ENCOUNTER,TestType.STRENGTH,TestType.LORE,this);
        boni.add(bonus1);
        return boni;
    }

    @Override
    protected List<SpellConsequence> createConsequences() {
        return new ArrayList<>();
    }
}
