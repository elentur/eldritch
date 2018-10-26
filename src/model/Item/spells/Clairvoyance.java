package model.Item.spells;

import Service.GameService;
import container.Result;
import enums.*;
import gamemechanics.Test;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Field;
import model.Item.*;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.Item.token.ClueToken;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clairvoyance extends Spell {
    List<ItemBonus> boni;

    public Clairvoyance() {
        super(ItemType.INCANTATION);
        boni = new ArrayList<>();
    }

    @Override
    public String getId() {
        return "&clairvoyance";
    }

    @Override
    public String getNameId() {
        return "${clairvoyance}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        return boni;
    }

    @Override
    public Encounter getEncounter() {
        if (!isUsed() && GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ENCOUNTER)) {
            setUsed(true);
            GameService.getInstance().addUsedSpell(this);
            Test test = new Test(TestType.LORE, 0, 1, SituationType.TEST);
            GameService.getInstance().addTest(test);
            if (test.getResult().isSuccess()) {

                Effect startEncounter = new Effect(EffectTyps.CUSTOM) {
                    @Override
                    public void execute() {
                        Field field = GameService.getInstance().getChosenField();
                        for (Encounter encounter : field.getEncounters(GameService.getInstance().getEncounteringInvestigator())) {
                            if (encounter.getEncounterType().equals(EncounterType.RESEARCH_ENCOUNTER)) {
                                encounter.addEndEvent(encounter1 -> {
                                    Clairvoyance.this.boni.clear();
                                    return null;
                                });
                                GameService.getInstance().addEncounter(encounter);
                                break;
                            }
                        }
                    }

                    @Override
                    public String getText() {
                        return "";
                    }
                };
                ChooseSpace effect = new ChooseSpace(startEncounter);
                GameService.getInstance().addEffect(effect);
            }
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
                boni.add(new ItemBonus_AdditionalDice(1, TestType.ALL, SituationType.STANDARD_ENCOUNTER, RangeType.SELF, FieldType.ALL, Clairvoyance.this));
            }

            @Override
            public String getText() {
                return "";
            }
        };
        SpellConsequence con0 = new SpellConsequence(this.getNameId(), 0, new int[]{0, 1, 3}, Arrays.asList(
                new Or(new GainCondition(ConditionType.PARANOIA_CONDITION, GameService.getInstance().getEncounteringInvestigator()),
                        new Discard(this)),
                new NullEffect(),
                effect));


        effect = new Effect(EffectTyps.CUSTOM) {

            @Override
            public void execute() {
                Field field = GameService.getInstance().getChosenField();
                if(field.getNumberOfClues()>0) {
                    ClueToken clueToken = field.getTokens().getClues().get(0);
                    field.removeClue(clueToken);
                }
            }

            @Override
            public String getText() {
                return "";
            }
        };
        SpellConsequence con1 = new SpellConsequence(this.getNameId(), 1, new int[]{0, 1, 3}, Arrays.asList(
               effect,
                new LooseOrGainHealthSanity(SpendType.SANITY, -1, GameService.getInstance().getEncounteringInvestigator()),
                new GainClue(EffectSelector.RANDOM,1,GameService.getInstance().getEncounteringInvestigator())

        ));
        return Arrays.asList(con0, con1);
    }
}
