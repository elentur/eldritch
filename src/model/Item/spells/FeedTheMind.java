package model.Item.spells;

import Service.GameService;
import container.Result;
import enums.*;
import gamemechanics.Action;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.*;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedTheMind extends Spell {
    private  Improve effect;
    public FeedTheMind() {
        super(ItemType.RITUAL);

    }


    @Override
    public String getId() {
        return "&feedTheMind";
    }

    @Override
    public String getNameId() {
        return "${feed_the_mind}";
    }

    @Override
    public List<ItemBonus> createBonus() {
       return new ArrayList<>();
    }



    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
           effect = new Improve(1,1,inv);

        Action encounter = new Action(inv,
                "feedTheMind",
                new NullEffect(),
                effect,
                new NullEffect(),
                TestType.LORE,
                -1,
                1,
                SituationType.SPELL_EFFECT
        );
        if ( GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ACTION)) {

            if (!GameService.getInstance().getEncounteringInvestigator().getDoneActions().contains(encounter)) {
                GameService.getInstance().addUsedSpell(this);
                return encounter;

            }
        }
        return null;
    }

    @Override
    public List<SpellConsequence> getConsequence(Result result) {
        Effect effect1 = new Effect(EffectTyps.CUSTOM) {
            @Override
            public void execute() {
                super.execute();
                effect.setNumber(effect.getNumber()+1);
            }

            @Override
            public String getText() {
                return "";
            }
        };
        SpellConsequence con0 = new SpellConsequence(this.getNameId(),0,new int[]{0,3},Arrays.asList(
                new Or(new LooseOrGainHealthSanity(SpendType.SANITY,-2,GameService.getInstance().getEncounteringInvestigator()),
                       new Discard(this)),
                effect1


        ));


         effect1 = new NullEffect();
        if (!result.contains(4)) {
            effect1 = new Discard(this);
        }

        SpellConsequence con1 = new SpellConsequence(this.getNameId(),1,new int[]{0,1},Arrays.asList(
                effect1,
                new LooseOrGainHealthSanity(SpendType.SANITY,-1,GameService.getInstance().getEncounteringInvestigator())

        ));
        return Arrays.asList(con0,con1);
    }
    @Override
    protected String getTextKey() {
        return "action";
    }
}
