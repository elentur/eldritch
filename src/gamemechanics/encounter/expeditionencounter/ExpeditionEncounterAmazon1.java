package gamemechanics.encounter.expeditionencounter;

import enums.*;
import gamemechanics.encounter.ExpeditionEncounter;
import model.effects.*;

import java.util.ArrayList;

public class ExpeditionEncounterAmazon1 extends ExpeditionEncounter {

    public ExpeditionEncounterAmazon1() {
        super("exe_1");
        setFieldID(FieldID.AMAZON);


    }


    @Override
    public void init() {
        super.init();

        getEffect()[0][START] = new NullEffect();
        getEffect()[0][PASS] = new NullEffect();
        getEffect()[0][FAIL] = new NullEffect();
        getTestType()[0] = TestType.OBSERVATION;

        getEffect()[1][START] = new NullEffect();
        getEffect()[1][PASS] = new GainArtifact(new ArrayList<>(),getInvestigator());
        getEffect()[1][FAIL] = new LooseOrGainHealthSanity(SpendType.SANITY,-2,getInvestigator());
        getTestType()[1] = TestType.LORE;


        getEffect()[2][START] = new NullEffect();
        getEffect()[2][PASS] = new GainClue(EffectSelector.RANDOM,2,getInvestigator());
        getEffect()[2][FAIL] = new GainCondition(ConditionType.BACK_INJURY,getInvestigator());
        getTestType()[2] = TestType.STRENGTH;
        getMod()[2] = -1;
    }
}