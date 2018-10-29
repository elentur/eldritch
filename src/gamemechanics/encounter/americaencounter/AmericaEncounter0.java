package gamemechanics.encounter.americaencounter;

import enums.*;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.AmericaEncounter;
import model.Effect;
import model.effects.*;
import utils.ResourceUtil;

import java.util.Collections;

public class AmericaEncounter0 extends AmericaEncounter {

    public AmericaEncounter0( ) {
        super("ame_0");

      

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case ARKHAM:
                getEffect()[0][START]=new GainSpell(Collections.singletonList(ItemType.INCANTATION),getInvestigator());
                getEffect()[0][PASS]=new NullEffect();
                getEffect()[0][FAIL]=new GainCondition(ConditionType.HALLUCINATIONS,getInvestigator());
                setEncounterPart(0);
                break;
            case SAN_FRANCISCO:
                Effect effect = new And(new Spend(SpendType.CLUE,1,getInvestigator()),
                        new Improve(TestType.OBSERVATION,1,getInvestigator()));
                YesNoChoice choice = new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect.getText(),null,null);
                effect.setCondition(choice);
                getEffect()[1][START]=effect;
                getEffect()[1][PASS]=new NullEffect();
                getEffect()[1][FAIL]=new NullEffect();
                setEncounterPart(1);
                break;
            case BUENOS_AIRES:
                getEffect()[2][START]=new NullEffect();
                getEffect()[2][PASS]=new GainCondition(ConditionType.BLESSED,getInvestigator());
                getEffect()[2][FAIL]=new LooseOrGainHealthSanity(SpendType.SANITY,-2,getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.LORE;
        getMod()[0]=0;
        getTestType()[1] = TestType.NONE;
        getMod()[1]=0;
        getTestType()[2] = TestType.LORE;
        getMod()[2]=0;
    }
}
