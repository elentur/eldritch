package preparation;

import enums.SituationType;
import enums.TestType;
import lombok.Getter;
import lombok.Setter;
import model.Investigator;
import model.Monster;

@Getter
@Setter
public class CombatPreparation extends Preparation {


    private Monster monster;
    private int modification;


    public CombatPreparation(TestType testTyp, Investigator investigator, Monster monster) {
        super(testTyp,investigator,SituationType.COMBAT_ENCOUNTER);
        this.monster = monster;
        this.modification = testTyp.equals(TestType.STRENGTH)?monster.getStrengthTest():monster.getWillTest();

    }



    @Override
    public int getModificationForSkillTest() {
        return modification+super.getModificationForSkillTest();

    }











}
