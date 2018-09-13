package gamemechanics.encounter.otherworldencounter;

import enums.*;
import gamemechanics.encounter.OtherWorldEncounter;
import model.effects.*;

public class OtherWorldEncounter9 extends OtherWorldEncounter {

    public OtherWorldEncounter9() {
        super( "owe_9");
        setOtherWorldID(OtherWorldID.YUGGOTH);


    }
    @Override
    public void init() {
        super.init();

        getEffect()[0][START]=new NullEffect();
        getEffect()[0][PASS] = new NullEffect();
        getEffect()[0][FAIL] = new NullEffect();
        getTestType()[0] = TestType.INFLUENCE;

        getEffect()[1][START]=new NullEffect();
        getEffect()[1][PASS] = new CloseGate(EffectSelector.THIS);
        getEffect()[1][FAIL] = new LooseOrGainHealthSanity(SpendType.SANITY,-1, getInvestigator());
        getTestType()[1] = TestType.LORE;


        getEffect()[2][START]=new Or(new LooseOrGainHealthSanity(SpendType.SANITY,2,getInvestigator()),new Spend(SpendType.CLUE,1, getInvestigator()));
        getEffect()[2][PASS] = new NullEffect();
        getEffect()[2][FAIL] = new NullEffect();
        getTestType()[2] = TestType.NONE;
        getMod()[2]=-1;
    }

}
