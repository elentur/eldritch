package gamemechanics.encounter;

import enums.EncounterType;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
@Setter
public class RumorEncounter extends StandardEncounter{



    public RumorEncounter( String encounterID){
       super(encounterID,EncounterType.RUMOR_ENCOUNTER);
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

    @Override
    public String getNameId() {
        return  "${rumor_encounter}";
    }



    @Override
    public String getId() {
        return "&rumor_encounter";
    }
}
