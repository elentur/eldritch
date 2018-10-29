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
import java.util.List;


public class FluteOfTheOuterGods extends Artifact {

    public FluteOfTheOuterGods() {
        super(ItemType.ITEM_MAGICAL);
    }

    @Override
    public String getId() {
        return "&fluteOfTheOuterGods";
    }

    @Override
    public String getNameId() {
        return "${flute_of_the_outer_gods}";
    }

    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect = new And(new LooseOrGainHealthSanity(SpendType.SANITY,-2, inv),
                new LooseOrGainHealthSanity(SpendType.HEALTH,-2, inv),
               new DiscardMonster(GameService.getInstance().getFieldOfInvestigator(inv).getMonster()));

        Action encounter = new Action(inv,
                "fluteOfTheOuterGods",
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
