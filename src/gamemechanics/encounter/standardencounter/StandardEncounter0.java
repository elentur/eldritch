package gamemechanics.encounter.standardencounter;

import enums.SituationType;
import enums.TestType;
import gamemechanics.encounter.StandardEncounter;
import model.Field;
import model.Item.Investigator;

public class StandardEncounter0 extends StandardEncounter {


    public StandardEncounter0(Investigator inv) {
        super(inv,"se_0");

        switch (getField().getType()){
            case SEA:
                setEncounterPart(2);
                break;
            case WILDNESS:
                setEncounterPart(1);
                break;
            default:
                setEncounterPart(0);
        }
        getTestType()[0] = TestType.WILL;
        getTestType()[1] = TestType.STRENGTH;
        getTestType()[2] = TestType.OBSERVATION;

    }



}
