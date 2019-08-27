package model.Item.conditions;

import Service.GameService;
import enums.*;
import gamemechanics.Test;
import gamemechanics.choice.InformationChoice;
import model.Effect;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_Rest;
import model.effects.And;
import model.effects.Discard;
import model.effects.LooseOrGainHealthSanity;
import utils.RNG;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class InternalInjury1 extends Condition {

    public InternalInjury1() {
        super(ItemType.INTERNAL_INJURY_CONDITION);
    }

    @Override
    public String getId() {
        return "&internalInjuryCondition";
    }


    @Override
    public String getNameId() {
        return "${internal_injury_condition}";
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
        test.setStartText( ResourceUtil.get(getNameId().replace("}", "_1}"), "condition"));
        GameService.getInstance().addTest(test);
        if (!test.getResult().isSuccess()) {
            Effect effect = new LooseOrGainHealthSanity(SpendType.HEALTH,-1,inv);
            InformationChoice choice = new InformationChoice(getName(),effect.getText(),
                    Collections.singletonList(effect));
            GameService.getInstance().addChoice(choice);
        }

    }
}