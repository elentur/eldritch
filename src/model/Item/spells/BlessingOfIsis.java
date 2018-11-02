package model.Item.spells;

import Service.GameService;
import container.Result;
import enums.*;
import gamemechanics.Action;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.*;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlessingOfIsis extends Spell {

    public BlessingOfIsis() {
        super(ItemType.RITUAL);

    }
    private GainCondition effect;

    @Override
    public String getId() {
        return "&blessingOfIsis";
    }

    @Override
    public String getNameId() {
        return "${blessing_of_isis}";
    }

    @Override
    public List<ItemBonus> createBonus() {
       return new ArrayList<>();
    }



    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
         effect = new GainCondition(ConditionType.BLESSED, new InvestigatorChoice(GameService.getInstance().getFieldOfInvestigator(inv).getFieldID(),1));

        Action encounter = new Action(inv,
                "blessingOfIsis",
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
        SpellConsequence con0 = new SpellConsequence(this.getNameId(),0,new int[]{0,1,2},Arrays.asList(
                new GainCondition(ConditionType.CURSED,effect.getInvestigator()),
                new LooseOrGainHealthSanity(SpendType.SANITY,-1,GameService.getInstance().getEncounteringInvestigator()),
                new NullEffect()

        ));
        List<Effect> blessedEffect = new ArrayList<>();
        for(Investigator investigator : GameService.getInstance().getFieldOfInvestigator(GameService.getInstance().getEncounteringInvestigator()).getInvestigators()){
            boolean hasNotBlessedCondition = investigator.getInventory().getItems().getItemsWidthTypeFilter(
                    item->
                        item.getItemType().equals(ItemType.CONDITION) &&
                                item.getSubType().equals(ItemType.BLESSED_CONDITION)

            ).isEmpty();

            if(!investigator.equals(effect.getInvestigator()) && hasNotBlessedCondition ){
                blessedEffect.add( new GainCondition(ConditionType.BLESSED,investigator));
            }
        }

        SpellConsequence con1 = new SpellConsequence(this.getNameId(),1,new int[]{0,3},Arrays.asList(
               new LooseOrGainHealthSanity(SpendType.HEALTH,-1,GameService.getInstance().getEncounteringInvestigator()),
               new And(blessedEffect.toArray(new Effect[]{}))

        ));
        return Arrays.asList(con0,con1);
    }
    @Override
    protected String getTextKey() {
        return "action";
    }
}
