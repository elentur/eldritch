package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.encounter.OtherWorldEncounter;
import javafx.beans.value.ChangeListener;
import model.Effect;
import model.Item.Artifact;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.Item.boni.ItemBonus_GainDice;
import model.Item.boni.ItemBonus_PreventLossOfHealthOrSanity;
import model.Item.boni.ItemBonus_SuccessMultiplier;
import model.effects.GainClue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GlassOfMortlan extends Artifact {



    public GlassOfMortlan() {
        super(ItemType.ITEM_MAGICAL);
    }

    @Override
    public String getId() {
        return "&glassOfMortlan";
    }

    @Override
    public String getNameId() {
        return "${glass_of_mortlan}";
    }





    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_PreventLossOfHealthOrSanity bonus1 = new ItemBonus_PreventLossOfHealthOrSanity(1, SpendType.SANITY, SituationType.SPELL_EFFECT, RangeType.SELF, null, this);
        boni.add(bonus1);
        ItemBonus_SuccessMultiplier bonus2 = new ItemBonus_SuccessMultiplier(Collections.singletonList(6),2,TestType.ALL,SituationType.SPELL_EFFECT,this);
        boni.add(bonus2);
        return boni;
    }


}
