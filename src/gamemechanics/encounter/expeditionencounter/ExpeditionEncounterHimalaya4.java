package gamemechanics.encounter.expeditionencounter;

import Service.GameService;
import enums.*;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.ExpeditionEncounter;
import gui.InterfaceLinking;
import model.Effect;
import model.Item.Monster;
import model.effects.*;

import java.util.ArrayList;

public class ExpeditionEncounterHimalaya4 extends ExpeditionEncounter {

    public ExpeditionEncounterHimalaya4() {
        super("exe_4");
        setFieldID(FieldID.HIMALAYA);


    }


    @Override
    public void init() {
        super.init();

        getEffect()[0][START] = new NullEffect();
        getEffect()[0][PASS] = new NullEffect();
        getEffect()[0][FAIL] = new NullEffect();
        getTestType()[0] = TestType.STRENGTH;

        getEffect()[1][START] =new NullEffect();
        getEffect()[1][PASS] = new And(new GainSpell(new ArrayList<>(),getInvestigator()),new GainSpell(new ArrayList<>(),getInvestigator()));
        getEffect()[1][FAIL] = new GainCondition(ConditionType.HALLUCINATIONS,getInvestigator());
        getTestType()[1] = TestType.LORE;
        getMod()[1] = -1;


        getEffect()[2][START] = new NullEffect();
        getEffect()[2][PASS] =  new GainClue(EffectSelector.RANDOM,2,getInvestigator());
        getEffect()[2][FAIL] = new GainCondition(ConditionType.CURSED,getInvestigator());
        getTestType()[2] = TestType.OBSERVATION;
        getMod()[2] = -1;
    }
}