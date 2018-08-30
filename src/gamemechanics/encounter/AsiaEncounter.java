package gamemechanics.encounter;

import enums.EncounterType;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;

@Getter
@Setter
public class AsiaEncounter extends StandardEncounter{



    public AsiaEncounter( String encounterID){
       super(encounterID,EncounterType.ASIA_ENCOUNTER);
    }



    @Override
    public String getNameId() {
        return  "${asia_encounter}";
    }



    @Override
    public String getId() {
        return "&asia_encounter";
    }
}
