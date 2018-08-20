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

    private final String encounterID;
    private final Field field;
    private final String oldOneID;

    public ResearchEncounter(Investigator inv, String encounterID, String oldOneID){
       super(inv,encounterID,EncounterType.RESEARCH_ENCOUNTER);
        setInvestigator(inv);
        this.encounterID = encounterID;
        setTestType(new TestType[3]);
        setEffect(new Effect[3][2]);
        setSituationType(SituationType.STANDARD_ENCOUNTER);
        setGame(GameService.getInstance());
        this.field = getGame().getFieldOfInvestigator(inv);
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
