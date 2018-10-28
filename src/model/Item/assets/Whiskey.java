package model.Item.assets;

import enums.ItemType;
import enums.RangeType;
import enums.SituationType;
import enums.SpendType;
import gamemechanics.Test;
import model.Effect;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_PreventLossOfHealthOrSanity;
import model.effects.Discard;
import model.effects.LooseOrGainHealthSanity;

import java.util.ArrayList;
import java.util.List;


public class Whiskey extends Asset {

    private ItemBonus_PreventLossOfHealthOrSanity bonus;

    public Whiskey() {
        super(ItemType.ITEM, 1);
    }

    @Override
    public String getId() {
        return "&whiskey";
    }

    @Override
    public String getNameId() {
        return "${whiskey}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        Test test = new Test( new Discard(this), SituationType.TEST);
        bonus = new ItemBonus_PreventLossOfHealthOrSanity(2, SpendType.SANITY, SituationType.ALL, RangeType.LOCAL, test, this);
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