package model.Item.assets;

import enums.*;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.ChooseSpace;
import model.effects.Discard;
import model.effects.LooseOrGainHealthSanity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AgencyQuarantine extends Asset {

    public AgencyQuarantine() {
        super(ItemType.SERVICE, 4);
    }

    @Override
    public String getId() {
        return "&agencyQuarantine";
    }

    @Override
    public String getNameId() {
        return "${agency_quarantine}";
    }

    @Override
    public List<Effect> getDrawEffects(Investigator investigator) {

      Effect chooseSpace = new ChooseSpace(new LooseOrGainHealthSanity(SpendType.HEALTH,-4, EffectSelector.ALL, FieldID.CHOSEN_FIELD,ItemType.MONSTER));

        Effect discard  = new Discard(this);

        return Arrays.asList(chooseSpace,discard);

    }

    @Override
    public List<ItemBonus> createBonus() {
     return new ArrayList<>();
    }


}
