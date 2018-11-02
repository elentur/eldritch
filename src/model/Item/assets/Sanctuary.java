package model.Item.assets;

import Service.GameService;
import enums.FieldID;
import enums.ItemType;
import gamemechanics.choice.ItemChoice;
import gamemechanics.choice.TradeChoice;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.ChooseSpace;
import model.effects.Discard;
import model.effects.Trade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Sanctuary extends Asset {

    public Sanctuary() {
        super(ItemType.SERVICE, 2);
    }

    @Override
    public String getId() {
        return "&sanctuary";
    }

    @Override
    public String getNameId() {
        return "${sanctuary}";
    }

    @Override
    public List<Effect> getDrawEffects() {
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect discardCondition = new Discard( new ItemChoice(1, Collections.singletonList(ItemType.CONDITION),inv.getInventory()));

        Effect discard = new Discard(this);

        return Arrays.asList(discardCondition, discard);
    }

    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }


}
