package model.Item.conditions;

import Service.GameService;
import enums.*;
import gamemechanics.Test;
import gamemechanics.choice.InformationChoice;
import model.Effect;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.Token;
import model.Item.boni.ItemBonus_Rest;
import model.effects.And;
import model.effects.Discard;
import model.effects.LooseOrGainHealthSanity;
import model.effects.Spend;
import oldVersion.elements.ClueToken;
import utils.RNG;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Hallucination2 extends Condition {

    public Hallucination2() {
        super(ItemType.HALLUCINATIONS_CONDITION);
    }

    @Override
    public String getId() {
        return "&hallucinationCondition";
    }

    @Override
    public String getNameId() {
        return "${hallucination_condition}";
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
        Test test = new Test(TestType.WILL, 0, 1, SituationType.RECKONING);
        GameService.getInstance().addTest(test);

        if (!test.getResult().isSuccess()) {
            InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_2}"), "condition"),
                    Collections.singletonList(new And(
                            new Spend(SpendType.CLUE,inv.getClues().size(), inv),
                            new Discard(this))));

            GameService.getInstance().addChoice(choice);
        }

    }

}
