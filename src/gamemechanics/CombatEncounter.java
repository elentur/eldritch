package gamemechanics;

import container.Result;
import lombok.ToString;
import model.Investigator;
import model.Monster;
import preparation.CombatPreparation;
import preparation.HorrorPreparation;

import java.util.List;

@ToString(of ={"activeMonster","investigator"})
public class CombatEncounter  {
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
        return new CombatPreparation(investigator);
    }

    public Result horrorCheck(HorrorPreparation preparation){

        SkillTest skillTest = new SkillTest( preparation.getTestTyp(),preparation.getModification());
        return skillTest.execute(investigator);
    }

    public Result attackMonster(CombatPreparation preparation){

       SkillTest skillTest = new SkillTest( preparation.getTestTyp(),preparation.getModification());
       return skillTest.execute(investigator);
    }

    public Monster getActiveMonster() {
        return activeMonster;
    }

    public Investigator getInvestigator() {
        return investigator;
    }
}
