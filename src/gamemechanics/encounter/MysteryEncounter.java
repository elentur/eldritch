package gamemechanics.encounter;

import enums.EncounterType;
import enums.FieldID;
import enums.OldOnes;
import lombok.Getter;
import lombok.Setter;
import model.Item.token.EldritchToken;
import utils.ResourceUtil;

@Getter
@Setter
public class MysteryEncounter extends StandardEncounter {

    private final OldOnes oldOne;
    private EldritchToken eldritchToken;
    public MysteryEncounter(String encounterID, OldOnes oldOne) {
        super( encounterID, EncounterType.MYSTERY_ENCOUNTER);
        setEncounterPart(0);
        this.oldOne=oldOne;
    }


    @Override
    public String getNameId() {

        return "${mystery_encounter_"+oldOne.getKey().replaceAll("[{}\\$]", "")+"}";
    }


    @Override
    public String getId() {
        return "&mystery_encounter_"+oldOne.getKey().replaceAll("[{}\\$]", "");
    }

    @Override
    public String getEncounterStartText() {
        return ResourceUtil.get("${" + getEncounterID()  + "_start}", getNameId().replaceAll("[{}\\$]", ""));
    }
    @Override
    public String getEncounterFailText() {
        String key = "${" + getEncounterID()   + "_fail}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }
    @Override
    public String getEncounterSuccessText() {
        String key = "${" + getEncounterID() + "_success}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }


}
