package gamemechanics.encounter.asiaencounter;

import enums.ConditionType;
import enums.FieldID;
import enums.SpendType;
import enums.TestType;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.AsiaEncounter;
import model.Effect;
import model.effects.*;
import utils.ResourceUtil;

public class AsiaEncounter6 extends AsiaEncounter {

    public AsiaEncounter6( ) {
        super("ase_3");

     

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case TOKYO:
                Effect effect1 = new And(new BecomeDelayed(getInvestigator()),
                        new Improve(1,1,getInvestigator()));
                YesNoChoice choice1 = new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect1.getText(),null,null);
                effect1.setCondition(choice1);
                getEffect()[0][START]=effect1;
                getEffect()[0][PASS]=new NullEffect();
                getEffect()[0][FAIL]=new NullEffect();
                setEncounterPart(0);
                break;
            case SHANGHAI:
                Effect effect2 = new And(new Spend(SpendType.CLUE,1,getInvestigator()),
                      new ChooseSpace(new DiscardMonster(new MonsterChoice(FieldID.CHOSEN_FIELD,true))));
                YesNoChoice choice2 = new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect2.getText(),null,null);
                effect2.setCondition(choice2);
                 getEffect()[1][START]=effect2;
                getEffect()[1][PASS]=new NullEffect();
                getEffect()[1][FAIL]=new NullEffect();
                setEncounterPart(1);
                break;
            case SYDNEY:
                getEffect()[2][START]=new NullEffect();
                getEffect()[2][PASS]=new GainCondition(ConditionType.BLESSED,getInvestigator());
                getEffect()[2][FAIL]=new GainCondition(ConditionType.DETAINED,getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.NONE;
        getMod()[0]=0;
        getTestType()[1] = TestType.NONE;
        getMod()[1]=0;
        getTestType()[2] = TestType.INFLUENCE;
        getMod()[2]=0;
    }

}
