package gamemechanics.encounter;

import Service.GameService;
import enums.EncounterType;
import enums.SituationType;
import enums.TestType;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import preparation.Preparation;
import utils.ResourceUtil;

@Getter
@Setter
public class ResearchEncounter extends StandardEncounter{

    private final String oldOneID;

    public ResearchEncounter(Investigator inv, String encounterID, String oldOneID){
       super(inv,encounterID,EncounterType.RESEARCH_ENCOUNTER);
        setInvestigator(inv);
        this.oldOneID=oldOneID;
    }



    @Override
    public String getNameId() {
        return  "${research_encounter"+"_"+oldOneID+"}";
    }



    @Override
    public String getId() {
        return "&research_encounter"+"_"+oldOneID;
    }
}
