package gamemechanics.encounter.specialencounter.shub_niggurath;

import enums.*;
import gamemechanics.encounter.SpecialEncounter;
import model.effects.*;

public class SpecialEncounter0 extends SpecialEncounter {

    public SpecialEncounter0() {
        super( "spe_0", OldOnes.SHUB_NIGGURATH);
        setFieldID(FieldID.PYRAMIDS);

     

    }
    @Override
    public void init() {
        super.init();
        getEffect()[0][START]=new NullEffect();
        getEffect()[0][PASS] = new NullEffect();
        getEffect()[0][FAIL] = new NullEffect();
        getTestType()[0] = TestType.STRENGTH;

        getEffect()[1][START]=new RetreatDoom(1);
        getEffect()[1][PASS] = new NullEffect();
        getEffect()[1][FAIL] = new GainCondition(ConditionType.AMNESIA, getInvestigator());
        getTestType()[1] = TestType.LORE;


        getEffect()[2][START]=new And(new LooseOrGainHealthSanity(SpendType.HEALTH,1,getInvestigator()),new GainCondition(ConditionType.INTERNAL_INJURY, getInvestigator()));
        getEffect()[2][PASS] = new RetreatDoom(1);
        getEffect()[2][FAIL] = new NullEffect();
        getTestType()[2] = TestType.OBSERVATION;
        getMod()[2]=-1;
    }

}
