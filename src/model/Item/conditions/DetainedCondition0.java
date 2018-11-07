package model.Item.conditions;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.ActionEncounter;
import gamemechanics.Test;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_Delayed;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


public class DetainedCondition0 extends Condition {

    private ItemBonus_Delayed delayed;
    private final Function<Encounter,Encounter> encounterListener;
    public DetainedCondition0() {
        super(ItemType.DETAINED_CONDITION);
        encounterListener = (encounter -> {
            if(encounter!= null && !encounter.getEncounterType().equals(EncounterType.ACTION)){
                return  getEncounter();
            }
            return encounter;
        });
    }

    @Override
    public String getId() {
        return "&detainedCondition";
    }


    @Override
    public String getNameId() {
        return "${detained_condition}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        delayed = new ItemBonus_Delayed(-1, Arrays.asList((Action)getEncounter()),this);
        boni.add(delayed);
        return boni;
    }


    @Override
    public Action getEncounter() {
        Test test = new Test(TestType.OBSERVATION,0,1,SituationType.TEST);
        test.getEffect()[0][1]= new And(new LooseOrGainHealthSanity(SpendType.HEALTH,-2,GameService.getInstance().getEncounteringInvestigator()),
                new LooseOrGainHealthSanity(SpendType.SANITY,-2,GameService.getInstance().getEncounteringInvestigator()));

        Effect effect = new Or(new Spend(SpendType.CLUE,1,GameService.getInstance().getEncounteringInvestigator()),
                new StartTest(test));
        ActionEncounter action=  new ActionEncounter(GameService.getInstance().getEncounteringInvestigator(),
                "detained",
                 effect,
                SituationType.ACTION
        );
        action.addEndEvent(encounter -> {
            GameService.getInstance().addEffect(new Discard(this));
            GameService.getInstance().addEffect(new NextInvestigator());
            return null;
        });
        return action;
    }

    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        super.executeReckoning(inv, autoFail);
    }

    @Override
    public List<Effect> getDrawEffects() {
        delayed.create();
        GameService.getInstance().addEncounterListener(encounterListener);
        return super.getDrawEffects();
    }

    @Override
    public void discard() {
        delayed.destroy();
        GameService.getInstance().removeEncounterListener(encounterListener);
        super.discard();

    }
}
