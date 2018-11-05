package gamemechanics;

import Service.DiceRollerService;
import container.ItemContainer;
import container.Result;
import enums.ConditionType;
import enums.ItemType;
import enums.TestType;
import model.Item.Condition;
import model.Item.Investigator;

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

        DiceRollerService service = new DiceRollerService();
        if (typ.equals(TestType.NONE)) {
            return service.rollDice(0,  ConditionType.NONE);
        }
        if (inv == null) {
            return service.rollDice(1,  ConditionType.NONE);
        } else {
            skill = inv.getSkill(typ);
            int value = skill + mod < 1 ? 1 : skill + mod;
            ItemContainer<Condition> conditions = inv.getConditions();
            Condition condition = conditions.stream().filter(item->item.getSubType().equals(ItemType.BLESSED_CONDITION)
            ||item.getSubType().equals(ItemType.CURSED_CONDITION) ).findFirst().orElse(null);
            ConditionType conditionType;
           if(condition == null || condition.getSubType()==null){
               conditionType=ConditionType.NONE;
           }else if(condition.getSubType().equals(ItemType.BLESSED_CONDITION)){
               conditionType=ConditionType.BLESSED;
           }else if(condition.getSubType().equals(ItemType.CURSED_CONDITION)){
               conditionType=ConditionType.CURSED;
           }else{
               conditionType=ConditionType.NONE;
           }

            return service.rollDice(value, conditionType);


        }

    }
}
