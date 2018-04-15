package gamemechanics;

import container.Result;
import model.Investigator;
import model.Monster;
import preparation.CombatPreparation;

import java.util.List;


public class CombatEncounter {
    private List<Monster> monsters;
    private Investigator investigator;
    private Monster activeMonster;

    public CombatEncounter(List<Monster> monsters, Investigator investigator) {
        this.monsters = monsters;
        this.investigator = investigator;
    }

    public List<Monster> getAvailableMonster(){
        return monsters;
    }

    public void setActiveMonster(Monster monster){
        activeMonster=monster;
    }


    public CombatPreparation prepareForCombat(){
        return new CombatPreparation();
    }

    public Result attackMonster(CombatPreparation preparation){

       SkillTest skillTest = new SkillTest( preparation.getTestTyp(),preparation.getModification());
       return skillTest.execute(investigator);
    }

}
