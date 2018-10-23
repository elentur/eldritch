package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.choice.YesNoChoice;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;
import model.effects.Discard;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Kerosene extends Asset {

    public Kerosene() {
        super(ItemType.ITEM, 1);
    }

    @Override
    public String getId() {
        return "&kerosene";
    }

    @Override
    public String getNameId() {
        return "${kerosene}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(5, TestType.STRENGTH, SituationType.COMBAT_ENCOUNTER, this);
        YesNoChoice choice = new YesNoChoice(getName(), ResourceUtil.get("${use_bonus_discard}", "ui",
                bonus1.getText(), getName()), Arrays.asList(new Discard(this)), null);
        bonus1.setCondition(choice);
        boni.add(bonus1);
        return boni;
    }


}
