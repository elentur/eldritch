package gamemechanics.encounter.asiaencounter;

import enums.ConditionType;
import enums.ItemType;
import enums.TestType;
import gamemechanics.encounter.AsiaEncounter;
import model.effects.*;

import java.util.Collections;

public class AsiaEncounter5 extends AsiaEncounter {

    public AsiaEncounter5( ) {
        super("ase_5");

     

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case TOKYO:
                getEffect()[0][START]=new Improve(TestType.LORE,1,getInvestigator());
                getEffect()[0][PASS]=new NullEffect();
                getEffect()[0][FAIL]=new BecomeDelayed(getInvestigator());
                setEncounterPart(0);
                break;
            case SHANGHAI:
                 getEffect()[1][START]=new NullEffect();
                getEffect()[1][PASS]=new GainCondition(ConditionType.BLESSED,getInvestigator());
                getEffect()[1][FAIL]=new GainCondition(ConditionType.HALLUCINATIONS,getInvestigator());
                setEncounterPart(1);
                break;
            case SYDNEY:
                getEffect()[2][START]=new NullEffect();
                getEffect()[2][PASS]=new GainAsset(Collections.singletonList(ItemType.WEAPON),getInvestigator());
                getEffect()[2][FAIL]=new GainCondition(ConditionType.DEPT, getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.LORE;
        getMod()[0]=0;
        getTestType()[1] = TestType.WILL;
        getMod()[1]=0;
        getTestType()[2] = TestType.INFLUENCE;
        getMod()[2]=-1;
    }

}
