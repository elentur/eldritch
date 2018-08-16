package gamemechanics.encounter;

import Service.EventService;
import Service.GameService;
import container.ItemContainer;
import container.Result;
import enums.SituationType;
import enums.TestType;
import gamemechanics.SkillTest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Item.Bonus;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.Monster;
import preparation.CombatPreparation;
import preparation.Preparation;

import java.util.*;
import java.util.function.Function;

@Getter
@Setter
@ToString(of = {"activeMonster", "investigator"})
public class CombatEncounter extends Encounter {
    private List<Monster> originalMonsters;
    private Map<Monster,Monster> monsters;
    private Monster activeMonster;
    private CombatPreparation attackPreparation;
    private CombatPreparation horrorPreparation;
    private GameService game;



    private EventService eventService = new EventService();

    public CombatEncounter(List<Monster> monsters, Investigator investigator) {
        this.game = GameService.getInstance();
        this.originalMonsters = monsters;
        this.monsters = new HashMap<>();
        for(Monster m : monsters){
            this.monsters.put(m.clone(),m);
        }
        this.investigator = investigator;
        activatePassiveBoni();
    }

    private void activatePassiveBoni() {
        Function<Bonus,Boolean> filter = bonus -> bonus.getSituation().equalsWithAll(SituationType.COMBAT_ENCOUNTER)
                && bonus.getField().equalsWithAll(game.getFieldOfInvestigator(investigator).getType())
                && bonus.isActivated()
                && bonus.isUsable()
                && bonus.isPassive();
        ItemContainer<Item> bonusItems = game.getBonusItemsforInvestigator(investigator);

        for(Bonus b :bonusItems.getBoniWithFilter(filter)){
            b.execute(this);
        }
    }

    public List<Monster> getAvailableMonster() {
        return new ArrayList<>(monsters.keySet());
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
            attackPreparation = new CombatPreparation(TestType.STRENGTH, investigator, activeMonster,this);
        }
        return attackPreparation;
    }

    public Investigator getInvestigator() {
        return investigator;
    }

    private CombatPreparation prepareForHorrorCheck() {
        if(horrorPreparation==null) {
            horrorPreparation = new CombatPreparation(TestType.WILL, investigator, activeMonster,this);
        }
        return horrorPreparation;
    }

    public void removeActiveMonster() {
        Monster org = this.monsters.get(getActiveMonster());
        org.setActualToughness(getActiveMonster().getActualToughness());
        this.monsters.remove(getActiveMonster());
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

    @Override
    public String getNameId() {
        return  "${combat_encounter}";
    }



    @Override
    public String getId() {
        return "&combat_encounter";
    }

}
