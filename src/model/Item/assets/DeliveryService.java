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
import model.effects.Trade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DeliveryService extends Asset {

    public DeliveryService() {
        super(ItemType.SERVICE_TEAMWORK, 1);
    }

    @Override
    public String getId() {
        return "&deliveryService";
    }

    @Override
    public String getNameId() {
        return "${delivery_service}";
    }

    @Override
    public List<Effect> getDrawEffects(Investigator investigator) {
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect chooseSpace = new ChooseSpace( new Trade(inv,FieldID.CHOSEN_FIELD, TradeChoice.LEFT_TO_RIGHT));

        Effect discard = new Discard(this);

        return Arrays.asList(chooseSpace, discard);
    }

    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }


}
