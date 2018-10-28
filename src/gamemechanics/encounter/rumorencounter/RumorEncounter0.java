package gamemechanics.encounter.rumorencounter;

import enums.ConditionType;
import enums.ItemType;
import enums.TestType;
import gamemechanics.encounter.RumorEncounter;
import model.effects.*;

import java.util.Collections;

public class RumorEncounter0 extends RumorEncounter {

    public RumorEncounter0() {
        super( "rue_0");
      


    }
    @Override
    public void init() {
        super.init();
        getEffect()[0][START] = new NullEffect();
        getEffect()[0][PASS] = new Or(new GainAsset(Collections.singletonList(ItemType.ANY),  getInvestigator()), new AssetFromReserve(1,  getInvestigator(),ItemType.ANY));
        getEffect()[0][FAIL] = new GainCondition(ConditionType.DETAINED, getInvestigator());
        setEncounterPart(0);
        getTestType()[0] = TestType.OBSERVATION;
    }

}
