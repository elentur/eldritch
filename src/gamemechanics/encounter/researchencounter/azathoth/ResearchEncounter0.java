package gamemechanics.encounter.researchencounter.azathoth;

import enums.*;
import gamemechanics.encounter.ResearchEncounter;
import model.Item.Investigator;
import model.effects.*;

public class ResearchEncounter0 extends ResearchEncounter {

    public ResearchEncounter0(Investigator inv) {
        super(inv,"re_0",OldOnes.AZATHOTH);

        switch (getField().getType()){
            case CITY:
                getEffect()[0][START]=new NullEffect();
                getEffect()[0][PASS]=new And(new GainClue(EffectSelector.THIS,1,inv),new GainClue(EffectSelector.ADDITIONAL,1,inv));
                getEffect()[0][FAIL]=new NullEffect();
                setEncounterPart(0);
                break;
            case WILDERNESS:
                getEffect()[1][START]=new NullEffect();
                getEffect()[1][PASS]=new And(new GainClue(EffectSelector.THIS,1,inv),new AdvanceOmen(EffectSelector.ANY,1,inv));
                getEffect()[1][FAIL]=new Loose(SpendType.HEALTH,1,inv);
                setEncounterPart(1);
                break;
            case SEA:
                getEffect()[2][START]=new NullEffect();
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
