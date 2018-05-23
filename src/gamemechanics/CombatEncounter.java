package gamemechanics;

import Service.EventService;
import container.Result;
import enums.TestTyp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Investigator;
import model.Monster;
import preparation.CombatPreparation;
import preparation.Preparation;

import java.util.List;

@Getter
@Setter
@ToString(of = {"activeMonster", "investigator"})
public class CombatEncounter extends Encounter {
    private List<Monster> monsters;

    private Monster activeMonster;
    private CombatPreparation attackPreparation;
    private CombatPreparation horrorPreparation;



    private EventService eventService = new EventService();

    public CombatEncounter(List<Monster> monsters, Investigator investigator) {
        this.monsters = monsters;
        this.investigator = investigator;
    }

    public List<Monster> getAvailableMonster() {
        return monsters;
    }

    public  void setActiveMonster(Monster monster){
        activeMonster=monster;
        encounterPart =1;
        attackPreparation=null;
        horrorPreparation=null;
    }

    public void sanityLoss() {
        eventService.looseSanity(investigator, activeMonster.getHorror() - result.getNumberOfSuccess());
    }
    public void healthLoss() {
        eventService.looseHealth(investigator, activeMonster.getDamage() - result.getNumberOfSuccess());
    }
    public void monsterDamage() {
        eventService.looseHealth(activeMonster, result.getNumberOfSuccess());
    }

    @Override
    public Preparation getPreparation(){

       if (  encounterPart ==1)  {
            return prepareForHorrorCheck();
        }else{
           return prepareForAttack();
       }

    }


    private CombatPreparation prepareForAttack() {
        if(attackPreparation==null) {
            result = null;
            attackPreparation = new CombatPreparation(TestTyp.STRENGTH, investigator, activeMonster);
        }
        return attackPreparation;
    }

    public Investigator getInvestigator() {
        return investigator;
    }

    private CombatPreparation prepareForHorrorCheck() {
        if(horrorPreparation==null) {
            horrorPreparation = new CombatPreparation(TestTyp.WILL, investigator, activeMonster);
        }
        return horrorPreparation;
    }

    public void removeActiveMonster() {
        getAvailableMonster().remove(getActiveMonster());
        setActiveMonster(null);
    }

    @Override
    public Result check() {
        if (  encounterPart==1) {
            return horrorCheck();
        } else {
            return attackCheck();
        }
    }

    private Result horrorCheck( ) {

        SkillTest skillTest = new SkillTest(horrorPreparation.getTestTyp(), horrorPreparation.getModificationForSkillTest());
        result = skillTest.execute(investigator);
        result.setMinNumberOfSuccesses(getActiveMonster().getHorror());
        return result;
    }

    private Result attackCheck( ) {

        SkillTest skillTest = new SkillTest(attackPreparation.getTestTyp(), attackPreparation.getModificationForSkillTest());
        result = skillTest.execute(investigator);
        result.setMinNumberOfSuccesses(getActiveMonster().getDamage());
        return result;
    }

    public int getMonsterLive() {
        if (result != null) {
            int v = activeMonster.getToughness() - result.getNumberOfSuccess();
            return v < 0 ? 0 : v;
        }
        return activeMonster.getToughness();
    }

    @Override
    public int completeEncounterPart(){
        if(encounterPart==2) {
            healthLoss();
            monsterDamage();
            removeActiveMonster();
            encounterPart=0;
            return encounterPart;
        }else{
            sanityLoss();
            return super.completeEncounterPart();
        }

    }
}
