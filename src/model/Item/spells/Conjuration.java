package model.Item.spells;

import Service.GameService;
import container.Result;
import enums.*;
import gamemechanics.Action;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.*;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Conjuration extends Spell {

    public Conjuration() {
        super(ItemType.RITUAL);

    }


    @Override
    public String getId() {
        return "&conjuration";
    }

    @Override
    public String getNameId() {
        return "${conjuration}";
    }

    @Override
    public List<ItemBonus> createBonus() {
       return new ArrayList<>();
    }



    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        AssetFromReserve effect = new AssetFromReserve(inv,ItemType.TRINKET, ItemType.ITEM);
        effect.setUseSuccess(true);

        Action encounter = new Action(inv,
                "conjuration",
                new NullEffect(),
                effect,
                new NullEffect(),
                TestType.LORE,
                1,
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
        Effect effect1 = new NullEffect();
        if (!result.contains(4)) {
            effect1 = new Discard(this);
        }
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        SpellConsequence con0 = new SpellConsequence(this.getNameId(), 0, new int[]{0, 1}, Arrays.asList(
                effect1,
                new LooseOrGainHealthSanity(SpendType.SANITY, -1, inv)));



        List<Effect> monsterEffect = new ArrayList<>();
        for(Monster monster : GameService.getInstance().getFieldOfInvestigator(GameService.getInstance().getEncounteringInvestigator()).getMonster()){
            if(!monster.equals(GameService.getInstance().getLastChosenMonster())){
                monsterEffect.add( new LooseOrGainHealthSanity(SpendType.HEALTH,-2,monster));
            }
        }

        SpellConsequence con1 = new SpellConsequence(this.getNameId(),1,new int[]{0,1,2},Arrays.asList(
               new LooseOrGainHealthSanity(SpendType.HEALTH,-2,GameService.getInstance().getEncounteringInvestigator()),
                new LooseOrGainHealthSanity(SpendType.SANITY,-1,GameService.getInstance().getEncounteringInvestigator()),
                monsterEffect.isEmpty()? new NullEffect(): new And(monsterEffect.toArray(new Effect[]{}))

        ));
        return Arrays.asList(con0,con1);
    }
    @Override
    protected String getTextKey() {
        return "action";
    }
}
