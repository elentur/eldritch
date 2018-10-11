package model.Item.spells;

import Service.GameService;
import container.Result;
import enums.*;
import gamemechanics.encounter.CombatEncounter;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.ItemBonus;
import model.Item.Spell;
import model.Item.SpellConsequence;
import model.Item.boni.ItemBonus_SwitchSkill;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StormOfSpirits extends Spell {
    public StormOfSpirits() {
        super(ItemType.INCANTATION);

    }



    @Override
    public String getId() {
        return  "&stormOfSpirits";
    }

    @Override
    public String getNameId() {
        return "${storm_of_spirits}";
    }
    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_SwitchSkill bonus1 = new ItemBonus_SwitchSkill(SituationType.COMBAT_ENCOUNTER,TestType.STRENGTH,TestType.LORE,this);
        boni.add(bonus1);
        return boni;
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
    public List<SpellConsequence> getConsequence(Result result) {
        Effect effect1 = new NullEffect();
        if(result.contains(1)){
            effect1 =   new Discard(this);
        }
        CombatEncounter encounter = (CombatEncounter)GameService.getInstance().getEncounterProperty().get();
        SpellConsequence con0 = new SpellConsequence(this.getNameId(),0,new int[]{0,1},Arrays.asList(
                effect1,
                new ChangeMonsterAttribute(MonsterAttributeType.DAMAGE,-1,GameService.getInstance().getLastChosenMonster())));

         effect1 = new NullEffect();
        if(encounter.getActiveMonster().getActualToughness()<=0){
            effect1 =   new Or(new NullEffect(),
                    new And( new LooseOrGainHealthSanity(SpendType.SANITY,-1,GameService.getInstance().getEncounteringInvestigator()),
                            new GainClue(EffectSelector.RANDOM,1,GameService.getInstance().getEncounteringInvestigator())));
        }
           SpellConsequence con1 = new SpellConsequence(this.getNameId(),1,new int[]{0,1},Arrays.asList(
                new LooseOrGainHealthSanity(SpendType.SANITY,-1,GameService.getInstance().getEncounteringInvestigator()),
                   effect1));
        return Arrays.asList(con0,con1);
    }
}
