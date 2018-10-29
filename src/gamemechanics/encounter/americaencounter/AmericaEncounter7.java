package gamemechanics.encounter.americaencounter;

import enums.ConditionType;
import enums.ItemType;
import enums.TestType;
import gamemechanics.encounter.AmericaEncounter;
import model.effects.GainCondition;
import model.effects.GainSpell;
import model.effects.Improve;
import model.effects.NullEffect;

import java.util.Collections;

public class AmericaEncounter7 extends AmericaEncounter {

    public AmericaEncounter7( ) {
        super("ame_7");

      

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case ARKHAM:
                getEffect()[0][START]=new NullEffect();
                getEffect()[0][PASS]=new GainCondition(ConditionType.BLESSED,getInvestigator());
                getEffect()[0][FAIL]=new GainCondition(ConditionType.PARANOIA,getInvestigator());
                setEncounterPart(0);
                break;
            case SAN_FRANCISCO:

                getEffect()[1][START]=new NullEffect();
                getEffect()[1][PASS]=new Improve(TestType.OBSERVATION,1,getInvestigator());
                getEffect()[1][FAIL]=new GainCondition(ConditionType.MADNESS,getInvestigator());
                setEncounterPart(1);
                break;
            case BUENOS_AIRES:
                getEffect()[2][START]=new NullEffect();
                getEffect()[2][PASS]=new GainSpell(Collections.singletonList(ItemType.RITUAL),getInvestigator());
                getEffect()[2][FAIL]=new NullEffect();
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.LORE;
        getMod()[0]=0;
        getTestType()[1] = TestType.WILL;
        getMod()[1]=0;
        getTestType()[2] = TestType.INFLUENCE;
        getMod()[2]=0;
    }
}
