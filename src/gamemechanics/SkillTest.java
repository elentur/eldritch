package gamemechanics;

import Service.DiceRollerService;
import container.Result;
import enums.ConditionTyp;
import enums.TestTyp;
import model.Investigator;

import java.util.List;


public class SkillTest {
    private TestTyp typ;
    private int mod;

    public SkillTest(TestTyp typ, int mod) {
        this.typ = typ;
        this.mod = mod;
        if (this.typ == null) {
            this.typ = TestTyp.NONE;
        }
    }

    public Result execute(Investigator inv) {
        int skill;
        ConditionTyp conditionTyp = ConditionTyp.NONE;
        DiceRollerService service = new DiceRollerService();
        if (inv == null) {
           skill = 1;
        }else {
            skill = inv.getSkill(typ);
            List<ConditionTyp> conditons = inv.getConditions();
            if (conditons.contains(ConditionTyp.BLESSED)) {
                conditionTyp = ConditionTyp.BLESSED;
            } else if (conditons.contains(ConditionTyp.CURSED)) {
                conditionTyp = ConditionTyp.CURSED;
            }
        }

        int value = skill + mod;
        return service.rollDice(value, conditionTyp);
    }

}
