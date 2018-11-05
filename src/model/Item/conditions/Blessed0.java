package model.Item.conditions;

import Service.GameService;
import enums.ItemType;
import enums.SpendType;
import gamemechanics.choice.InformationChoice;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.ItemBonus;
import model.effects.*;
import oldVersion.gameBuild.Game;
import utils.RNG;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Blessed0 extends Condition {

    public Blessed0() {
        super(ItemType.BLESSED_CONDITION);
    }

    @Override
    public String getId() {
        return "&blessedCondition";
    }


    @Override
    public String getNameId() {
        return "${blessed_condition}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }



    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        super.executeReckoning(inv, autoFail);
       if(RNG.getInt(6)<=1) {
           InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_info}"), "condition"), Collections.singletonList(new Discard(this)));
           GameService.getInstance().addChoice(choice);

       }
    }

    @Override
    public void doubleEffect() {
        super.doubleEffect();
        InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_0}"), "condition"),
                Collections.singletonList(new And(new LooseOrGainHealthSanity(SpendType.HEALTH,2, GameService.getInstance().getEncounteringInvestigator()),
                        new LooseOrGainHealthSanity(SpendType.SANITY,2, GameService.getInstance().getEncounteringInvestigator()))));
        GameService.getInstance().addChoice(choice);

    }
}
