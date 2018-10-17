package gamemechanics.encounter;

import Service.GameService;
import container.ItemStack;
import container.Result;
import enums.EncounterType;
import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.Action;
import gamemechanics.SkillTest;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.ItemBonus;
import model.Item.Spell;
import model.effects.ExecuteEndEvents;
import model.effects.NextInvestigator;
import preparation.Preparation;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Getter
@Setter
public abstract class Encounter implements Item {
    private final EncounterType encounterType;
    protected static final int START =0;
    protected static final int PASS =1;
    protected static final int FAIL =2;
    public String uniqueId = UUID.randomUUID().toString();
    private GameService game;
    int encounterPart;
    protected Result result;
    Investigator investigator;
    private TestType[] testType;
    private int[] minNumberOfSuccesses;
    private int[] mod;
    private Effect[][] effect;
    private SituationType situationType;
    private Field field;
    private ItemStack stack;
    private Preparation preparation;
    private List<Function<Encounter,Void>> endEvents;


    public Encounter(EncounterType type) {
        encounterType = type;
        endEvents=new ArrayList<>();
    }


    public Result getResult() {
        return result;
    }

    public Result check() {


        SkillTest skillTest = new SkillTest(getPreparation().getTestTyp(), getPreparation().getModificationForSkillTest());
        result = skillTest.execute(investigator);
        result.setMinNumberOfSuccesses(getMinNumberOfSuccesses()[getEncounterPart()]);

        return result;
    }


    public Preparation getPreparation() {
        return preparation;
    }

    public int completeEncounterPart() {
        preparation.getUsedBoni().clear();
        return encounterPart;
    }

    protected void executeEndEvents(){
        for(Function<Encounter,Void> event : endEvents){
            event.apply(this);
        }
        getGame().addEffect(new NextInvestigator());
    }

    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }

    @Override
    public List<ItemBonus> getBonus() {
        return new ArrayList<>();
    }

    @Override
    public ItemType getItemType() {
        return ItemType.ENCOUNTER;
    }

    @Override
    public ItemType getSubType() {
        return ItemType.NONE;
    }

    @Override
    public String getName() {
        return ResourceUtil.get(getNameId(), "encounter");

    }

    public String getResultInformation(){

        if (result.isSuccess()) {
           return getEncounterSuccessText();
        } else {
          return getEncounterFailText();
        }


    }

    public String getEncounterStartText() {
        return "";
    }

    public String getEncounterFailText() {
        return "";
    }

    public String getEncounterSuccessText() {
        return "";
    }

    public void init() {
        setEncounterPart(0);
        setPreparation(null);
        game=GameService.getInstance();
        setInvestigator(game.getEncounteringInvestigator());
        this.field = game.getFieldOfInvestigator(getInvestigator());
    }
    @Override
    public void setStack(ItemStack itemStack){
        stack=itemStack;
    }
    @Override
    public void discard(){
       getGame().addEffect(new ExecuteEndEvents(this::executeEndEvents));
        if(stack!= null) {
            stack.discard(this);
        }


    }
    @Override
    public Encounter draw(){
        return (Encounter) getStack().draw();
    }
    @Override
    public void executeReckoning(Investigator inv, boolean autoFail){}

    @Override
    public List<Effect> getDrawEffects() {
        return new ArrayList<>();
    }

    @Override
    public Action getEncounter() {
        return null;
    }

    protected void checkForSpellConsequences(){
        for (Spell spell : GameService.getInstance().getUsedSpells()){
            spell.executeConsequences(result);
        }
        GameService.getInstance().getUsedSpells().clear();
    }

    public void addEndEvent(Function<Encounter,Void> event){
        endEvents.add(event);
    }
}
