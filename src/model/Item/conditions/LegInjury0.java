package model.Item.conditions;

import Service.GameService;
import enums.*;
import gamemechanics.Test;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.YesNoChoice;
import model.Effect;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_Rest;
import model.effects.And;
import model.effects.BecomeDelayed;
import model.effects.Discard;
import model.effects.LooseOrGainHealthSanity;
import utils.RNG;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LegInjury0 extends Condition {

    public LegInjury0() {
        super(ItemType.LEG_INJURY_CONDITION);
    }

    @Override
    public String getId() {
        return "&legInjuryCondition";
    }


    @Override
    public String getNameId() {
        return "${leg_injury_condition}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        Condition that = this;
        Effect effect = new Effect(EffectTyps.CUSTOM) {
            @Override
            public void execute() {
                super.execute();
                if(RNG.getInt(6)<=1) {
                    GameService.getInstance().addChoice(new InformationChoice(getName(),
                            ResourceUtil.get(getNameId().replace("}", "_info}"), "condition"),
                            Collections.singletonList(new Discard(that))));
                }
            }

            @Override
            public String getText() {
                return "";
            }
        };
        ItemBonus_Rest bonus1 = new ItemBonus_Rest(effect, RangeType.SELF,this);
        boni.add(bonus1);
        return boni;
    }



    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        super.executeReckoning(inv, autoFail);
        Test test = new Test(TestType.STRENGTH, 0, 1, SituationType.RECKONING);
        test.setStartText( ResourceUtil.get(getNameId().replace("}", "_0}"), "condition"));
        GameService.getInstance().addTest(test);
        if (!test.getResult().isSuccess()) {
            Effect effect0 = new LooseOrGainHealthSanity(SpendType.HEALTH,-1,inv);
            InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_0}"), "condition") + "\n" +effect0.getText(),
                    Collections.singletonList(
                            effect0));
            GameService.getInstance().addChoice(choice);

            Effect effect = new And(new BecomeDelayed(inv),new Discard(this));
            YesNoChoice condition = new YesNoChoice(getName(),effect.getText(),Collections.singletonList(effect),null);
            effect.setCondition(condition);
            GameService.getInstance().addEffect(effect);
        }

    }
}