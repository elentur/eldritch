package gamemechanics.encounter.americaencounter;

import enums.ConditionType;
import enums.ItemType;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.AmericaEncounter;
import model.effects.*;

import java.util.Collections;

public class AmericaEncounter5 extends AmericaEncounter {

    public AmericaEncounter5( ) {
        super("ame_5");

      

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case ARKHAM:
                getEffect()[0][START]=new GainSpell(Collections.singletonList(ItemType.INCANTATION),getInvestigator());
                getEffect()[0][PASS]=new NullEffect();
                getEffect()[0][FAIL]=new GainCondition(ConditionType.AMNESIA,getInvestigator());
                setEncounterPart(0);
                break;
            case SAN_FRANCISCO:

                getEffect()[1][START]=new Improve(TestType.OBSERVATION,1,getInvestigator());
                getEffect()[1][PASS]=new NullEffect();
                getEffect()[1][FAIL]=new And(new LooseOrGainHealthSanity(SpendType.HEALTH,-1,getInvestigator()),
                        new LooseOrGainHealthSanity(SpendType.SANITY,-1,getInvestigator()));
                setEncounterPart(1);
                break;
            case BUENOS_AIRES:
                getEffect()[2][START]=new GainSpell(Collections.singletonList(ItemType.RITUAL),getInvestigator());
                getEffect()[2][PASS]=new NullEffect();
                getEffect()[2][FAIL]=new GainCondition(ConditionType.PARANOIA,getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.LORE;
        getMod()[0]=0;
        getTestType()[1] = TestType.LORE;
        getMod()[1]=0;
        getTestType()[2] = TestType.LORE;
        getMod()[2]=0;
    }
}
