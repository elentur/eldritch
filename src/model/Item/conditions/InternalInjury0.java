package model.Item.conditions;

import Service.GameService;
import enums.*;
import gamemechanics.Test;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.ItemChoice;
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


public class InternalInjury0 extends Condition {

    public InternalInjury0() {
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
        GameService.getInstance().addTest(test);
        if (!test.getResult().isSuccess()) {
            InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_0}"), "condition"),
                    Collections.singletonList(new And(new LooseOrGainHealthSanity(SpendType.HEALTH,-3,GameService.getInstance().getEncounteringInvestigator()),
                            new Discard(this))));
            GameService.getInstance().addChoice(choice);
        }

    }
}
