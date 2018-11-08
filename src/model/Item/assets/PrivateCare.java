package model.Item.assets;

import Service.GameService;
import enums.ItemType;
import enums.SpendType;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.Discard;
import model.effects.LooseOrGainHealthSanity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PrivateCare extends Asset {

    public PrivateCare() {
        super(ItemType.SERVICE, 2);
    }

    @Override
    public String getId() {
        return "&privateCare";
    }

    @Override
    public String getNameId() {
        return "${private_care}";
    }

    @Override
    public List<Effect> getDrawEffects(Investigator investigator) {
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect1  = new LooseOrGainHealthSanity(SpendType.HEALTH,inv.getHealth()-inv.getActualHealth(),inv);
        Effect effect2  = new LooseOrGainHealthSanity(SpendType.SANITY,inv.getSanity()-inv.getActualSanity(),inv);
        Effect effect3  = new Discard(this);

        return Arrays.asList(effect1,effect2,effect3);
    }

    @Override
    public List<ItemBonus> createBonus() {
     return new ArrayList<>();
    }


}
