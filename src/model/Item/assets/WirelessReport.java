package model.Item.assets;

import Service.GameService;
import enums.ItemType;
import gamemechanics.choice.ItemChoice;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.Discard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class WirelessReport extends Asset {

    public WirelessReport() {
        super(ItemType.SERVICE_TEAMWORK, 1);
    }

    @Override
    public String getId() {
        return "&wirelessReport";
    }

    @Override
    public String getNameId() {
        return "${wireless_report}";
    }

    @Override
    public List<Effect> getDrawEffects() {
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect discardCondition = new Discard( new ItemChoice(true, Collections.singletonList(ItemType.CONDITION),inv.getInventory()));

        Effect discard = new Discard(this);

        return Arrays.asList(discardCondition, discard);
    }

    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }


}
