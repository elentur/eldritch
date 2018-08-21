package gamemechanics.encounter;

import enums.EncounterType;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;

@Getter
@Setter
public class AmericaEncounter extends StandardEncounter{



    public AmericaEncounter(Investigator inv, String encounterID){
       super(inv,encounterID,EncounterType.AMERICA_ENCOUNTER);
        setInvestigator(inv);
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
