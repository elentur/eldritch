package gamemechanics.encounter.asiaencounter;

import Service.GameService;
import enums.*;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.AsiaEncounter;
import model.Effect;
import model.effects.*;
import utils.ResourceUtil;

import java.util.Collections;

public class AsiaEncounter7 extends AsiaEncounter {

    public AsiaEncounter7() {
        super("ase_7");


    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()) {
            case TOKYO:
                getEffect()[0][START] = new NullEffect();
                getEffect()[0][PASS] = new GainCondition(ConditionType.BLESSED, getInvestigator());
                getEffect()[0][FAIL] = new GainCondition(ConditionType.DETAINED, getInvestigator());
                setEncounterPart(0);
                break;
            case SHANGHAI:
                Effect effect = new Effect(EffectTyps.CUSTOM) {

                    @Override
                    public void execute() {
                        super.execute();
                        condition = new MonsterChoice(FieldID.CHOSEN_FIELD);
                        super.init();
                        if (!isAccepted()) {
                            return;
                        }
                        GameService.getInstance().addEffect(new ChooseSpace(new MoveMonster(  ((MonsterChoice) condition).getSelectedMonster().get(0),FieldID.CHOSEN_FIELD)));
                    }

                    @Override
                    public String getText() {
                        return "";
                    }
                };
                getEffect()[1][START] = new NullEffect();
                getEffect()[1][PASS] = new ChooseSpace(effect);
                getEffect()[1][FAIL] = new NullEffect();
                setEncounterPart(1);
                break;
            case SYDNEY:
                Effect effect1 = new And(new BecomeDelayed(getInvestigator()),
                        new GainAsset(Collections.singletonList(ItemType.WEAPON), getInvestigator()));
                YesNoChoice choice1 = new YesNoChoice(ResourceUtil.get("${do_you_want}", "ui"), effect1.getText(), null, null);
                effect1.setCondition(choice1);
                getEffect()[2][START] = effect1;
                getEffect()[2][PASS] = new NullEffect();
                getEffect()[2][FAIL] = new NullEffect();
                setEncounterPart(2);
                break;
        }

        getTestType()[0] = TestType.STRENGTH;

        getMod()[0] = 0;

        getTestType()[1] = TestType.INFLUENCE;

        getMod()[1] = 0;

        getTestType()[2] = TestType.NONE;

        getMod()[2] = 0;
    }

}
