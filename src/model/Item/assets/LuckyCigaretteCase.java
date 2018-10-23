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


public class LuckyCigaretteCase extends Asset {

    public LuckyCigaretteCase() {
        super(ItemType.TRINKET, 2);
    }

    @Override
    public String getId() {
        return "&luckyCigaretteCase";
    }

    @Override
    public String getNameId() {
        return "${lucky_cigarette_case}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_DiceResult bonus1 = new ItemBonus_DiceResult(1,1,TestType.ALL,SituationType.ALL,this);
        bonus1.setPerRound(true);
        boni.add(bonus1);
        return boni;
    }


}
