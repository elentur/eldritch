package gamemechanics.encounter.researchencounter;

import enums.ConditionType;
import enums.ItemType;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.ResearchEncounter;
import gamemechanics.encounter.StandardEncounter;
import model.Item.Investigator;
import model.effects.*;

public class ResearchEncounter0 extends ResearchEncounter {

    public ResearchEncounter0(Investigator inv) {
        super(inv,"re_0","azathoth");

        switch (getField().getType()){
            case CITY:
                getEffect()[0][PASS]=new Or(new RandomItem(ItemType.ASSET,1,inv),new AssetFromReserve(ItemType.ASSET,1,inv));
                getEffect()[0][FAIL]=new GainCondition(ConditionType.DETAINED,inv);
                setEncounterPart(0);
                break;
            case WILDNESS:
                getEffect()[1][PASS]=new And(new RandomItem(ItemType.ITEM,1,inv),new Loose(SpendType.SANITY,1,inv));
                getEffect()[1][FAIL]=new NullEffect();
                setEncounterPart(1);
                break;
            case SEA:
                getEffect()[2][PASS]=new  RandomItem(ItemType.ARTIFACT,1,inv);
                getEffect()[2][FAIL]=new BecomeDelayed(inv);
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.OBSERVATION;
        getTestType()[1] = TestType.NONE;
        getMinNumberOfSuccesses()[1]=0;
        getTestType()[2] = TestType.OBSERVATION;

    }



}
