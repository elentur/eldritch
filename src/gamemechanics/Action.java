package gamemechanics;

import Service.GameService;
import enums.*;
import gamemechanics.choice.InformationChoice;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import model.effects.*;
import preparation.Preparation;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Action extends Encounter {

    private final String encounterID;
    private final Field field;
    private Preparation preparation;

    public Action(Investigator inv, String encounterID, Effect startEffect, Effect passEffect, Effect failEffect, TestType testType, int minNumberOfSuccesses) {
        super(EncounterType.ACTION);
        setInvestigator(inv);
        this.encounterID = encounterID;
        setTestType(new TestType[1]);
        setMinNumberOfSuccesses(new int[]{1});
        setMod(new int[]{0});
        setEffect(new Effect[1][3]);
        setSituationType(SituationType.ACTION);
        setGame(GameService.getInstance());
        this.field = getGame().getFieldOfInvestigator(inv);

        setEncounterPart(0);
        getEffect()[getEncounterPart()][START]=startEffect;
        getEffect()[getEncounterPart()][PASS]=passEffect;
        getEffect()[getEncounterPart()][FAIL]=failEffect;

        getTestType()[getEncounterPart()] = testType;
        getMinNumberOfSuccesses()[getEncounterPart()]=minNumberOfSuccesses;
    }



    @Override
    public String getNameId() {
        return  "${action}";
    }



    @Override
    public String getId() {
        return "&action";
    }

    public Preparation getPreparation() {
        if(preparation ==null){
            preparation = new Preparation(getTestType()[getEncounterPart()], getInvestigator(), getSituationType(), this);
        }
        return preparation;
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
        String key = "${" + encounterID +"_success}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }

    public int completeEncounterPart() {
        if (result.isSuccess()) {
            getGame().addEffect(getEffect()[getEncounterPart()][PASS]);
        } else {
            getGame().addEffect(getEffect()[getEncounterPart()][FAIL]);
        }

        setEncounterPart(3);
        return getEncounterPart();
    }
}
