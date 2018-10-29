package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_DiceResult;
import model.effects.And;
import model.effects.GainClue;
import model.effects.LooseOrGainHealthSanity;
import model.effects.NullEffect;

import java.util.ArrayList;
import java.util.List;


public class CultesDesGoules extends Artifact {

    public CultesDesGoules() {
        super(ItemType.ITEM_TOME);
    }

    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect = new And(new LooseOrGainHealthSanity(SpendType.SANITY,-1, inv),
                new GainClue(EffectSelector.RANDOM,2,inv));

        Action encounter = new Action(inv,
                "cultesDesGoules",
                new NullEffect(),
                effect,
                new NullEffect(),
                TestType.LORE,
                0,
                1,
                SituationType.ACTION
        );

        if ( GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ACTION)) {
            return encounter;
        }
        return null;
    }

    @Override
    public String getId() {
        return "&cultesDesGoules";
    }

    @Override
    public String getNameId() {
        return "${cultes_des_goules}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }


}
