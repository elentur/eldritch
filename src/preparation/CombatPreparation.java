package preparation;

import Service.GameService;
import container.BonusContainer;
import container.ItemContainer;
import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import lombok.Getter;
import lombok.Setter;
import model.Investigator;
import model.Item.Item;
import model.Item.ItemBonus;
import model.Item.ItemBonus_AdditionalDice;
import model.Item.ItemBonus_GainDice;
import model.Monster;

@Getter
@Setter
public class CombatPreparation extends Preparation {


    private Monster monster;
    private int modification;


    public CombatPreparation(TestTyp testTyp, Investigator investigator, Monster monster) {
        super(testTyp,investigator,SituationTyp.COMBAT_ENCOUNTER);
        this.monster = monster;
        this.modification = testTyp.equals(TestTyp.STRENGTH)?monster.getStrengthTest():monster.getWillTest();

    }



    @Override
    public int getModificationForSkillTest() {
        return modification+super.getModificationForSkillTest();

    }











}
