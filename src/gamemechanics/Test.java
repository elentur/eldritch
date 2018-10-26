package gamemechanics;

import Service.GameService;
import enums.EncounterType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.choice.InformationChoice;
import gamemechanics.encounter.Encounter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import model.effects.ExecuteEndEvents;
import model.effects.NextInvestigator;
import model.effects.NullEffect;
import preparation.Preparation;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@EqualsAndHashCode(of = {"encounterID"}, callSuper = false)
public class Test extends Encounter {

    private final String encounterID;


    public Test(SituationType situationType) {
        this(  TestType.NONE, 0, 0, situationType);
    }
    public Test(Effect effect,SituationType situationType) {
        this( effect, EncounterType.TEST,TestType.NONE, 0, 0, situationType);
    }
    public Test(TestType testType, int mod, int minNumberOfSuccesses, SituationType situationType) {
        this(null,EncounterType.TEST, testType, mod, minNumberOfSuccesses, situationType);

    }

    protected Test(Effect effect,EncounterType encounterType, TestType testType, int mod, int minNumberOfSuccesses, SituationType situationType) {
        super(encounterType);

        this.encounterID = "test";
        setTestType(new TestType[1]);
        setMinNumberOfSuccesses(new int[]{1});
        setMod(new int[]{0});
        getMod()[0] = mod;
        setEffect(new Effect[1][3]);
        setSituationType(situationType);
        setGame(GameService.getInstance());
        setEncounterPart(0);
        getEffect()[getEncounterPart()][START]=effect;



        getTestType()[getEncounterPart()] = testType;
        getMinNumberOfSuccesses()[getEncounterPart()] = minNumberOfSuccesses;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public String getNameId() {
        return "${test}";
    }


    @Override
    public String getId() {
        return "&test";
    }

    @Override
    public Preparation getPreparation() {
        if (super.getPreparation() == null) {
            setPreparation(new Preparation(getTestType()[getEncounterPart()], getInvestigator(), getSituationType(), this));
        }
        return super.getPreparation();
    }

    public String getEncounterStartText() {
        return ResourceUtil.get("${" + encounterID + "_start}", getNameId().replaceAll("[{}\\$]", ""));
    }

    public String getEncounterEffectText() {

        return "";
    }

    public String getEncounterFailText() {
        String key = "${" + encounterID + "_fail}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }

    public String getEncounterSuccessText() {
        String key = "${" + encounterID + "_success}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }


    public int completeEncounterPart() {
        if(getEffect()[getEncounterPart()][START]!=null){
            GameService.getInstance().addEffect(getEffect()[getEncounterPart()][START]);
        }
        setEncounterPart(3);
        checkForSpellConsequences();
        return super.completeEncounterPart();
    }

    @Override
    public void discard() {
        getGame().addEffect(new ExecuteEndEvents(this::executeEndEvents));
    }

    @Override
    protected void executeEndEvents() {
        for (Function<Encounter, Void> event : getEndEvents()) {
            event.apply(this);
        }
    }
}
