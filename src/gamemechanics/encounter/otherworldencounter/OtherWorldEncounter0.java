package gamemechanics.encounter.otherworldencounter;

import enums.ConditionType;
import enums.FieldID;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.ExpeditionEncounter;
import gamemechanics.encounter.OtherWorldEncounter;
import model.Item.Investigator;
import model.effects.*;

public class OtherWorldEncounter0 extends OtherWorldEncounter {

    public OtherWorldEncounter0(Investigator inv) {
        super(inv, "owe_0");
        setFieldID(FieldID.PYRAMIDS);

        getEffect()[0][START]=new NullEffect();
        getEffect()[0][PASS] = new NullEffect();
        getEffect()[0][FAIL] = new NullEffect();
        getTestType()[0] = TestType.STRENGTH;

        getEffect()[1][START]=new RetreatDoom(1,inv);
        getEffect()[1][PASS] = new NullEffect();
        getEffect()[1][FAIL] = new GainCondition(ConditionType.AMNESIA, inv);
        getTestType()[1] = TestType.LORE;


        getEffect()[2][START]=new And(new Loose(SpendType.HEALTH,1,inv),new GainCondition(ConditionType.INTERNAL_INJURY, inv));
        getEffect()[2][PASS] = new RetreatDoom(1,inv);
        getEffect()[2][FAIL] = new NullEffect();
        getTestType()[2] = TestType.OBSERVATION;
        getMod()[2]=-1;

    }


}
