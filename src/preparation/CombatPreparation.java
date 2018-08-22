package preparation;

import enums.SituationType;
import enums.TestType;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;
import model.Item.Monster;

@Getter
@Setter
public class CombatPreparation extends Preparation {


    private Monster monster;



    public CombatPreparation(TestType testTyp, Investigator investigator, Monster monster,Encounter encounter) {
        super(testTyp,investigator,SituationType.COMBAT_ENCOUNTER,  encounter);
        this.monster = monster;
       //setModification(testTyp.equals(TestType.STRENGTH)?monster.getStrengthTest():monster.getWillTest());

    }















}
