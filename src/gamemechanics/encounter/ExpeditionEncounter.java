package gamemechanics.encounter;

import enums.EncounterType;
import enums.FieldID;
import gamemechanics.choice.InformationChoice;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExpeditionEncounter extends StandardEncounter {
private static final String[] STEP = {"start","pass","fail"};

    private FieldID fieldID;

    public ExpeditionEncounter(Investigator inv, String encounterID) {
        super(inv, encounterID, EncounterType.EXPEDITION_ENCOUNTER);
        setInvestigator(inv);
        setEncounterPart(0);
    }


    @Override
    public String getNameId() {
        return "${expedition_encounter}";
    }


    @Override
    public String getId() {
        return "&expedition_encounter_"+getFieldID().getKey().replaceAll("[{}\\$]", "");
    }

    @Override
    public String getEncounterStartText() {
        return ResourceUtil.get("${" + getEncounterID() + "_" + STEP[getEncounterPart()] + "_start}", getNameId().replaceAll("[{}\\$]", ""));
    }

    @Override
    public String getEncounterFailText() {
        String key = "${" + getEncounterID() + "_" + STEP[getEncounterPart()] + "_fail}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }

    @Override
    public String getEncounterSuccessText() {
        String key = "${" + getEncounterID() + "_" + STEP[getEncounterPart()] + "_success}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }

    @Override
    public int completeEncounterPart() {
        String header = "";
        String text = "";
        List<Effect> effects = new ArrayList<>();
        if (result.isSuccess()) {
            header = ResourceUtil.get("${success}", "ui");
            text = getEncounterSuccessText() + "\n" + getEffect()[getEncounterPart()][PASS].getText();
            effects.add(getEffect()[getEncounterPart()][PASS]);
            setEncounterPart(getEncounterPart() == 0 ? 1 : 3);
        } else {
            header = ResourceUtil.get("${fail}", "ui");
            text = getEncounterFailText() + "\n" + getEffect()[getEncounterPart()][FAIL].getText();
            effects.add(getEffect()[getEncounterPart()][FAIL]);
            setEncounterPart(getEncounterPart() == 0 ? 2 : 3);
        }
        getGame().addChoice(new InformationChoice(header, text, effects));

        return getEncounterPart();
    }
}
