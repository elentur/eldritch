package model.Item.assets;

import Service.GameService;
import enums.*;
import gamemechanics.choice.Choice;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.Encounter;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;
import model.Item.boni.ItemBonus_RepeatRoll;
import model.effects.Spend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Axe extends Asset {

    public Axe() {
        super(ItemType.ITEM_WEAPON, 2);
    }

    @Override
    public String getId() {
        return "&Axe";
    }

    @Override
    public String getNameId() {
        return "${axe}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(2,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        ItemBonus_RepeatRoll bonus2 = new ItemBonus_RepeatRoll(EffectSelector.ALL,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        Choice condition = new YesNoChoice("",bonus2.getText(), Collections.singletonList(new Spend(SpendType.SANITY, 2, GameService.getInstance().getEncounteringInvestigator())),null );
        bonus1.setCondition(condition);
        boni.add(bonus2);
        return boni;
    }


}
