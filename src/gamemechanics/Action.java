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
public class Action extends Encounter {

    private final String encounterID;
    private final Field field;


    public Action(Investigator inv, String encounterID, Effect startEffect, SituationType situationType) {
        this(inv, encounterID, startEffect, new NullEffect(), new NullEffect(), TestType.NONE, 0,0,situationType);
    }

    public Action(Investigator inv, String encounterID, Effect startEffect, Effect passEffect, Effect failEffect, TestType testType, int mod, int minNumberOfSuccesses,SituationType situationType) {
        this(EncounterType.ACTION, inv, encounterID, startEffect, passEffect, failEffect, testType,mod, minNumberOfSuccesses, situationType);

    }

    protected Action(EncounterType encounterType, Investigator inv, String encounterID, Effect startEffect, Effect passEffect, Effect failEffect, TestType testType,int mod, int minNumberOfSuccesses,SituationType situationType) {
        super(encounterType);
        setInvestigator(inv);
        this.encounterID = encounterID;
        setTestType(new TestType[1]);
        setMinNumberOfSuccesses(new int[]{1});
        setMod(new int[]{0});
        getMod()[0]=mod;
        setEffect(new Effect[1][3]);
        setSituationType(situationType);
        setGame(GameService.getInstance());
        this.field = getGame().getFieldOfInvestigator(inv);

        setEncounterPart(0);
        getEffect()[getEncounterPart()][START] = startEffect;
        getEffect()[getEncounterPart()][PASS] = passEffect;
        getEffect()[getEncounterPart()][FAIL] = failEffect;

        getTestType()[getEncounterPart()] = testType;
        getMinNumberOfSuccesses()[getEncounterPart()] = minNumberOfSuccesses;
    }

    @Override
    public String getNameId() {
        return "${action}";
    }


    @Override
    public String getId() {
        return "&action";
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
        return getEffect()[getEncounterPart()][START].getText();
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
        checkForSpellConsequences();
        String header;
        String text;

        List<Effect> effects = new ArrayList<>();
        if (getEffect()[getEncounterPart()][START] instanceof NullEffect) {
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
        } else {
            getGame().addEffect(getEffect()[getEncounterPart()][START]);
        }
        setEncounterPart(3);

        return super.completeEncounterPart();
    }

    @Override
    public void discard() {
        getInvestigator().addDoneAction(this);
        getGame().addEffect(new ExecuteEndEvents(this::executeEndEvents));
           }

    @Override
    protected void executeEndEvents() {
        for (Function<Encounter, Void> event : getEndEvents()) {
            event.apply(this);
        }
        if (getInvestigator().getDoneActions().size() >= getInvestigator().getMaxActions()) {
            getGame().addEffect(new NextInvestigator());
            getInvestigator().getDoneActions().clear();
        }
    }
}
