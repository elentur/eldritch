package model.Item.conditions;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.ActionEncounter;
import gamemechanics.Test;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.DetainedCondition;
import model.effects.*;


public class DetainedCondition1 extends DetainedCondition {


    @Override
    public Action getEncounter() {
        if (GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ACTION)) {
            return action;
        }
        return null;
    }

    @Override
    protected Encounter getSpecialEncounter() {
        Test test = new Test(TestType.WILL, -1, 1, SituationType.TEST);
          test.getEffect()[0][1]= new And(new Discard(this), new NextInvestigator());
        test.getEffect()[0][2] = new And(
                new LooseOrGainHealthSanity(SpendType.SANITY, -3, GameService.getInstance().getEncounteringInvestigator()),
                new GainCondition(ConditionType.PARANOIA,getOwner()),
                new Discard(this),
                new NextInvestigator()
        );

        Effect effect = new Or(
                new And(
                        new Spend(SpendType.CLUE, 1, GameService.getInstance().getEncounteringInvestigator()),
                        new Discard(this),
                        new NextInvestigator()),
                new StartTest(test));
        ActionEncounter action = new ActionEncounter(GameService.getInstance().getEncounteringInvestigator(),
                "detained_encounter",
                effect,
                SituationType.ACTION
        );
        return action;
    }
}
