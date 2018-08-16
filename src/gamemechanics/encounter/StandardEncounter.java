package gamemechanics.encounter;

import enums.TestType;

import model.Item.Investigator;
import preparation.Preparation;

public class StandardEncounter extends Encounter{
    public StandardEncounter (Investigator inv){
        setInvestigator(inv);

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

        return new Preparation(getTestType(),getInvestigator(),getSituationType(),this);
    }
}
