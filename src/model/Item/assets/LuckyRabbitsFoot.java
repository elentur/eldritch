package model.Item.assets;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_DiceResult;
import model.Item.boni.ItemBonus_RepeatRoll;

import java.util.ArrayList;
import java.util.List;


public class LuckyRabbitsFoot extends Asset {

    public LuckyRabbitsFoot() {
        super(ItemType.TRINKET, 1);
    }

    @Override
    public String getId() {
        return "&luckyRabbitsFoot";
    }

    @Override
    public String getNameId() {
        return "${lucky_rabbits_foot}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_RepeatRoll bonus1 = new ItemBonus_RepeatRoll(1,TestType.ALL,SituationType.ALL,this);
        boni.add(bonus1);
        return boni;
    }


}
