package model.Item.conditions;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.Test;
import gamemechanics.choice.InformationChoice;
import model.Effect;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.*;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;


public class DeptCondition5 extends Condition {

    public DeptCondition5() {
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
        Effect effect = new And(new Or(new GainCondition(ConditionType.DARK_PACT,GameService.getInstance().getEncounteringInvestigator()),
                new AdvanceDoom(1)),
                new Discard(this));
        Test test = new Test(
                effect,
                SituationType.RECKONING
        );
        test.setStartText(ResourceUtil.get(getNameId().replace("}", "_5}"), "condition"));
        GameService.getInstance().addTest(test);
    }

}