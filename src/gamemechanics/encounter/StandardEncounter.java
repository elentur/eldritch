package gamemechanics.encounter;

import Service.GameService;
import enums.EncounterType;
import enums.SituationType;
import enums.TestType;

import lombok.Getter;
import lombok.Setter;
import model.Field;
import model.Item.Investigator;
import preparation.Preparation;
import utils.ResourceUtil;

import java.io.File;

@Getter
@Setter
public class StandardEncounter extends Encounter{

    private final String encounterID;
    private final Field field;
    public StandardEncounter (Investigator inv, String encounterID){
        super(EncounterType.STANDARD_ENCOUNTER);
        setInvestigator(inv);
        this.encounterID = encounterID;
        setTestType(new TestType[3]);
        setSituationType(SituationType.STANDARD_ENCOUNTER);
        setGame(GameService.getInstance());
        this.field = getGame().getFieldOfInvestigator(inv);
    }



    @Override
    public String getNameId() {
        return  "${standard_encounter}";
    }



    @Override
    public String getId() {
        return "&standard_encounter";
    }


    public Preparation getPreparation(){

        return new Preparation(getTestType()[getEncounterPart()],getInvestigator(),getSituationType(),this);
    }

    public String getEncounterStartText(){
        return   ResourceUtil.get("${"+encounterID+"_"+field.getType().getText().toLowerCase()+"_start}", getNameId().replaceAll("[{}\\$]",""));
    }
    public String getEncounterFailText(){
        String key ="${"+encounterID+"_"+field.getType().getText().toLowerCase()+"_fail}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]",""));
        if(value.equals(key)){
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]",""));
        }
        return value;
    }
    public String getEncounterSuccessText(){
        String key ="${"+encounterID+"_"+field.getType().getText().toLowerCase()+"_success}";
        String value =   ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]",""));
        if(value.equals(key)){
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]",""));
        }
        return value;
    }
}
