package gamemechanics;

import Service.EventService;
import container.Result;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Investigator;
import model.Monster;
import preparation.CombatPreparation;
import preparation.HorrorPreparation;
import preparation.Preparation;

import java.util.List;

@Getter
@Setter
@ToString(of = {"activeMonster", "investigator"})
public class CombatEncounter implements Encounter {
    private List<Monster> monsters;
    private Investigator investigator;
    private Monster activeMonster;
    private CombatPreparation combatPreparation;
    private HorrorPreparation horrorPreparation;
    private Result result;

    private EventService eventService = new EventService();

    public CombatEncounter(List<Monster> monsters, Investigator investigator) {
        this.monsters = monsters;
        this.investigator = investigator;
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

    public CombatPreparation prepareForCombat() {

        result = null;
        combatPreparation = new CombatPreparation(investigator, activeMonster);
        return combatPreparation;
    }

    public Result horrorCheck(HorrorPreparation preparation) {

        SkillTest skillTest = new SkillTest(preparation.getTestTyp(), preparation.getModificationForSkillTest());
        result = skillTest.execute(investigator);
        result.setMinNumberOfSuccesses(getActiveMonster().getHorror());
        return result;
    }

    public Result attackMonster(CombatPreparation preparation) {

        SkillTest skillTest = new SkillTest(preparation.getTestTyp(), preparation.getModificationForSkillTest());
        result = skillTest.execute(investigator);
        result.setMinNumberOfSuccesses(getActiveMonster().getToughness());
        return result;
    }


    public Investigator getInvestigator() {
        return investigator;
    }

    public HorrorPreparation prepareForHorrorCheck() {

        horrorPreparation = new HorrorPreparation(investigator, activeMonster);
        return horrorPreparation;
    }

    public void removeActiveMonster() {
        getAvailableMonster().remove(getActiveMonster());
        setActiveMonster(null);
    }

    public Result check(Preparation preparation) {
        if (preparation instanceof CombatPreparation) {
            return attackMonster((CombatPreparation) preparation);
        } else {
            return horrorCheck((HorrorPreparation) preparation);
        }
    }

    public int getMonsterLive() {
        if (result != null) {
            int v = activeMonster.getToughness() - result.getNumberOfSuccess();
            return v < 0 ? 0 : v;
        }
        return activeMonster.getToughness();
    }
}
