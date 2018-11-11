package gamemechanics.encounter.asiaencounter;

import enums.ConditionType;
import enums.FieldID;
import enums.SpendType;
import enums.TestType;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.encounter.AsiaEncounter;
import model.effects.*;

public class AsiaEncounter3 extends AsiaEncounter {

    public AsiaEncounter3( ) {
        super("ase_3");

     

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case TOKYO:
                getEffect()[0][START]=new NullEffect();
                getEffect()[0][PASS]=new Improve( TestType.LORE,1,getInvestigator());
                getEffect()[0][FAIL]=new NullEffect();
                setEncounterPart(0);
                break;
            case SHANGHAI:
                 getEffect()[1][START]=new NullEffect();
                getEffect()[1][PASS]=new ChooseSpace(new LooseOrGainHealthSanity(SpendType.HEALTH,-2,new MonsterChoice(FieldID.CHOSEN_FIELD)));
                getEffect()[1][FAIL]=new GainCondition(ConditionType.DETAINED,getInvestigator());
                setEncounterPart(1);
                break;
            case SYDNEY:
                getEffect()[2][START]=new NullEffect();
                getEffect()[2][PASS]=new Improve( TestType.STRENGTH,1,getInvestigator());
                getEffect()[2][FAIL]=new LooseOrGainHealthSanity(SpendType.SANITY,-1, getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.INFLUENCE;
        getMod()[0]=0;
        getTestType()[1] = TestType.INFLUENCE;
        getMod()[1]=0;
        getTestType()[2] = TestType.WILL;
        getMod()[2]=0;
    }

}
