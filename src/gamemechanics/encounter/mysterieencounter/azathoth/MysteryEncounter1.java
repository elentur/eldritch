package gamemechanics.encounter.mysterieencounter.azathoth;

import Service.GameService;
import enums.FieldID;
import enums.OldOnes;
import enums.SpendType;
import enums.TestType;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.MysteryEncounter;
import model.Effect;
import model.Field;
import model.effects.*;
import utils.ResourceUtil;

public class MysteryEncounter1 extends MysteryEncounter {

    public MysteryEncounter1() {
        super( "mye_1", OldOnes.AZATHOTH);
     

    }
    @Override
    public void init() {
        super.init();
        Effect effect1 = new Spend(SpendType.CLUE,2,GameService.getInstance().getEncounteringInvestigator());
        Effect effect2 = new AddEldritchToMystery(1);
        Effect effect3 = new RemoveEldritchToken(getField().getFieldID(),getEldritchToken());
        Effect effect = new And(effect1,effect2,effect3);
        YesNoChoice choice = new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect.getText(),null,null);
        choice.addCondition(e-> GameService.getInstance().getEncounteringInvestigator().getClues().size()>=2);
        effect.setCondition(choice);

        getEffect()[0][START]=effect;
        getEffect()[0][PASS] =new NullEffect();
        getEffect()[0][FAIL] = new NullEffect();
        getTestType()[0] = TestType.NONE;

    }

}
