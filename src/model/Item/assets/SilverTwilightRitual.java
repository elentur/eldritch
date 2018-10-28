package model.Item.assets;

import Service.GameService;
import enums.FieldID;
import enums.ItemType;
import gamemechanics.choice.TradeChoice;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.ChooseSpace;
import model.effects.Discard;
import model.effects.RetreatDoom;
import model.effects.Trade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SilverTwilightRitual extends Asset {

    public SilverTwilightRitual() {
        super(ItemType.SERVICE, 3);
    }

    @Override
    public String getId() {
        return "&silverTwilightRitual";
    }

    @Override
    public String getNameId() {
        return "${silver_twilight_ritual}";
    }

    @Override
    public List<Effect> getDrawEffects() {
        Effect retreatDoom = new RetreatDoom( 1);

        Effect discard = new Discard(this);

        return Arrays.asList(retreatDoom, discard);
    }

    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }


}