package gamemechanics;

import Service.DiceRollerService;
import container.Result;
import enums.ConditionType;
import enums.TestType;
import model.Investigator;

import java.util.List;


public class SkillTest {
    private TestType typ;
    private int mod;

    public SkillTest(TestType typ, int mod) {
        this.typ = typ;
        this.mod = mod;
        if (this.typ == null) {
            this.typ = TestType.NONE;
        }
    }

    public Result execute(Investigator inv) {
        int skill;
        ConditionType conditionTyp = ConditionType.NONE;
        DiceRollerService service = new DiceRollerService();
        if (inv == null) {
           skill = 1;
        }else {
            skill = inv.getSkill(typ);
            List<ConditionType> conditons = inv.getConditions();
            if (conditons.contains(ConditionType.BLESSED)) {
                conditionTyp = ConditionType.BLESSED;
            } else if (conditons.contains(ConditionType.CURSED)) {
                conditionTyp = ConditionType.CURSED;
            }
        }

        int value = skill + mod<1?1:skill+mod;

        Result result=service.rollDice(value, conditionTyp);

        return result;
    }

}
