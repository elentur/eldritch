package gamemechanics.encounter;

import enums.EncounterType;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

@Getter
@Setter
public class AmericaEncounter extends StandardEncounter{



    public AmericaEncounter( String encounterID){
       super(encounterID,EncounterType.AMERICA_ENCOUNTER);
    }



    @Override
    public String getNameId() {
        return  "${america_encounter}";
    }



    @Override
    public String getId() {
        return "&america_encounter";
    }

    public String getEncounterStartText() {


        return ResourceUtil.get("${" + getEncounterID() + "_" + getField().getFieldID().toString().toLowerCase() + "_start}", getNameId().replaceAll("[{}\\$]", ""),getEncounterEffectText());
    }
    public String getEncounterEffectText() {
        return getEffect()[getEncounterPart()][START].getText();
    }
    public String getEncounterFailText() {
        String key = "${" + getEncounterID() + "_" + getField().getFieldID().toString().toLowerCase() + "_fail}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }

    public String getEncounterSuccessText() {
        String key = "${" + getEncounterID() + "_" + getField().getFieldID().toString().toLowerCase() + "_success}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }
}
