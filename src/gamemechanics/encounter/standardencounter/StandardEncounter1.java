package gamemechanics.encounter.standardencounter;

import enums.*;
import gamemechanics.encounter.StandardEncounter;
import model.effects.*;

public class StandardEncounter1 extends StandardEncounter {

    public StandardEncounter1() {
        super("se_1");

      

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getType()) {
            case CITY:
                getEffect()[0][START] = new NullEffect();
                getEffect()[0][PASS] = new SpawnClue(1);
                getEffect()[0][FAIL] = new GainCondition(ConditionType.DEPT, getInvestigator());
                setEncounterPart(0);
                break;
            case WILDERNESS:
                getEffect()[1][START] = new NullEffect();
                getEffect()[1][PASS] = new Or(new GainClue(EffectSelector.RANDOM,1, getInvestigator()),new Improve(TestType.LORE,1,getInvestigator()));
                getEffect()[1][FAIL] = new NullEffect();
                setEncounterPart(1);
                break;
            case SEA:
                getEffect()[2][START] = new And(new Move(1,getInvestigator()),new Loose(SpendType.SANITY,1, getInvestigator()));
                getEffect()[2][PASS] = new NullEffect();
                getEffect()[2][FAIL] = new NullEffect();
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.INFLUENCE;
        getTestType()[1] = TestType.WILL;
        getTestType()[2] = TestType.NONE;
    }


}
