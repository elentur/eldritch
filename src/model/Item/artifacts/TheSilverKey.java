package model.Item.artifacts;

import enums.*;
import model.Item.Artifact;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_PreventLossOfHealthOrSanity;
import model.Item.boni.ItemBonus_RepeatRoll;
import model.Item.boni.ItemBonus_SuccessMultiplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TheSilverKey extends Artifact {



    public TheSilverKey() {
        super(ItemType.ITEM_MAGICAL);
    }

    @Override
    public String getId() {
        return "&theSilverKey";
    }

    @Override
    public String getNameId() {
        return "${the_silver_key}";
    }





    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_RepeatRoll bonus1 = new ItemBonus_RepeatRoll(1, TestType.ALL,SituationType.OTHER_WORLD_ENCOUNTER, this);
        boni.add(bonus1);

        return boni;
    }


}
