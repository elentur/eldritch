package model.Item;

import Service.GameService;
import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.Action;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.boni.ItemBonus_Delayed;
import model.effects.Discard;
import model.effects.NextInvestigator;
import model.effects.NullEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class DetainedCondition extends Condition {

    protected   Action action;
    protected ItemBonus_Delayed delayed;
    public DetainedCondition() {
        super(ItemType.DETAINED_CONDITION);


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
        return boni;
    }


    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        super.executeReckoning(inv, autoFail);
    }

    @Override
    public List<Effect> getDrawEffects(Investigator investigator) {

        action=  new Action(investigator,

                "detained_action",
                new NullEffect(),
               new Discard(this),
                new NullEffect(),
                TestType.INFLUENCE,
                0,
                1,
                SituationType.ACTION
        );
        action.addEndEvent(encounter -> {
                    GameService.getInstance().addEffect(new NextInvestigator());
                    return null;
                });
        delayed = new ItemBonus_Delayed(-1, Collections.singletonList(action),this);
        getBonus().add(delayed);
        delayed.create();
        investigator.addSpecialEncounter(getSpecialEncounter());
        return super.getDrawEffects(investigator);
    }

    @Override
    public void discard() {

        if(getOwner()!=null) {
            getOwner().removeSpecialEncounter(getSpecialEncounter());
        }
        delayed.destroy();
        super.discard();

    }

    protected abstract Encounter getSpecialEncounter();


}
