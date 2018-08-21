package gamemechanics.encounter.expeditionencounter;

import enums.*;
import gamemechanics.encounter.ExpeditionEncounter;
import gamemechanics.encounter.StandardEncounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;
import model.effects.*;

public class ExpeditionEncounter0 extends ExpeditionEncounter {

    public ExpeditionEncounter0(Investigator inv) {
        super(inv, "exe_0");
        setFieldID(FieldID.PYRAMIDS);

        getEffect()[0][PASS] = new Or(new RandomItem(ItemType.ASSET, 1, inv), new AssetFromReserve(ItemType.ASSET, 1, inv));
        getEffect()[0][FAIL] = new GainCondition(ConditionType.DETAINED, inv);
        getTestType()[0] = TestType.OBSERVATION;

        getEffect()[1][PASS] = new And(new RandomItem(ItemType.ITEM, 1, inv), new Loose(SpendType.SANITY, 1, inv));
        getEffect()[1][FAIL] = new NullEffect();
        getTestType()[1] = TestType.NONE;
        getMinNumberOfSuccesses()[1] = 0;

        getEffect()[2][PASS] = new RandomItem(ItemType.ARTIFACT, 1, inv);
        getEffect()[2][FAIL] = new BecomeDelayed(inv);
        getTestType()[2] = TestType.OBSERVATION;

    }


}
