package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.OtherWorldEncounter;
import javafx.beans.value.ChangeListener;
import model.Effect;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.effects.And;
import model.effects.DiscardMonster;
import model.effects.GainClue;
import model.effects.LooseOrGainHealthSanity;

import java.util.ArrayList;
import java.util.List;


public class RubyOfRlyeh extends Artifact {

    private ChangeListener listener;

    public RubyOfRlyeh() {
        super(ItemType.ITEM_MAGICAL);
    }

    @Override
    public String getId() {
        return "&rubyOfRlyeh";
    }

    @Override
    public String getNameId() {
        return "${ruby_of_rlyeh}";
    }

    @Override
    public void discard() {
        super.discard();
        GameService.getInstance().getPhases().getUpdate().removeListener(listener);
    }

    @Override
    public List<Effect> getDrawEffects() {
        listener = (a, oldValue, newValue) -> {
            if (GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.MYTHOS)) {
                Investigator inv = GameService.getInstance().getInvestigatorForItem(RubyOfRlyeh.this);
                inv.setMaxActions(2);
            }
        };
        GameService.getInstance().getPhases().getUpdate().addListener(listener);
        return super.getDrawEffects();
    }
    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect1 =new Effect(EffectTyps.CUSTOM) {
            @Override
            public void execute() {
                super.execute();
                GameService.getInstance().getEncounteringInvestigator().setMaxActions(4);
            }

            @Override
            public String getText() {
                return "";
            }
        };
        Effect effect = new And(new LooseOrGainHealthSanity(SpendType.SANITY,-1, inv),
                effect1);

        Action encounter = new Action(inv,
                "rubyOfRlyeh",
                effect,
                SituationType.ACTION
        );

        if ( GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ACTION)) {
            return encounter;
        }
        return null;
    }
    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }


}
