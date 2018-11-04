package gamemechanics.encounter.asiaencounter;

import enums.*;
import gamemechanics.encounter.AsiaEncounter;
import model.effects.*;

public class AsiaEncounter0 extends AsiaEncounter {

    public AsiaEncounter0( ) {
        super("ase_0");

     

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case TOKYO:
                getEffect()[0][START]=new NullEffect();
                getEffect()[0][PASS]=new Improve( TestType.LORE,1,getInvestigator());
                getEffect()[0][FAIL]=new LooseOrGainHealthSanity(SpendType.SANITY,-1, getInvestigator());
                setEncounterPart(0);
                break;
            case SHANGHAI:
                getEffect()[1][START]=new NullEffect();
                getEffect()[1][PASS]=new ChooseSpace(new LooseOrGainHealthSanity(SpendType.HEALTH,-2,EffectSelector.ALL,FieldID.CHOSEN_FIELD,ItemType.MONSTER));
                getEffect()[1][FAIL]=new GainCondition(ConditionType.BACK_INJURY,getInvestigator());
                setEncounterPart(1);
                break;
            case SYDNEY:
                getEffect()[2][START]=new NullEffect();
                getEffect()[2][PASS]=new Improve( TestType.STRENGTH,1,getInvestigator());
                getEffect()[2][FAIL]=new LooseOrGainHealthSanity(SpendType.SANITY,-1, getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.LORE;
        getMod()[0]=0;
        getTestType()[1] = TestType.INFLUENCE;
        getMod()[1]=0;
        getTestType()[2] = TestType.WILL;
        getMod()[2]=0;
    }

}
