package gamemechanics.encounter.expeditionencounter;

import enums.*;
import gamemechanics.encounter.ExpeditionEncounter;
import model.effects.*;

import java.util.ArrayList;

public class ExpeditionEncounterHeartOfAfrica0 extends ExpeditionEncounter {

    public ExpeditionEncounterHeartOfAfrica0() {
        super("exe_0");
        setFieldID(FieldID.HEART_OF_AFRICA);


    }


    @Override
    public void init() {
        super.init();

        getEffect()[0][START] = new NullEffect();
        getEffect()[0][PASS] = new NullEffect();
        getEffect()[0][FAIL] = new NullEffect();
        getTestType()[0] = TestType.STRENGTH;

        getEffect()[1][START] = new GainArtifact(new ArrayList<>(),getInvestigator());
        getEffect()[1][PASS] = new NullEffect();
        getEffect()[1][FAIL] = new LooseOrGainHealthSanity(SpendType.SANITY,-1,getInvestigator());
        getTestType()[1] = TestType.OBSERVATION;
        getMod()[1] = -1;


        getEffect()[2][START] = new GainCondition(ConditionType.LEG_INJURY,getInvestigator());
        getEffect()[2][PASS] =  new GainArtifact(new ArrayList<>(),getInvestigator());
        getEffect()[2][FAIL] = new LooseOrGainHealthSanity(SpendType.HEALTH,-2,getInvestigator());
        getTestType()[2] = TestType.INFLUENCE;
    }
}