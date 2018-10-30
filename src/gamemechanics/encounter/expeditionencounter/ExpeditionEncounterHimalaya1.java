package gamemechanics.encounter.expeditionencounter;

import enums.*;
import gamemechanics.encounter.ExpeditionEncounter;
import model.effects.*;

import java.util.ArrayList;

public class ExpeditionEncounterHimalaya1 extends ExpeditionEncounter {

    public ExpeditionEncounterHimalaya1() {
        super("exe_1");
        setFieldID(FieldID.HIMALAYA);


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
        getEffect()[1][FAIL] = new LooseOrGainHealthSanity(SpendType.SANITY,-2,getInvestigator());
        getTestType()[1] = TestType.LORE;
        getMod()[1] = -1;


        getEffect()[2][START] = new NullEffect();
        getEffect()[2][PASS] =  new GainArtifact(new ArrayList<>(),getInvestigator());
        getEffect()[2][FAIL] = new LooseOrGainHealthSanity(SpendType.HEALTH,-2,getInvestigator());
        getTestType()[2] = TestType.INFLUENCE;
    }
}