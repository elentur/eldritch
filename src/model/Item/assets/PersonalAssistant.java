package model.Item.assets;

import enums.*;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.Item.boni.ItemBonus_RepeatRoll;

import java.util.ArrayList;
import java.util.List;


public class PersonalAssistant extends Asset {

    public PersonalAssistant() {
        super(ItemType.ALLEY, 4);
    }

    @Override
    public String getId() {
        return "&personalAssistant";
    }

    @Override
    public String getNameId() {
        return "${personal_assistant}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_AdditionalDice bonus1 = new ItemBonus_AdditionalDice(1,TestType.INFLUENCE,SituationType.ALL,RangeType.LOCAL,FieldType.ALL,this);
        boni.add(bonus1);

        ItemBonus_RepeatRoll bonus2 = new ItemBonus_RepeatRoll(1,TestType.INFLUENCE,SituationType.ALL,this);
        boni.add(bonus2);
        return boni;
    }


}
