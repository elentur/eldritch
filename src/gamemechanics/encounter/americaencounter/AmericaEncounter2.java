package gamemechanics.encounter.americaencounter;

import enums.ConditionType;
import enums.TestType;
import gamemechanics.encounter.AmericaEncounter;
import model.effects.GainCondition;
import model.effects.GainSpell;
import model.effects.Improve;
import model.effects.NullEffect;

import java.util.ArrayList;

public class AmericaEncounter2 extends AmericaEncounter {

    public AmericaEncounter2( ) {
        super("ame_2");

      

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case ARKHAM:
                getEffect()[0][START]=new NullEffect();
                getEffect()[0][PASS]=new  GainSpell(new ArrayList<>(),getInvestigator());
                getEffect()[0][FAIL]=new NullEffect();
                setEncounterPart(0);
                break;
            case SAN_FRANCISCO:
                getEffect()[1][START]=new NullEffect();
                getEffect()[1][PASS]=new Improve(TestType.OBSERVATION,1,getInvestigator());
                getEffect()[1][FAIL]=new  GainCondition(ConditionType.DEPT,getInvestigator());
                setEncounterPart(1);
                break;
            case BUENOS_AIRES:
                getEffect()[2][START]=new NullEffect();
                getEffect()[2][PASS]=new  GainSpell(new ArrayList<>(),getInvestigator());
                getEffect()[2][FAIL]=new  GainCondition(ConditionType.DEPT,getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.LORE;
        getMod()[0]=+1;
        getTestType()[1] = TestType.INFLUENCE;
        getMod()[1]=0;
        getTestType()[2] = TestType.LORE;
        getMod()[2]=1;
    }
}
