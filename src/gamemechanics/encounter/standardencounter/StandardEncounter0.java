package gamemechanics.encounter.standardencounter;

import enums.SituationType;
import enums.TestType;
import gamemechanics.encounter.StandardEncounter;
import model.Item.Investigator;

public class StandardEncounter0 extends StandardEncounter {


    public StandardEncounter0(Investigator inv) {
        super(inv);
        setTestType(TestType.WILL);
        setSituationType(SituationType.STANDARD_ENCOUNTER);
    }



}
