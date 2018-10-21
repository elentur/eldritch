package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;
import model.Item.boni.ItemBonus_RepeatRoll;

import java.util.ArrayList;
import java.util.List;


public class PrivateInvestigator extends Asset {

    public PrivateInvestigator() {
        super(ItemType.ALLEY, 2);
    }

    @Override
    public String getId() {
        return "&privateInvestigator";
    }

    @Override
    public String getNameId() {
        return "${private_investigator}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(1,TestType.OBSERVATION,SituationType.ALL,this);
        boni.add(bonus1);

        ItemBonus_RepeatRoll bonus2 = new ItemBonus_RepeatRoll(1,TestType.OBSERVATION,SituationType.ALL,this);
        boni.add(bonus2);
        return boni;
    }


}
