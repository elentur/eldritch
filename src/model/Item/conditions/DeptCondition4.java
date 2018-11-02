package model.Item.conditions;

import Service.GameService;
import enums.ItemType;
import enums.SituationType;
import enums.SpendType;
import enums.TestType;
import gamemechanics.Action;
import gamemechanics.Test;
import gamemechanics.choice.InformationChoice;
import model.Effect;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.And;
import model.effects.Discard;
import model.effects.LooseOrGainHealthSanity;
import model.effects.NullEffect;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;


public class DeptCondition4 extends Condition {

    public DeptCondition4() {
        super(ItemType.DEPT_CONDITION);
    }

    @Override
    public String getId() {
        return "&deptCondition";
    }


    @Override
    public String getNameId() {
        return "${dept_condition}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }


    @Override
    public Action getEncounter() {
        return new Action(GameService.getInstance().getEncounteringInvestigator(),
                "dept",
                new NullEffect(),
                new Discard(this),
                new NullEffect(),
                TestType.INFLUENCE,
                0,
                1,
                SituationType.ACTION);
    }

    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        super.executeReckoning(inv, autoFail);
        InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_info}"), "condition"), null);
        GameService.getInstance().addChoice(choice);
        Effect effect = new And(new LooseOrGainHealthSanity(SpendType.SANITY, -3, GameService.getInstance().getEncounteringInvestigator()),
                new Discard(this));
        Test test = new Test(
                TestType.WILL,
                0,
                1,
                SituationType.RECKONING
        );
        test.getEffect()[0][1] = effect;
        test.setStartText(ResourceUtil.get(getNameId().replace("}", "_4}"), "condition"));
        GameService.getInstance().addTest(test);
    }

}
