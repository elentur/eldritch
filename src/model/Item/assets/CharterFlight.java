package model.Item.assets;

import Service.GameService;
import enums.EffectSelector;
import enums.FieldID;
import enums.ItemType;
import enums.SpendType;
import gamemechanics.choice.InvestigatorChoice;
import model.Effect;
import model.Field;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CharterFlight extends Asset {

    public CharterFlight() {
        super(ItemType.SERVICE, 1);
    }

    @Override
    public String getId() {
        return "&charterFlight";
    }

    @Override
    public String getNameId() {
        return "${charter_flight}";
    }

    @Override
    public List<Effect> getDrawEffects() {
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect chooseSpace = new ChooseSpace(2, new Move(FieldID.CHOSEN_FIELD, inv));
        Effect discard = new Discard(this);

        return Arrays.asList(chooseSpace, discard);
    }

    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }


}
