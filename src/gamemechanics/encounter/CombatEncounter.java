package gamemechanics.encounter;

import Service.GameService;
import container.ItemContainer;
import container.Result;
import enums.*;
import gamemechanics.SkillTest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Field;
import model.Item.Bonus;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.Monster;
import model.effects.LooseOrGainHealthSanity;
import model.effects.NextInvestigator;
import preparation.CombatPreparation;
import preparation.Preparation;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@ToString(of = {"activeMonster", "investigator"})
public class CombatEncounter extends Encounter {
    private List<Monster> monsters;
    private Monster activeMonster;
    private Monster original;
    private CombatPreparation attackPreparation;
    private CombatPreparation horrorPreparation;


    public CombatEncounter(Monster monster, List<Monster> monsters, Investigator investigator) {
        super(EncounterType.COMBAT_ENCOUNTER);
        this.investigator = investigator;
        setGame(GameService.getInstance());
        this.monsters = monsters;
        this.setActiveMonster(monster);
        setSituationType(SituationType.COMBAT_ENCOUNTER);

    }

    @Override
    public void init() {
        super.init();

        setEncounterPart(1);


    }


    @Override
    public Encounter draw() {
        return this;
    }




    public void setActiveMonster(Monster monster) {
        original = monster;
        activeMonster = monster.clone();
        if (activeMonster == null) {
            return;
        }
        setMod(new int[]{0, activeMonster.getWillTest(), activeMonster.getStrengthTest()});
        setTestType(new TestType[]{TestType.NONE, TestType.WILL, TestType.STRENGTH});
        encounterPart = 1;
        attackPreparation = null;
        horrorPreparation = null;
    }

    private void sanityLoss() {
        if (result != null) {
            int dmg = activeMonster.getHorror() - result.getNumberOfSuccess();
            dmg = dmg < 0 ? 0 : dmg;
            if (dmg == 0) return;
            GameService.getInstance().addEffect(new LooseOrGainHealthSanity(SpendType.SANITY, -dmg, investigator));
        }
    }

    private void healthLoss() {
        if (result != null) {
            int dmg = activeMonster.getDamage() - result.getNumberOfSuccess();
            dmg = dmg < 0 ? 0 : dmg;
            if (dmg == 0) return;
            GameService.getInstance().addEffect(new LooseOrGainHealthSanity(SpendType.HEALTH, -dmg, investigator));
        }

    }

    private void monsterDamage() {
        if (result != null) {
            if (result.getNumberOfSuccess() == 0) return;
            GameService.getInstance().addEffect(new LooseOrGainHealthSanity(SpendType.HEALTH, -result.getNumberOfSuccess(), original));
        }
    }

    @Override
    public Preparation getPreparation() {

        if (encounterPart == 1) {
            return prepareForHorrorCheck();
        } else {
            return prepareForAttack();
        }

    }


    private CombatPreparation prepareForAttack() {
        if (attackPreparation == null) {
            result = null;
            TestType testType = TestType.STRENGTH;
            if(activeMonster.getDamage()==0){
                testType=TestType.NONE;
            }
            attackPreparation = new CombatPreparation(testType, investigator, activeMonster, this);
            setPreparation(attackPreparation);
        }
        return attackPreparation;
    }

    public Investigator getInvestigator() {
        return investigator;
    }

    private CombatPreparation prepareForHorrorCheck() {
        if (horrorPreparation == null) {
            TestType testType = TestType.WILL;
            if(activeMonster.getHorror()==0){
                testType=TestType.NONE;
            }
            horrorPreparation = new CombatPreparation(testType, investigator, activeMonster, this);
            setPreparation(horrorPreparation);
        }
        return horrorPreparation;
    }


    @Override
    public Result check() {
        if (encounterPart == 1) {
            return horrorCheck();
        } else {
            return attackCheck();
        }
    }

    private Result horrorCheck() {

        SkillTest skillTest = new SkillTest(horrorPreparation.getTestTyp(), horrorPreparation.getModificationForSkillTest());
        result = skillTest.execute(investigator);
        result.setMinNumberOfSuccesses(getActiveMonster().getHorror());
        activatePassiveBoni(EventTimeType.AFTER);
        return result;
    }

    private Result attackCheck() {

        SkillTest skillTest = new SkillTest(attackPreparation.getTestTyp(), attackPreparation.getModificationForSkillTest());
        result = skillTest.execute(investigator);
        result.setMinNumberOfSuccesses(getActiveMonster().getDamage());
        activatePassiveBoni(EventTimeType.AFTER);
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
    public int completeEncounterPart() {
        if (encounterPart == 2) {
            healthLoss();
            monsterDamage();
            GameService.getInstance().addEffect(activeMonster.getStrengthTestEffect());
        } else {
            sanityLoss();
            GameService.getInstance().addEffect(activeMonster.getWillTestEffect());
        }


        encounterPart++;

        checkForSpellConsequences();
        return super.completeEncounterPart();
    }

    @Override
    public String getNameId() {
        return "${combat_encounter}";
    }


    @Override
    public String getId() {
        return "&combat_encounter";
    }

    @Override
    public void discard() {
        Field field =GameService.getInstance().getFieldOfInvestigator(getInvestigator());
        GameService.getInstance().addEffect(
                new NextInvestigator(() -> monsters.isEmpty() && !field.getMonster().isEmpty()));


    }

}
