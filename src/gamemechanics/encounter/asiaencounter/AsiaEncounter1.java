package gamemechanics.encounter.asiaencounter;

import enums.FieldID;
import enums.SpendType;
import enums.TestType;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.AsiaEncounter;
import model.Effect;
import model.effects.*;

import java.util.Collections;

public class AsiaEncounter1 extends AsiaEncounter {

    public AsiaEncounter1( ) {
        super("ase_1");

     

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case TOKYO:
                getEffect()[0][START]=new NullEffect();
                getEffect()[0][PASS]=new Improve( 1,1,getInvestigator());
                getEffect()[0][FAIL]=new LooseOrGainHealthSanity(SpendType.HEALTH,-1, getInvestigator());
                setEncounterPart(0);
                break;
            case SHANGHAI:
                Effect effect = new And(new BecomeDelayed(getInvestigator()),new ChooseSpace(new LooseOrGainHealthSanity(SpendType.HEALTH,-2,new MonsterChoice(FieldID.CHOSEN_FIELD))));
                YesNoChoice choice = new YesNoChoice(getName(),effect.getText(), Collections.singletonList(effect),null);
                getEffect()[1][START]=new ChoiceEffect(choice);
                getEffect()[1][PASS]=new NullEffect();
                getEffect()[1][FAIL]=new NullEffect();
                setEncounterPart(1);
                break;
            case SYDNEY:
                getEffect()[2][START]=new Improve( TestType.STRENGTH,1,getInvestigator());
                getEffect()[2][PASS]=new NullEffect();
                getEffect()[2][FAIL]=new LooseOrGainHealthSanity(SpendType.HEALTH,-2, getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.LORE;
        getMod()[0]=0;
        getTestType()[1] = TestType.NONE;
        getMod()[1]=0;
        getTestType()[2] = TestType.STRENGTH;
        getMod()[2]=-1;
    }

}
