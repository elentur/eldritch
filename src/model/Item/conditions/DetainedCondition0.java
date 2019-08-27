package model.Item.conditions;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.ActionEncounter;
import gamemechanics.Test;
import model.Effect;
import model.Item.DetainedCondition;
import model.effects.*;


public class DetainedCondition0 extends DetainedCondition {


    @Override
    public Action getEncounter() {
        if (GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ACTION)) {
            return action;
        }
        Test test = new Test(TestType.INFLUENCE, -1, 1, SituationType.TEST);
        //  test.getEffect()[0][1]= new And(new Discard(this), new NextInvestigator());
        test.getEffect()[0][2] = new And(
                new LooseOrGainHealthSanity(SpendType.HEALTH, -2, GameService.getInstance().getEncounteringInvestigator()),
                new LooseOrGainHealthSanity(SpendType.SANITY, -2, GameService.getInstance().getEncounteringInvestigator())
                );

        Effect effect = new Or(
                new Spend(SpendType.CLUE, 1, GameService.getInstance().getEncounteringInvestigator()),
                new StartTest(test));
        ActionEncounter action = new ActionEncounter(GameService.getInstance().getEncounteringInvestigator(),
                "detained_encounter",
                effect,
                SituationType.ACTION
        );
        action.addEndEvent(encounter -> {
            new Discard(this);
            return null;
        });
        return action;
    }

}
