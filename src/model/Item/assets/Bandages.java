package model.Item.assets;

import Service.GameService;
import enums.*;
import gamemechanics.Test;
import gamemechanics.choice.Choice;
import gamemechanics.choice.YesNoChoice;
import model.Effect;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;
import model.Item.boni.ItemBonus_PreventLossOfHealthOrSanity;
import model.Item.boni.ItemBonus_RepeatRoll;
import model.effects.Discard;
import model.effects.LooseOrGainHealthSanity;
import model.effects.Spend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Bandages extends Asset {

    private ItemBonus_PreventLossOfHealthOrSanity bonus;

    public Bandages() {
        super(ItemType.ITEM, 1);
    }

    @Override
    public String getId() {
        return "&bandages";
    }

    @Override
    public String getNameId() {
        return "${bandages}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        Test test = new Test( new Discard(this), SituationType.TEST);
        bonus = new ItemBonus_PreventLossOfHealthOrSanity(2, SpendType.HEALTH, SituationType.ALL, RangeType.LOCAL, test, this);
        return new ArrayList<>();
    }
    @Override
    public List<Effect> getDrawEffects() {
        LooseOrGainHealthSanity.listener.add(bonus);
        return super.getDrawEffects();
    }

    @Override
    public void discard() {
        super.discard();
        LooseOrGainHealthSanity.listener.remove(bonus);
    }

}
