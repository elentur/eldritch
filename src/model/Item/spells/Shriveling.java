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

public class Shriveling extends Spell {

    public Shriveling() {
        super(ItemType.RITUAL);

    }


    @Override
    public String getId() {
        return "&shriveling";
    }

    @Override
    public String getNameId() {
        return "${shriveling}";
    }

    @Override
    public List<ItemBonus> createBonus() {
       return new ArrayList<>();
    }



    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect = new LooseOrGainHealthSanity(SpendType.HEALTH,-2, new MonsterChoice(GameService.getInstance().getFieldOfInvestigator(inv).getFieldID(),true));

        Action encounter = new Action(inv,
                "shriveling",
                new NullEffect(),
                effect,
                new NullEffect(),
                TestType.LORE,
                0,
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
        SpellConsequence con0 = new SpellConsequence(this.getNameId(),0,new int[]{1,2,3},Arrays.asList(
                new Or(new LooseOrGainHealthSanity(SpendType.SANITY,-2,GameService.getInstance().getEncounteringInvestigator()),
                       new Discard(this)),
                new NullEffect(),
                new LooseOrGainHealthSanity(SpendType.HEALTH,-result.getNumberOfSuccess(),GameService.getInstance().getLastChosenMonster())

        ));
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
