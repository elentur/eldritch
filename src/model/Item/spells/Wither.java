package model.Item.spells;

import Service.GameService;
import container.Result;
import enums.*;
import gamemechanics.ActionEncounter;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.*;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.Item.boni.ItemBonus_RepeatRoll;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wither extends Spell {
    public Wither() {
        super(ItemType.INCANTATION);

    }



    @Override
    public String getId() {
        return  "&wither";
    }

    @Override
    public String getNameId() {
        return "${wither}";
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
               getBonus().add(new ItemBonus_AdditionalDice(3,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,
                       RangeType.SELF,FieldType.ALL,Wither.this));
            }

            @Override
            public String getText() {
                return "";
            }
        };

        ActionEncounter encounter =  new ActionEncounter(inv,
                "wither",
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
        SpellConsequence con0 = new SpellConsequence(this.getNameId(),0,new int[]{1,2},Arrays.asList(
                new Or(new LooseOrGainHealthSanity(SpendType.SANITY,-2,GameService.getInstance().getEncounteringInvestigator()),
                        new Discard(this)),
                 effect));
        effect = new Effect(EffectTyps.CUSTOM) {

            @Override
            public void execute() {
                getBonus().add(new ItemBonus_RepeatRoll(1,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,
                      Wither.this));
            }

            @Override
            public String getText() {
                return "";
            }
        };
        SpellConsequence con1 = new SpellConsequence(this.getNameId(),1,new int[]{0,1,2},Arrays.asList(
                new Or(new GainCondition(ConditionType.INJURY,GameService.getInstance().getEncounteringInvestigator()), new Discard(this)),
                new LooseOrGainHealthSanity(SpendType.SANITY,-1,GameService.getInstance().getEncounteringInvestigator()),
                effect

        ));
        return Arrays.asList(con0,con1);
    }
}
