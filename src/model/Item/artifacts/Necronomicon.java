package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Necronomicon extends Artifact {

    public Necronomicon() {
        super(ItemType.ITEM_TOME);
    }

    @Override
    public String getId() {
        return "&necronomicon";
    }

    @Override
    public String getNameId() {
        return "${necronomicon}";
    }
    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect = new And(new LooseOrGainHealthSanity(SpendType.SANITY,-1,inv),
                new GainSpell(new ArrayList<>(), inv),
                new GainSpell(new ArrayList<>(), inv));

        Action encounter = new Action(inv,
                "necronomicon",
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
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }


}
