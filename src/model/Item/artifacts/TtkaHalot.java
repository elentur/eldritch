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
import model.effects.And;
import model.effects.GainSpell;
import model.effects.LooseOrGainHealthSanity;
import model.effects.NullEffect;

import java.util.ArrayList;
import java.util.List;


public class TtkaHalot extends Artifact {

    public TtkaHalot() {
        super(ItemType.ITEM_TOME);
    }

    @Override
    public String getId() {
        return "&ttkaHalot";
    }

    @Override
    public String getNameId() {
        return "${ttka_halot}";
    }
    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect = new And(new LooseOrGainHealthSanity(SpendType.SANITY,-1,inv),
                new LooseOrGainHealthSanity(SpendType.HEALTH,-3,
                        new MonsterChoice(GameService.getInstance().getFieldOfInvestigator(inv).getFieldID())));

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
