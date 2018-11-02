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
import model.effects.ExecuteEndEvents;
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
    private String startText;
    private String failText;
    private String successText;


    public Test(SituationType situationType) {
        this(TestType.NONE, 0, 0, situationType);
    }

    public Test(Effect effect, SituationType situationType) {
        this(effect, EncounterType.TEST, TestType.NONE, 0, 0, situationType);
    }

    public Test(TestType testType, int mod, int minNumberOfSuccesses, SituationType situationType) {
        this(null, EncounterType.TEST, testType, mod, minNumberOfSuccesses, situationType);

    }

    public Test(Effect effect, EncounterType encounterType, TestType testType, int mod, int minNumberOfSuccesses, SituationType situationType) {
        super(encounterType);

        this.encounterID = "test";
        this.setFailText("");
        this.setStartText("");
        this.setSuccessText("");
        setTestType(new TestType[1]);
        setMinNumberOfSuccesses(new int[]{1});
        setMod(new int[]{0});
        getMod()[0] = mod;
        setEffect(new Effect[1][3]);
        setSituationType(situationType);
        setGame(GameService.getInstance());
        setEncounterPart(0);
        getEffect()[getEncounterPart()][START] = effect;


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
        return getStartText();
    }

    public String getEncounterEffectText() {

        return "";
    }

    public String getEncounterFailText() {

        return getFailText();
    }

    public String getEncounterSuccessText() {
        return getSuccessText();
    }


    public int completeEncounterPart() {
        checkForSpellConsequences();
        String header;
        String text;

        List<Effect> effects = new ArrayList<>();
        if (getEffect()[getEncounterPart()][START] != null) {
            GameService.getInstance().addEffect(getEffect()[getEncounterPart()][START]);
        }else{
            if (result.isSuccess()) {
                header = ResourceUtil.get("${success}", "ui");
                text = getEncounterSuccessText() + "\n" + getEffect()[getEncounterPart()][PASS].getText();
                effects.add(getEffect()[getEncounterPart()][PASS]);
            } else {
                header = ResourceUtil.get("${fail}", "ui");
                text = getEncounterFailText() + "\n" + getEffect()[getEncounterPart()][FAIL].getText();
                effects.add(getEffect()[getEncounterPart()][FAIL]);
            }
            getGame().addChoice(new InformationChoice(header, text, effects));
        }
        setEncounterPart(3);

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
