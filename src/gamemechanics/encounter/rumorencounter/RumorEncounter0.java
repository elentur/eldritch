package gamemechanics.encounter.rumorencounter;

import enums.ConditionType;
import enums.ItemType;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.RumorEncounter;
import gamemechanics.encounter.StandardEncounter;
import model.Item.Investigator;
import model.effects.*;

public class RumorEncounter0 extends RumorEncounter {

    public RumorEncounter0(Investigator inv) {
        super(inv, "rue_0");
        getEffect()[0][START] = new NullEffect();
        getEffect()[0][PASS] = new Or(new RandomItem(ItemType.ASSET, 1, inv), new AssetFromReserve(ItemType.ASSET, 1, inv));
        getEffect()[0][FAIL] = new GainCondition(ConditionType.DETAINED, inv);
        setEncounterPart(0);
        getTestType()[0] = TestType.OBSERVATION;


    }


}
