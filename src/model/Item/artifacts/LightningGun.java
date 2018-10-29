package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.Monster;
import model.Item.boni.ItemBonus_GainDice;
import model.Item.boni.ItemBonus_SuccessMultiplier;
import model.effects.And;
import model.effects.DiscardMonster;
import model.effects.LooseOrGainHealthSanity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LightningGun extends Artifact {

    public LightningGun() {
        super(ItemType.ITEM_WEAPON_MAGICAL);
    }

    @Override
    public String getId() {
        return "&lightningGun";
    }

    @Override
    public String getNameId() {
        return "${lightning_gun}";
    }
    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        List<Effect> effects = new ArrayList<>();
        for(Monster m : GameService.getInstance().getFieldOfInvestigator(inv).getMonster()){
            effects.add( new LooseOrGainHealthSanity(SpendType.HEALTH,-1, m));
        }
        effects.add(0,new LooseOrGainHealthSanity(SpendType.HEALTH,-1, inv));
        Effect effect = new And(effects.toArray(new Effect[]{}));

        Action encounter = new Action(inv,
                "lightningGun",
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
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(6, TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        return boni;
    }


}
