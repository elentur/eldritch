package gamemechanics.encounter.americaencounter;

import enums.EffectSelector;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.AmericaEncounter;
import gamemechanics.encounter.ResearchEncounter;
import model.Item.Investigator;
import model.effects.*;

public class AmericaEncounter0 extends AmericaEncounter {

    public AmericaEncounter0(Investigator inv) {
        super(inv,"ame_0");

        switch (getField().getFieldID()){
            case ARKHAM:
                getEffect()[0][PASS]=new And(new GainClue(EffectSelector.THIS,1,inv),new GainClue(EffectSelector.ADDITIONAL,1,inv));
                getEffect()[0][FAIL]=new NullEffect();
                setEncounterPart(0);
                break;
            case SAN_FRANCISCO:
                getEffect()[1][PASS]=new And(new GainClue(EffectSelector.THIS,1,inv),new AdvanceDoom(EffectSelector.ANY,inv));
                getEffect()[1][FAIL]=new Loose(SpendType.HEALTH,1,inv);
                setEncounterPart(1);
                break;
            case BUENOS_AIRES:
                getEffect()[2][PASS]=new And(new GainClue(EffectSelector.THIS,1,inv),new GainClue(EffectSelector.ADDITIONAL,1,inv));
                getEffect()[2][FAIL]=new BecomeDelayed(inv);
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.OBSERVATION;
        getMod()[0]=-1;
        getTestType()[1] = TestType.OBSERVATION;
        getMod()[1]=-1;
        getTestType()[2] = TestType.LORE;

    }



}
