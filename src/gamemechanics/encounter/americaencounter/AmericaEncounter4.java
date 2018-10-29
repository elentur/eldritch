package gamemechanics.encounter.americaencounter;

import enums.ConditionType;
import enums.ItemType;
import enums.SpendType;
import enums.TestType;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.AmericaEncounter;
import model.Effect;
import model.effects.*;
import utils.ResourceUtil;

import java.util.Collections;

public class AmericaEncounter4 extends AmericaEncounter {

    public AmericaEncounter4( ) {
        super("ame_4");

      

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case ARKHAM:
                getEffect()[0][START]=new NullEffect();
                getEffect()[0][PASS]=new  GainSpell(Collections.singletonList(ItemType.INCANTATION),getInvestigator());
                getEffect()[0][FAIL]=new LooseOrGainHealthSanity(SpendType.HEALTH,-1,getInvestigator());
                setEncounterPart(0);
                break;
            case SAN_FRANCISCO:
                Effect effect2 = new And(new BecomeDelayed(getInvestigator()),
                        new Improve(1,1,getInvestigator()));
                YesNoChoice choice2 = new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect2.getText(),null,null);
                effect2.setCondition(choice2);
                getEffect()[1][START]=effect2;
                getEffect()[1][PASS]=new NullEffect();
                getEffect()[1][FAIL]=new NullEffect();
                setEncounterPart(1);
                break;
            case BUENOS_AIRES:
                getEffect()[2][START]=new  GainSpell(Collections.singletonList(ItemType.RITUAL),getInvestigator());
                getEffect()[2][PASS]=new NullEffect();
                getEffect()[2][FAIL]=new  GainCondition(ConditionType.DETAINED,getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.OBSERVATION;
        getMod()[0]=0;
        getTestType()[1] = TestType.NONE;
        getMod()[1]=0;
        getTestType()[2] = TestType.WILL;
        getMod()[2]=0;
    }
}
