package gamemechanics;

import container.Result;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Investigator;
import model.Monster;
import preparation.CombatPreparation;
import preparation.HorrorPreparation;

import java.util.List;

@Getter
@Setter
@ToString(of ={"activeMonster","investigator"})
public class CombatEncounter implements Encounter {
    private List<Monster> monsters;
    private Investigator investigator;
    private Monster activeMonster;
    private CombatPreparation combatPreparation;
    private HorrorPreparation horrorPreparation;

    public CombatEncounter(List<Monster> monsters, Investigator investigator) {
        this.monsters = monsters;
        this.investigator = investigator;
    }

    public List<Monster> getAvailableMonster(){
        return monsters;
    }




    public CombatPreparation prepareForCombat(){
         combatPreparation= new CombatPreparation(investigator,activeMonster);
         return combatPreparation;
    }

    public Result horrorCheck(HorrorPreparation preparation){

        SkillTest skillTest = new SkillTest( preparation.getTestTyp(),preparation.getModification());
       Result result = skillTest.execute(investigator);
       result.setNum(getActiveMonster().getHorror());
       return  result;
    }

    public Result attackMonster(CombatPreparation preparation){

       SkillTest skillTest = new SkillTest( preparation.getTestTyp(),preparation.getModification());
        Result result = skillTest.execute(investigator);
        result.setNum(getActiveMonster().getToughness());
        return  result;
    }



    public Investigator getInvestigator() {
        return investigator;
    }

    public HorrorPreparation prepareForHorrorCheck() {
        horrorPreparation = new HorrorPreparation(investigator,activeMonster);
        return horrorPreparation;
    }

    public void removeActiveMonster() {
      getAvailableMonster().remove(getActiveMonster());
      setActiveMonster(null);
    }
}
