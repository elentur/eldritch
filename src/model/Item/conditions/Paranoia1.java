package model.Item.conditions;

import Service.GameService;
import enums.*;
import gamemechanics.Test;
import gamemechanics.choice.InformationChoice;
import model.Effect;
import model.Item.*;
import model.Item.boni.ItemBonus_Rest;
import model.effects.Discard;
import model.effects.GainAsset;
import model.effects.GainCondition;
import model.effects.LooseOrGainHealthSanity;
import oldVersion.gameBuild.Game;
import utils.RNG;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Paranoia1 extends Condition {

    public Paranoia1() {
        super(ItemType.PARANOIA_CONDITION);
    }

    @Override
    public String getId() {
        return "&paranoiaCondition";
    }

    @Override
    public String getNameId() {
        return "${paranoia_condition}";
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
        test.setStartText(ResourceUtil.get(getNameId().replace("}", "_1}"), "condition"));
        GameService.getInstance().addTest(test);
            if (test.getResult().isSuccess()) {
                InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_1}"), "condition"),null);
                GameService.getInstance().addChoice(choice);
                Asset asset = GameService.getInstance().getAssets().showFirst();
                test = new Test(TestType.OBSERVATION, 0, asset.getPrice(), SituationType.RECKONING);
                test.setStartText(ResourceUtil.get(getNameId().replace("}", "_1a}"), "condition"));
                GameService.getInstance().addTest(test);
                if(test.getResult().isSuccess()){
                    GameService.getInstance().addEffect(new GainAsset(GameService.getInstance().getAssets().draw(), inv));
                }else{
                    GameService.getInstance().addEffect(new GainCondition(ConditionType.DETAINED,inv));
                }
                GameService.getInstance().addEffect(new Discard(this));
            }


    }

}
