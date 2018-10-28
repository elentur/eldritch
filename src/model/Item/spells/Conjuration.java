package model.Item.spells;

import Service.GameService;
import container.Result;
import enums.*;
import gamemechanics.Action;
import gamemechanics.choice.InformationChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.*;
import model.effects.*;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Conjuration extends Spell {
    AssetFromReserve effect;
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
        if(GameService.getInstance().getReserve().getReserve(Arrays.asList(ItemType.TRINKET, ItemType.ITEM)).isEmpty()){
            GameService.getInstance().addChoice(new InformationChoice(
                    this.getName(),
                    ResourceUtil.get(getNameId().replace("}","_error0}"),"spell"),
                    null
            ));
            return null;
        }
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        effect = new AssetFromReserve(1,inv,ItemType.TRINKET, ItemType.ITEM);
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
        Effect custom = new Effect(EffectTyps.CUSTOM) {
            @Override
            public void execute() {
                effect.setNum(2);
                effect.setUseSuccess(false);
            }

            @Override
            public String getText() {
                return "";
            }
        };
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        SpellConsequence con0 = new SpellConsequence(this.getNameId(), 0, new int[]{0, 1,4}, Arrays.asList(
                effect1,
                new LooseOrGainHealthSanity(SpendType.SANITY, -1, inv),
                custom
        ));



       custom = new Effect(EffectTyps.CUSTOM) {
           @Override
           public void execute() {
               effect.setNum(0);
               effect.setUseSuccess(true);
           }

           @Override
           public String getText() {
               return "";
           }
       };

        SpellConsequence con1 = new SpellConsequence(this.getNameId(),1,new int[]{0,1,2},Arrays.asList(
               new LooseOrGainHealthSanity(SpendType.HEALTH,-2,GameService.getInstance().getEncounteringInvestigator()),
                new LooseOrGainHealthSanity(SpendType.SANITY,-1,GameService.getInstance().getEncounteringInvestigator()),
                custom

        ));
        return Arrays.asList(con0,con1);
    }
    @Override
    protected String getTextKey() {
        return "action";
    }
}
