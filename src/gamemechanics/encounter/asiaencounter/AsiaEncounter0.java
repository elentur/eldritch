package gamemechanics.encounter.asiaencounter;

import enums.EffectSelector;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.AmericaEncounter;
import gamemechanics.encounter.AsiaEncounter;
import model.Item.Investigator;
import model.effects.*;

public class AsiaEncounter0 extends AsiaEncounter {

    public AsiaEncounter0(Investigator inv) {
        super(inv,"ase_0");

        switch (getField().getFieldID()){
            case TOKYO:
                getEffect()[0][START]=new NullEffect();
                getEffect()[0][PASS]=new And(new GainClue(EffectSelector.THIS,1,inv),new GainClue(EffectSelector.ADDITIONAL,1,inv));
                getEffect()[0][FAIL]=new NullEffect();
                setEncounterPart(0);
                break;
            case SHANGHAI:
                getEffect()[1][START]=new NullEffect();
                getEffect()[1][PASS]=new And(new GainClue(EffectSelector.THIS,1,inv),new AdvanceDoom(1,inv));
                getEffect()[1][FAIL]=new Loose(SpendType.HEALTH,1,inv);
                setEncounterPart(1);
                break;
            case SYDNEY:
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
