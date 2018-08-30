package gamemechanics.encounter;

import enums.EncounterType;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;

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
}
