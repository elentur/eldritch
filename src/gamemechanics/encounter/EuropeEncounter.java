package gamemechanics.encounter;

import enums.EncounterType;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;

@Getter
@Setter
public class EuropeEncounter extends StandardEncounter{



    public EuropeEncounter(Investigator inv, String encounterID){
       super(inv,encounterID,EncounterType.EUROPE_ENCOUNTER);
        setInvestigator(inv);
    }



    @Override
    public String getNameId() {
        return  "${europe_encounter}";
    }



    @Override
    public String getId() {
        return "&europe_encounter";
    }
}
