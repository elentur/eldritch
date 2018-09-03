package gamemechanics.encounter.standardencounter;

import enums.ConditionType;
import enums.ItemType;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.StandardEncounter;
import model.effects.*;

public class StandardEncounter0 extends StandardEncounter {

    public StandardEncounter0() {
        super("se_0");

      

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getType()) {
            case CITY:
                getEffect()[0][START] = new NullEffect();
                getEffect()[0][PASS] = new Or(new RandomAsset(ItemType.ANY,  getInvestigator()), new AssetFromReserve(ItemType.ANY, 1, getInvestigator()));
                getEffect()[0][FAIL] = new GainCondition(ConditionType.DETAINED, getInvestigator());
                setEncounterPart(0);
                break;
            case WILDERNESS:
                getEffect()[1][START] = new NullEffect();
                getEffect()[1][PASS] = new And(new RandomAsset(ItemType.ITEM,  getInvestigator()), new Loose(SpendType.SANITY, 1, getInvestigator()));
                getEffect()[1][FAIL] = new NullEffect();
                setEncounterPart(1);
                break;
            case SEA:
                getEffect()[2][START] = new NullEffect();
                getEffect()[2][PASS] = new RandomArtifact(ItemType.ANY,  getInvestigator());
                getEffect()[2][FAIL] = new BecomeDelayed(getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.OBSERVATION;
        getTestType()[1] = TestType.NONE;
        getMinNumberOfSuccesses()[1] = 0;
        getTestType()[2] = TestType.OBSERVATION;
    }


}
