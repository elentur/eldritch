package gamemechanics.encounter.americaencounter;

import enums.SpendType;
import enums.TestType;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.AmericaEncounter;
import model.Effect;
import model.effects.*;
import utils.ResourceUtil;

import java.util.ArrayList;

public class AmericaEncounter6 extends AmericaEncounter {

    public AmericaEncounter6( ) {
        super("ame_6");

      

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case ARKHAM:
                Effect effect = new And(new BecomeDelayed(getInvestigator()),
                        new GainSpell(new ArrayList<>(),getInvestigator()),
                        new GainSpell(new ArrayList<>(),getInvestigator()));
                YesNoChoice choice = new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect.getText(),null,null);
                effect.setCondition(choice);
                getEffect()[0][START]=effect;
                getEffect()[0][PASS]=new NullEffect();
                getEffect()[0][FAIL]=new NullEffect();
                setEncounterPart(0);
                break;
            case SAN_FRANCISCO:

                getEffect()[1][START]=new Improve(TestType.OBSERVATION,1,getInvestigator());
                getEffect()[1][PASS]=new NullEffect();
                getEffect()[1][FAIL]= new LooseOrGainHealthSanity(SpendType.SANITY,-2,getInvestigator());
                setEncounterPart(1);
                break;
            case BUENOS_AIRES:
                Effect effect2 = new And(new BecomeDelayed(getInvestigator()),
                        new GainSpell(new ArrayList<>(),getInvestigator()),
                        new GainSpell(new ArrayList<>(),getInvestigator()));
                YesNoChoice choice2 = new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect2.getText(),null,null);
                effect2.setCondition(choice2);
                getEffect()[2][START]=effect2;
                getEffect()[2][PASS]=new NullEffect();
                getEffect()[2][FAIL]=new NullEffect();
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.NONE;
        getMod()[0]=0;
        getTestType()[1] = TestType.WILL;
        getMod()[1]=-1;
        getTestType()[2] = TestType.NONE;
        getMod()[2]=0;
    }
}
