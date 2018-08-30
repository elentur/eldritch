package gamemechanics.encounter.europeencounter;

import enums.EffectSelector;
import enums.SpendType;
import enums.TestType;
import gamemechanics.encounter.AmericaEncounter;
import gamemechanics.encounter.EuropeEncounter;
import model.Item.Investigator;
import model.effects.*;

public class EuropeEncounter0 extends EuropeEncounter {

    public EuropeEncounter0() {
        super("eue_0");

 

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case LONDON:
                getEffect()[0][START]=new NullEffect();
                getEffect()[0][PASS]=new And(new GainClue(EffectSelector.THIS,1,getInvestigator()),new GainClue(EffectSelector.ADDITIONAL,1,getInvestigator()));
                getEffect()[0][FAIL]=new NullEffect();
                setEncounterPart(0);
                break;
            case ROME:
                getEffect()[1][START]=new NullEffect();
                getEffect()[1][PASS]=new And(new GainClue(EffectSelector.THIS,1,getInvestigator()),new AdvanceDoom(1,getInvestigator()));
                getEffect()[1][FAIL]=new Loose(SpendType.HEALTH,1,getInvestigator());
                setEncounterPart(1);
                break;
            case ISTANBUL:
                getEffect()[2][START]=new NullEffect();
                getEffect()[2][PASS]=new And(new GainClue(EffectSelector.THIS,1,getInvestigator()),new GainClue(EffectSelector.ADDITIONAL,1,getInvestigator()));
                getEffect()[2][FAIL]=new BecomeDelayed(getInvestigator());
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
