package model.Item.spells;

import Service.GameService;
import container.Result;
import enums.*;
import gamemechanics.Test;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.Spell;
import model.Item.SpellConsequence;
import model.Item.boni.ItemBonus_PreventLossOfHealthOrSanity;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstillBravery extends Spell {
    ItemBonus_PreventLossOfHealthOrSanity bonus;

    public InstillBravery() {
        super(ItemType.INCANTATION);

    }


    @Override
    public String getId() {
        return "&instillBravery";
    }

    @Override
    public String getNameId() {
        return "${instill_bravery}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        Test test = new Test( TestType.LORE, 0, 1, SituationType.SPELL_EFFECT);
        bonus = new ItemBonus_PreventLossOfHealthOrSanity(2, SpendType.SANITY, SituationType.ALL, RangeType.ALL, test, this);
        return new ArrayList<>();
    }

    @Override
    public Encounter getEncounter() {
        return null;
    }


    @Override
    protected String getTextKey() {
        return null;
    }

    @Override
    public List<Effect> getDrawEffects() {
        LooseOrGainHealthSanity.listener.add(bonus);
        return super.getDrawEffects();
    }

    @Override
    public void discard() {
        super.discard();
        LooseOrGainHealthSanity.listener.remove(bonus);
    }

    @Override
    public List<SpellConsequence> getConsequence(Result result) {
        Effect effect1 = new NullEffect();
        if (!result.contains(4)) {
            effect1 = new Discard(this);
        }
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        SpellConsequence con0 = new SpellConsequence(this.getNameId(), 0, new int[]{0, 1}, Arrays.asList(
                effect1,
                new LooseOrGainHealthSanity(SpendType.HEALTH, -1, inv)));
        Effect customEffect = new Effect(EffectTyps.CUSTOM) {
            @Override
            public void execute() {
                super.execute();
                bonus.setValue(result.getNumberOfSuccess());
            }

            @Override
            public String getText() {
                return "";
            }
        };

        SpellConsequence con1 = new SpellConsequence(this.getNameId(), 1, new int[]{0, 1, 3}, Arrays.asList(
                new Or(new LooseOrGainHealthSanity(SpendType.SANITY, -1, inv),
                        new GainCondition(ConditionType.HALLUCINATIONS_CONDITION, inv)),
                new NullEffect(),
                customEffect
        ));
        return Arrays.asList(con0, con1);
    }
}
