package gamemechanics.encounter;

import Service.GameService;
import enums.EncounterType;
import enums.OldOnes;
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

    private final OldOnes oldOne;

    public ResearchEncounter(Investigator inv, String encounterID, OldOnes oldOne){
       super(inv,encounterID,EncounterType.RESEARCH_ENCOUNTER);
        setInvestigator(inv);
        this.oldOne=oldOne;
    }



    @Override
    public String getNameId() {
        return  "${research_encounter_"+oldOne.getKey().replaceAll("[{}\\$]", "")+"}";
    }



    @Override
    public String getId() {
        return "&research_encounter_"+oldOne.getKey().replaceAll("[{}\\$]", "");
    }
}
