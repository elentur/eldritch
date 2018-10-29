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


public class DeVermisMysteriis extends Artifact {

    public DeVermisMysteriis() {
        super(ItemType.ITEM_TOME);
    }

    @Override
    public String getId() {
        return "&deVermisMysteriis";
    }

    @Override
    public String getNameId() {
        return "${de_vermis_mysteriis}";
    }
    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect = new And(new LooseOrGainHealthSanity(SpendType.SANITY,-1, inv),
               new Improve(1,1,inv));

        Action encounter = new Action(inv,
                "deVermisMysteriis",
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
