package gamemechanics.encounter.americaencounter;

import enums.ConditionType;
import enums.ItemType;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.AmericaEncounter;
import model.effects.*;

import java.util.Collections;

public class AmericaEncounter1 extends AmericaEncounter {

    public AmericaEncounter1( ) {
        super("ame_1");

      

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case ARKHAM:
                getEffect()[0][START]=new GainSpell(Collections.singletonList(ItemType.INCANTATION),getInvestigator());
                getEffect()[0][PASS]=new NullEffect();
                getEffect()[0][FAIL]=new GainCondition(ConditionType.PARANOIA,getInvestigator());
                setEncounterPart(0);
                break;
            case SAN_FRANCISCO:

                getEffect()[1][START]=new NullEffect();
                getEffect()[1][PASS]=new Improve(1,1,getInvestigator());
                getEffect()[1][FAIL]=new  GainCondition(ConditionType.LEG_INJURY,getInvestigator());
                setEncounterPart(1);
                break;
            case BUENOS_AIRES:
                getEffect()[2][START]=new NullEffect();
                getEffect()[2][PASS]=new GainSpell(Collections.singletonList(ItemType.RITUAL),getInvestigator());
                getEffect()[2][FAIL]=new LooseOrGainHealthSanity(SpendType.SANITY,-1,getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.WILL;
        getMod()[0]=+1;
        getTestType()[1] = TestType.WILL;
        getMod()[1]=-1;
        getTestType()[2] = TestType.LORE;
        getMod()[2]=0;
    }
}
