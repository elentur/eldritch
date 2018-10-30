package gamemechanics.encounter.expeditionencounter;

import enums.*;
import gamemechanics.encounter.ExpeditionEncounter;
import model.effects.*;

import java.util.ArrayList;

public class ExpeditionEncounterAntarctica2 extends ExpeditionEncounter {

    public ExpeditionEncounterAntarctica2() {
        super("exe_2");
        setFieldID(FieldID.ANTARCTICA);


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
        getEffect()[1][FAIL] = new GainCondition(ConditionType.HALLUCINATIONS,getInvestigator());
        getTestType()[1] = TestType.WILL;

        getEffect()[2][START] = new NullEffect();
        getEffect()[2][PASS] = new GainClue(EffectSelector.RANDOM,2,getInvestigator());
        getEffect()[2][FAIL] = new GainCondition(ConditionType.LEG_INJURY,getInvestigator());
        getTestType()[2] = TestType.STRENGTH;
        getMod()[2] = -1;
    }
}