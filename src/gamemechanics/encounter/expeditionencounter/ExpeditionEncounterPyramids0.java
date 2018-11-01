package gamemechanics.encounter.expeditionencounter;

import enums.ConditionType;
import enums.FieldID;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.ExpeditionEncounter;
import model.effects.GainArtifact;
import model.effects.GainCondition;
import model.effects.LooseOrGainHealthSanity;
import model.effects.NullEffect;

import java.util.ArrayList;

public class ExpeditionEncounterPyramids0 extends ExpeditionEncounter {

    public ExpeditionEncounterPyramids0() {
        super("exe_0");
        setFieldID(FieldID.PYRAMIDS);


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
        getEffect()[1][FAIL] = new LooseOrGainHealthSanity(SpendType.HEALTH,-1,getInvestigator());
        getTestType()[1] = TestType.LORE;
        getMod()[1] = -1;


        getEffect()[2][START] = new GainCondition(ConditionType.LEG_INJURY,getInvestigator());
        getEffect()[2][PASS] =  new GainArtifact(new ArrayList<>(),getInvestigator());
        getEffect()[2][FAIL] = new LooseOrGainHealthSanity(SpendType.HEALTH,-2,getInvestigator());
        getTestType()[2] = TestType.INFLUENCE;
    }
}