package gamemechanics.encounter.expeditionencounter;

import enums.*;
import gamemechanics.encounter.ExpeditionEncounter;
import gamemechanics.encounter.StandardEncounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;
import model.effects.*;

public class ExpeditionEncounter0 extends ExpeditionEncounter {

    public ExpeditionEncounter0() {
        super("exe_0");
        setFieldID(FieldID.PYRAMIDS);


    }


    @Override
    public void init() {
        super.init();

        getEffect()[0][START] = new NullEffect();
        getEffect()[0][PASS] = new NullEffect();
        getEffect()[0][FAIL] = new NullEffect();
        getTestType()[0] = TestType.STRENGTH;

        getEffect()[1][START] = new RetreatDoom(1, getInvestigator());
        getEffect()[1][PASS] = new NullEffect();
        getEffect()[1][FAIL] = new GainCondition(ConditionType.AMNESIA, getInvestigator());
        getTestType()[1] = TestType.LORE;


        getEffect()[2][START] = new And(new Loose(SpendType.HEALTH, 1, getInvestigator()), new GainCondition(ConditionType.INTERNAL_INJURY, getInvestigator()));
        getEffect()[2][PASS] = new RetreatDoom(1, getInvestigator());
        getEffect()[2][FAIL] = new NullEffect();
        getTestType()[2] = TestType.OBSERVATION;
        getMod()[2] = -1;
    }
}