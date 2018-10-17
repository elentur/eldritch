package gamemechanics.encounter.mysterieencounter.azathoth;

import Service.GameService;
import enums.*;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.MysteryEncounter;
import model.Effect;
import model.effects.AddEldritchToMystery;
import model.effects.And;
import model.effects.NullEffect;
import model.effects.Spend;
import utils.ResourceUtil;

public class MysteryEncounter0 extends MysteryEncounter {

    public MysteryEncounter0() {
        super( "mye_0", OldOnes.AZATHOTH);

     

    }
    @Override
    public void init() {
        super.init();
        Effect effect1 = new Spend(SpendType.CLUE,2,GameService.getInstance().getEncounteringInvestigator());
        Effect effect2 = new AddEldritchToMystery(1);
        Effect effect = new And(effect1,effect2);
        YesNoChoice choice = new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect.getText(),null,null);
        choice.addCondition(e-> GameService.getInstance().getEncounteringInvestigator().getClues().size()>=2);
        effect.setCondition(choice);

        getEffect()[0][START]=new NullEffect();
        getEffect()[0][PASS] =effect;
        getEffect()[0][FAIL] = new NullEffect();
        getTestType()[0] = TestType.OBSERVATION;

    }

}
