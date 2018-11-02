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


public class DeptCondition1 extends Condition {

    public DeptCondition1() {
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
        Effect effect = new And(new GainCondition(ConditionType.AMNESIA, GameService.getInstance().getEncounteringInvestigator()),
                new Discard(this));
        Test test = new Test(
                TestType.LORE,
                0,
                1,
                SituationType.RECKONING
        );
        test.getEffect()[0][1] = effect;
        test.setStartText(ResourceUtil.get(getNameId().replace("}", "_1}"), "condition"));
        GameService.getInstance().addTest(test);
    }

}
