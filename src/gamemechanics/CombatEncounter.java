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

import java.util.List;

@Getter
@Setter
@ToString(of = {"activeMonster", "investigator"})
public class CombatEncounter implements Encounter {
    private List<Monster> monsters;
    private Investigator investigator;
    private Monster activeMonster;
    private CombatPreparation attackPreparation;
    private CombatPreparation horrorPreparation;
    private Result result;
    private boolean isAttackCheck;

    private EventService eventService = new EventService();

    public CombatEncounter(List<Monster> monsters, Investigator investigator) {
        this.monsters = monsters;
        this.investigator = investigator;
        this.isAttackCheck =false;
    }

    public List<Monster> getAvailableMonster() {
        return monsters;
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

    public CombatPreparation prepareForAttack() {
        this.isAttackCheck =true;
        result = null;
        attackPreparation = new CombatPreparation(TestTyp.STRENGTH , investigator, activeMonster);
        return attackPreparation;
    }

    public Investigator getInvestigator() {
        return investigator;
    }

    public CombatPreparation prepareForHorrorCheck() {
        this.isAttackCheck =false;
        horrorPreparation = new CombatPreparation(TestTyp.WILL ,investigator, activeMonster);
        return horrorPreparation;
    }

    public void removeActiveMonster() {
        getAvailableMonster().remove(getActiveMonster());
        setActiveMonster(null);
    }

    public Result check() {
        if (  this.isAttackCheck) {
            return attackCheck();
        } else {
            return horrorCheck();
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
        result.setMinNumberOfSuccesses(getActiveMonster().getToughness());
        return result;
    }

    public int getMonsterLive() {
        if (result != null) {
            int v = activeMonster.getToughness() - result.getNumberOfSuccess();
            return v < 0 ? 0 : v;
        }
        return activeMonster.getToughness();
    }
}
