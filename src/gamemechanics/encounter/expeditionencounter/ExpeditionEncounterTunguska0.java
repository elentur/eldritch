package gamemechanics.encounter.expeditionencounter;

import enums.ConditionType;
import enums.FieldID;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.ExpeditionEncounter;
import model.effects.*;

public class ExpeditionEncounterTunguska0 extends ExpeditionEncounter {

    public ExpeditionEncounterTunguska0() {
        super("exe_0");
        setFieldID(FieldID.TUNGUSKA);


    }


    @Override
    public void init() {
        super.init();

        getEffect()[0][START] = new NullEffect();
        getEffect()[0][PASS] = new NullEffect();
        getEffect()[0][FAIL] = new NullEffect();
        getTestType()[0] = TestType.STRENGTH;

        getEffect()[1][START] = new NullEffect();
        getEffect()[1][PASS] = new RetreatDoom(1);
        getEffect()[1][FAIL] = new GainCondition(ConditionType.AMNESIA, getInvestigator());
        getTestType()[1] = TestType.LORE;


        getEffect()[2][START] = new And(new LooseOrGainHealthSanity(SpendType.HEALTH, -1, getInvestigator()), new GainCondition(ConditionType.LEG_INJURY, getInvestigator()));
        getEffect()[2][PASS] = new RetreatDoom(1);
        getEffect()[2][FAIL] = new NullEffect();
        getTestType()[2] = TestType.OBSERVATION;
        getMod()[2] = -1;
    }
}