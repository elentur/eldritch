package model.Item.spells;

import Service.GameService;
import container.Result;
import enums.*;
import gamemechanics.ActionEncounter;
import gamemechanics.choice.EncounterChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.Spell;
import model.Item.SpellConsequence;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.Item.boni.ItemBonus_RepeatRoll;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MistsOfReleh extends Spell {
    public MistsOfReleh() {
        super(ItemType.INCANTATION);

    }



    @Override
    public String getId() {
        return  "&mistsOfReleh";
    }

    @Override
    public String getNameId() {
        return "${mists_of_releh}";
    }
    @Override
    public List<ItemBonus> createBonus() {
       return new ArrayList<>();
    }

    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect = new Effect(EffectTyps.CUSTOM) {

            @Override
            public void execute() {
                Field field = GameService.getInstance().getFieldOfInvestigator(inv);
                GameService.getInstance().addChoice(new EncounterChoice(field));
            }

            @Override
            public String getText() {
                return "";
            }
        };

        ActionEncounter encounter =  new ActionEncounter(inv,
                "mistsOfReleh",
                new NullEffect(),
                effect,
                new NullEffect(),
                TestType.LORE,
                0,
                1,
                SituationType.SPELL_EFFECT
        );
        if(!isUsed() && GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ENCOUNTER)){
            setUsed(true);
            GameService.getInstance().addUsedSpell(this);
            return encounter;
        }
        return null;
    }


    @Override
    protected String getTextKey() {
        return "action_encounter";
    }

    @Override
    public List<SpellConsequence> getConsequence(Result result) {
        Effect effect = new Effect(EffectTyps.CUSTOM) {

            @Override
            public void execute() {
                ((ItemBonus_AdditionalDice)getBonus().get(0)).setValue(5);
            }

            @Override
            public String getText() {
                return "";
            }
        };
        SpellConsequence con0 = new SpellConsequence(this.getNameId(),0,new int[]{0,2},Arrays.asList(
                new And(new LooseOrGainHealthSanity(SpendType.SANITY,-1,GameService.getInstance().getEncounteringInvestigator()),
                        new LooseOrGainHealthSanity(SpendType.HEALTH,-1,GameService.getInstance().getEncounteringInvestigator())),
                 new NullEffect()));


        effect = new Effect(EffectTyps.CUSTOM) {

            @Override
            public void execute() {
                getBonus().add(new ItemBonus_RepeatRoll(1,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,
                      MistsOfReleh.this));
            }

            @Override
            public String getText() {
                return "";
            }
        };
        SpellConsequence con1 = new SpellConsequence(this.getNameId(),1,new int[]{0,1},Arrays.asList(
                new Or(new GainCondition(ConditionType.HALLUCINATIONS_CONDITION,GameService.getInstance().getEncounteringInvestigator()), new Discard(this)),
                new LooseOrGainHealthSanity(SpendType.SANITY,-1,GameService.getInstance().getEncounteringInvestigator())

        ));
        return Arrays.asList(con0,con1);
    }
}
